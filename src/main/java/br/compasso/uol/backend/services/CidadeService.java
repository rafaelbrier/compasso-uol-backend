package br.compasso.uol.backend.services;

import br.compasso.uol.backend.dtos.CidadeCreateRequest;
import br.compasso.uol.backend.dtos.CidadeFetchRequest;
import br.compasso.uol.backend.dtos.CidadeRetornoDto;

import javax.validation.Valid;
import java.util.List;

public interface CidadeService {

    /**
     * Busca uma lista de cidade de acordo com os parâmetros do objeto {@link CidadeFetchRequest}
     *
     * @param cidadeFetchRequest o objeto {@link CidadeFetchRequest} que contém os parâmetros de busca
     * @return uma lista de {@link CidadeRetornoDto}
     */
    List<CidadeRetornoDto> buscarCidades(CidadeFetchRequest cidadeFetchRequest);

    /**
     * Insere/Cria uma nova Cidade.
     *
     * @param cidadeCreateRequest o objeto de requisição para criação de uma nova cidade
     * @return o objeto {@link CidadeRetornoDto} com as informações de retorno
     */
    CidadeRetornoDto salvarCidade(@Valid CidadeCreateRequest cidadeCreateRequest);
}
