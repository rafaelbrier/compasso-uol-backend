package br.compasso.uol.backend.services.impl;

import br.compasso.uol.backend.dtos.CidadeCreateRequest;
import br.compasso.uol.backend.dtos.CidadeFetchRequest;
import br.compasso.uol.backend.dtos.CidadeRetornoDto;
import br.compasso.uol.backend.models.Cidade;
import br.compasso.uol.backend.repositories.CidadeRepository;
import br.compasso.uol.backend.repositories.specifications.CidadeSpecification;
import br.compasso.uol.backend.services.CidadeService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Service
@Validated
public class CidadeServiceImpl implements CidadeService {

    private final CidadeRepository cidadeRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CidadeServiceImpl(CidadeRepository cidadeRepository, ModelMapper modelMapper) {
        this.cidadeRepository = cidadeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CidadeRetornoDto> buscarCidades(CidadeFetchRequest cidadeFetchRequest) {
        CidadeSpecification cidadeSpecification = new CidadeSpecification(cidadeFetchRequest);
        return modelMapper.map(cidadeRepository.findAll(cidadeSpecification), new TypeToken<List<CidadeRetornoDto>>() {}.getType());
    }

    @Override
    public CidadeRetornoDto salvarCidade(@Valid CidadeCreateRequest cidadeCreateRequest) {
        Cidade cidade = modelMapper.map(cidadeCreateRequest, Cidade.class);
        return modelMapper.map(cidadeRepository.save(cidade), CidadeRetornoDto.class);
    }
}
