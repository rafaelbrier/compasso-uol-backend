package br.compasso.uol.backend.resources;

import br.compasso.uol.backend.dtos.ClienteFetchRequest;
import br.compasso.uol.backend.dtos.ClienteRetornoDto;
import br.compasso.uol.backend.dtos.Response;
import br.compasso.uol.backend.models.Cliente;
import br.compasso.uol.backend.services.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "ClienteLeituraResource", tags = {"Clientes, Leitura"})
@RestController
@RequestMapping(value = "/clientes")
public class ClienteLeituraResource {

    private final ClienteService clienteService;
    private final ModelMapper modelMapper;

    @Autowired
    public ClienteLeituraResource(ClienteService clienteService, ModelMapper modelMapper) {
        this.clienteService = clienteService;
        this.modelMapper = modelMapper;
    }

    @ApiOperation(
            value = "Busca uma lista de Clientes.",
            notes = "Recurso responsável por buscar uma lista de Clientes atendendo os parâmetros da requisição."
    )
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Clientes buscados com sucesso.")})
    @GetMapping
    public Response<List<ClienteRetornoDto>> buscarListaCidade(ClienteFetchRequest clienteFetchRequest) {
        List<Cliente> clientes = clienteService.buscarClientes(clienteFetchRequest);
        return Response.ok(modelMapper.map(clientes, new TypeToken<List<ClienteRetornoDto>>() {
        }.getType()));
    }
}
