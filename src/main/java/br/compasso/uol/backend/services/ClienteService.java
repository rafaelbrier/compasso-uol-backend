package br.compasso.uol.backend.services;

import br.compasso.uol.backend.dtos.ClienteCreateRequest;
import br.compasso.uol.backend.dtos.ClienteFetchRequest;
import br.compasso.uol.backend.dtos.ClienteNameChangeRequest;
import br.compasso.uol.backend.exceptions.GenericException;
import br.compasso.uol.backend.exceptions.NotFoundException;
import br.compasso.uol.backend.models.Cliente;

import javax.validation.Valid;
import java.util.List;

public interface ClienteService {

    /**
     * Busca um {@link Cliente} pelo seu identificador {@code clienteId}
     *
     * @param clienteId o identificador único do {@link Cliente}
     * @return a {@link Cliente} encontrada caso exista
     * @throws NotFoundException caso não exista uma {@link Cliente} com o id buscado
     */
    Cliente buscarPorId(long clienteId);

    /**
     * Busca uma lista de {@link Cliente} de acordo com os parâmetros do objeto {@link ClienteFetchRequest}
     *
     * @param clienteFetchRequest o objeto {@link ClienteFetchRequest} que contém os parâmetros de busca
     * @return uma lista de {@link Cliente}
     */
    List<Cliente> buscarClientes(ClienteFetchRequest clienteFetchRequest);

    /**
     * Insere/Cria um novo {@link Cliente}.
     *
     * @param clienteCreateRequest o objeto de requisição para criação de um novo {@link Cliente}
     * @return o objeto {@link Cliente} salvo
     */
    Cliente salvarCliente(@Valid ClienteCreateRequest clienteCreateRequest);

    /**
     * Altera o nome completo de um {@link Cliente}
     *
     * @param clienteId                o identificador único do {@link Cliente} que deseja-se alterar o nome completo
     * @param clienteNameChangeRequest o objeto de requisição para alterar o nome completo de um {@link Cliente}
     * @return o objeto {@link Cliente} salvo
     */
    Cliente alterarNomeCliente(long clienteId, @Valid ClienteNameChangeRequest clienteNameChangeRequest);

    /**
     * Deleta um cliente pelo seu identificador {@code clienteId}
     *
     * @param clienteId o identificador único do {@link Cliente}
     * @throws NotFoundException caso não exista um {@link Cliente} para deletar com o id informado
     * @throws GenericException  caso algum erro desconhecido ocorra no processo de deletar o cliente
     */
    void deletarCliente(long clienteId);

}
