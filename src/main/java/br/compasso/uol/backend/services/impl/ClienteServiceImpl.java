package br.compasso.uol.backend.services.impl;

import br.compasso.uol.backend.dtos.ClienteCreateRequest;
import br.compasso.uol.backend.dtos.ClienteFetchRequest;
import br.compasso.uol.backend.dtos.ClienteRetornoDto;
import br.compasso.uol.backend.models.Cidade;
import br.compasso.uol.backend.models.Cliente;
import br.compasso.uol.backend.repositories.ClienteRepository;
import br.compasso.uol.backend.repositories.specifications.ClienteSpecification;
import br.compasso.uol.backend.services.CidadeService;
import br.compasso.uol.backend.services.ClienteService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Cliente> buscarClientes(ClienteFetchRequest clienteFetchRequest) {
        ClienteSpecification clienteSpecification = new ClienteSpecification(clienteFetchRequest);
        return modelMapper.map(clienteRepository.findAll(clienteSpecification), new TypeToken<List<ClienteRetornoDto>>() {}.getType());
    }

    @Override
    public Cliente salvarCliente(@Valid ClienteCreateRequest clienteCreateRequest) {
        Cidade cidade = cidadeService.buscarPorId(clienteCreateRequest.getCidadeId());
        Cliente cliente = modelMapper.map(clienteCreateRequest, Cliente.class);
        cliente.setCidade(cidade);
        return clienteRepository.save(cliente);
    }

}
