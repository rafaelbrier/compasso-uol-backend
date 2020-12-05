package br.compasso.uol.backend.services.impl;

import br.compasso.uol.backend.dtos.ClienteCreateRequest;
import br.compasso.uol.backend.dtos.ClienteFetchRequest;
import br.compasso.uol.backend.dtos.ClienteNameChangeRequest;
import br.compasso.uol.backend.exceptions.GenericException;
import br.compasso.uol.backend.exceptions.NotFoundException;
import br.compasso.uol.backend.models.Cidade;
import br.compasso.uol.backend.models.Cliente;
import br.compasso.uol.backend.repositories.ClienteRepository;
import br.compasso.uol.backend.repositories.specifications.ClienteSpecification;
import br.compasso.uol.backend.services.CidadeService;
import br.compasso.uol.backend.services.ClienteService;
import br.compasso.uol.backend.utils.MessageUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Service
@Validated
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final CidadeService cidadeService;
    private final ModelMapper modelMapper;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository,
                              CidadeService cidadeService,
                              ModelMapper modelMapper) {
        this.clienteRepository = clienteRepository;
        this.cidadeService = cidadeService;
        this.modelMapper = modelMapper;
    }

    @Override
    public Cliente buscarPorId(long clienteId) {
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new NotFoundException(MessageUtils.buscarMensagem("cliente.nao.encontrado"), clienteId));
    }

    @Override
    public List<Cliente> buscarClientes(ClienteFetchRequest clienteFetchRequest) {
        ClienteSpecification clienteSpecification = new ClienteSpecification(clienteFetchRequest);
        return clienteRepository.findAll(clienteSpecification);
    }

    @Override
    public Cliente salvarCliente(@Valid ClienteCreateRequest clienteCreateRequest) {
        Cidade cidade = cidadeService.buscarPorId(clienteCreateRequest.getCidadeId());
        Cliente cliente = modelMapper.map(clienteCreateRequest, Cliente.class);
        cliente.setCidade(cidade);
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente alterarNomeCliente(long clienteId, @Valid ClienteNameChangeRequest clienteNameChangeRequest) {
        Cliente cliente = buscarPorId(clienteId);
        cliente.setNomeCompleto(clienteNameChangeRequest.getNomeCompleto());
        return clienteRepository.save(cliente);
    }

    @Override
    public void deletarCliente(long clienteId) {
        try {
            clienteRepository.deleteById(clienteId);
        } catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException(MessageUtils.buscarMensagem("cliente.deletar.nao.encontrado", clienteId));
        } catch (Exception ex) {
            throw new GenericException(MessageUtils.buscarMensagem("response.code500"));
        }
    }
}
