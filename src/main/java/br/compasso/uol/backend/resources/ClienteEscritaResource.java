package br.compasso.uol.backend.resources;

import br.compasso.uol.backend.dtos.ClienteCreateRequest;
import br.compasso.uol.backend.dtos.ClienteRetornoDto;
import br.compasso.uol.backend.dtos.Response;
import br.compasso.uol.backend.models.Cliente;
import br.compasso.uol.backend.services.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(value = "ClienteEscritaResource", tags = {"Clientes, Escrita"})
@RestController
@RequestMapping(value = "/clientes")
public class ClienteEscritaResource {

    private final ClienteService clienteService;
    private final ModelMapper modelMapper;

    @Autowired
    public ClienteEscritaResource(ClienteService clienteService, ModelMapper modelMapper) {
        this.clienteService = clienteService;
        this.modelMapper = modelMapper;
    }

    @ApiOperation(
            value = "Insere/Cria um novo Cliente.",
            notes = "Recurso responsável por Inserir/Criar um novo Cliente."
    )
    @ApiResponses(
            value = {@ApiResponse(code = 201, message = "Cliente inserido/criado com sucesso."),
                    @ApiResponse(code = 400, message = "Erros de validação na requisição."),
                    @ApiResponse(code = 404, message = "Recursos necessários inválidos ou não encontrados.")})
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public Response<ClienteRetornoDto> criarCliente(@RequestBody ClienteCreateRequest clienteCreateRequest) {
        Cliente cliente = clienteService.salvarCliente(clienteCreateRequest);
        return Response.created(modelMapper.map(cliente, ClienteRetornoDto.class));
    }
}
