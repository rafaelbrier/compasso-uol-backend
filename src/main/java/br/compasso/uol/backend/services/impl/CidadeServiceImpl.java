package br.compasso.uol.backend.services.impl;

import br.compasso.uol.backend.dtos.CidadeCreateRequest;
import br.compasso.uol.backend.dtos.CidadeFetchRequest;
import br.compasso.uol.backend.exceptions.NotFoundException;
import br.compasso.uol.backend.models.Cidade;
import br.compasso.uol.backend.repositories.CidadeRepository;
import br.compasso.uol.backend.repositories.specifications.CidadeSpecification;
import br.compasso.uol.backend.services.CidadeService;
import br.compasso.uol.backend.utils.MessageUtils;
import org.modelmapper.ModelMapper;
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
    public Cidade buscarPorId(long cidadeId) throws NotFoundException {
        return cidadeRepository.findById(cidadeId)
                .orElseThrow(() -> new NotFoundException(MessageUtils.buscarMensagem("cidade.nao.encontrada")));
    }

    @Override
    public List<Cidade> buscarCidades(CidadeFetchRequest cidadeFetchRequest) {
        CidadeSpecification cidadeSpecification = new CidadeSpecification(cidadeFetchRequest);
        return cidadeRepository.findAll(cidadeSpecification);
    }

    @Override
    public Cidade salvarCidade(@Valid CidadeCreateRequest cidadeCreateRequest) {
        Cidade cidade = modelMapper.map(cidadeCreateRequest, Cidade.class);
        return cidadeRepository.save(cidade);
    }
}
