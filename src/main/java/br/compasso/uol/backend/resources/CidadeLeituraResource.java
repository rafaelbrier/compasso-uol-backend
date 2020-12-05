package br.compasso.uol.backend.resources;

import br.compasso.uol.backend.dtos.CidadeFetchRequest;
import br.compasso.uol.backend.dtos.CidadeRetornoDto;
import br.compasso.uol.backend.dtos.Response;
import br.compasso.uol.backend.models.Cidade;
import br.compasso.uol.backend.services.CidadeService;
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

@Api(value = "CidadeLeituraResource", tags = {"Cidades, Leitura"})
@RestController
@RequestMapping(value = "/cidades")
public class CidadeLeituraResource {

    private final CidadeService cidadeService;
    private final ModelMapper modelMapper;

    @Autowired
    public CidadeLeituraResource(CidadeService cidadeService, ModelMapper modelMapper) {
        this.cidadeService = cidadeService;
        this.modelMapper = modelMapper;
    }

    @ApiOperation(
            value = "Busca uma lista de Cidades.",
            notes = "Recurso responsável por buscar uma lista de Cidades atendendo os parâmetros da requisição."
    )
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Cidades buscadas com sucesso.")})
    @GetMapping
    public Response<List<CidadeRetornoDto>> buscarListaCidade(CidadeFetchRequest cidadeFetchRequest) {
        List<Cidade> cidades = cidadeService.buscarCidades(cidadeFetchRequest);
        return Response.ok(modelMapper.map(cidades, new TypeToken<List<CidadeRetornoDto>>() {}.getType()));
    }
}
