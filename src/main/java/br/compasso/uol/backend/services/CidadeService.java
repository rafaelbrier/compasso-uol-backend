package br.compasso.uol.backend.services;

import br.compasso.uol.backend.dtos.CidadeCreateRequest;
import br.compasso.uol.backend.dtos.CidadeFetchRequest;
import br.compasso.uol.backend.exceptions.NotFoundException;
import br.compasso.uol.backend.models.Cidade;

import javax.validation.Valid;
import java.util.List;

public interface CidadeService {

    /**
     * Busca uma cidade pelo seu identificador {@code cidadeId}
     *
     * @param cidadeId o identificador único da Cidade
     * @return a {@link Cidade} encontrada caso exista
     * @throws NotFoundException caso não exista uma {@link Cidade} com o id buscado
     */
    Cidade buscarPorId(long cidadeId);

    /**
     * Busca uma lista de cidade de acordo com os parâmetros do objeto {@link CidadeFetchRequest}
     *
     * @param cidadeFetchRequest o objeto {@link CidadeFetchRequest} que contém os parâmetros de busca
     * @return uma lista de {@link Cidade}
     */
    List<Cidade> buscarCidades(CidadeFetchRequest cidadeFetchRequest);

    /**
     * Insere/Cria uma nova Cidade.
     *
     * @param cidadeCreateRequest o objeto de requisição para criação de uma nova cidade
     * @return o objeto {@link Cidade} salvo
     */
    Cidade salvarCidade(@Valid CidadeCreateRequest cidadeCreateRequest);
}
