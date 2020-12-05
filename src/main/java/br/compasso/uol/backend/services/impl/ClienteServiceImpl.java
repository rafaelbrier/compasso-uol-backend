package br.compasso.uol.backend.services.impl;

import br.compasso.uol.backend.dtos.ClienteCreateRequest;
import br.compasso.uol.backend.dtos.ClienteFetchRequest;
import br.compasso.uol.backend.dtos.ClienteRetornoDto;
import br.compasso.uol.backend.repositories.ClienteRepository;
import br.compasso.uol.backend.repositories.specifications.ClienteSpecification;
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
    private final ModelMapper modelMapper;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository,
                              ModelMapper modelMapper) {
        this.clienteRepository = clienteRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<ClienteRetornoDto> buscarClientes(ClienteFetchRequest clienteFetchRequest) {
        ClienteSpecification clienteSpecification = new ClienteSpecification(clienteFetchRequest);
        return modelMapper.map(clienteRepository.findAll(clienteSpecification), new TypeToken<List<ClienteRetornoDto>>() {}.getType());
    }

    @Override
    public ClienteRetornoDto salvarCidade(@Valid ClienteCreateRequest clienteCreateRequest) {
        return null;
    }
}
