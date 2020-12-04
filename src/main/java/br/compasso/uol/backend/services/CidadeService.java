package br.compasso.uol.backend.services;

import br.compasso.uol.backend.dtos.CidadeCreateRequest;
import br.compasso.uol.backend.dtos.CidadeRetornoDto;

import javax.validation.Valid;

public interface CidadeService {

    /**
     * Insere/Cria uma nova Cidade.
     *
     * @param cidadeCreateRequest o objeto de requisição para criação de uma nova cidade
     * @return o objeto {@link CidadeRetornoDto} com as informações de retorno
     */
    CidadeRetornoDto salvarCidade(@Valid CidadeCreateRequest cidadeCreateRequest);
}
