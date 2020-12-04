package br.compasso.uol.backend.resources;

import br.compasso.uol.backend.dtos.CidadeCreateRequest;
import br.compasso.uol.backend.dtos.CidadeRetornoDto;
import br.compasso.uol.backend.dtos.Response;
import br.compasso.uol.backend.services.CidadeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(value = "CidadeEscritaResource", tags = {"Cidade, Escrita"})
@RestController
@RequestMapping(value = "/cidades")
public class CidadeEscritaResource {

    private final CidadeService cidadeService;

    @Autowired
    public CidadeEscritaResource(CidadeService cidadeService) {
        this.cidadeService = cidadeService;
    }

    @ApiOperation(
            value = "Insere/Cria uma nova Cidade.",
            notes = "Recurso responsável por Inserir/Criar uma nova Cidade."
    )
    @ApiResponses(
            value = {@ApiResponse(code = 201, message = "Cidade inserida/criada com sucesso."),
                    @ApiResponse(code = 400, message = "Erros de validação na requisição")})
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public Response<CidadeRetornoDto> criarCidade(@RequestBody CidadeCreateRequest cidadeCreateRequest) {
        return Response.created(cidadeService.salvarCidade(cidadeCreateRequest));
    }
}
