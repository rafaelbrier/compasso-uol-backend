package br.compasso.uol.backend.resources;

import br.compasso.uol.backend.dtos.CidadeFetchRequest;
import br.compasso.uol.backend.dtos.CidadeRetornoDto;
import br.compasso.uol.backend.dtos.Response;
import br.compasso.uol.backend.services.CidadeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "CidadeLeituraResource", tags = {"Cidade, Leitura"})
@RestController
@RequestMapping(value = "/cidades")
public class CidadeLeituraResource {

    private final CidadeService cidadeService;

    @Autowired
    public CidadeLeituraResource(CidadeService cidadeService) {
        this.cidadeService = cidadeService;
    }

    @ApiOperation(
            value = "Busca uma lista de Cidades.",
            notes = "Recurso responsável por buscar uma lista de Cidades atendendo os parâmetros da requisição."
    )
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Cidades buscadas com sucesso.")})
    @GetMapping
    public Response<List<CidadeRetornoDto>> buscarListaCidade(CidadeFetchRequest cidadeFetchRequest) {
        return Response.created(cidadeService.buscarCidades(cidadeFetchRequest));
    }
}
