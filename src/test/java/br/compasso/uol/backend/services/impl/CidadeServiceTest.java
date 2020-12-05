package br.compasso.uol.backend.services.impl;


import br.compasso.uol.backend.configs.ModelMapperConfig;
import br.compasso.uol.backend.dtos.CidadeCreateRequest;
import br.compasso.uol.backend.dtos.CidadeFetchRequest;
import br.compasso.uol.backend.exceptions.NotFoundException;
import br.compasso.uol.backend.models.Cidade;
import br.compasso.uol.backend.repositories.CidadeRepository;
import br.compasso.uol.backend.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CidadeServiceTest {

    private static final String MOCK_FOLDER = "mocks/cidade";
    private static final String MOCK_OBJECT = "cidade.json";

    @MockBean
    private CidadeRepository cidadeRepository;
    private final ModelMapper mapper = new ModelMapperConfig().modelMapper();

    private CidadeServiceImpl service() {
        return new CidadeServiceImpl(cidadeRepository, mapper);
    }

    /**
     * Retorna um Mock de {@link Cidade} completo e válido
     * @return objeto {@link Cidade} completo e válido
     */
    protected static Cidade getMock() {
        return TestUtils.getMock(MOCK_FOLDER, MOCK_OBJECT, Cidade.class);
    }

    @Test
    public void testarBuscaPorId_RegistroExiste() {
        Cidade cidade = getMock();
        when(cidadeRepository.findById(cidade.getId())).thenReturn(Optional.of(cidade));
        Cidade cidadeBuscada = service().buscarPorId(cidade.getId());
        assertEquals(cidade.getId(), cidadeBuscada.getId());
    }

    @Test
    public void testarBuscaPorId_RegistroNaoExiste() {
        Cidade cidade = getMock();
        when(cidadeRepository.findById(cidade.getId())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> service().buscarPorId(cidade.getId()));
    }

    @Test
    public void testarBuscarCidades() {
        Cidade cidade = getMock();
        when(cidadeRepository.findAll(Mockito.any())).thenReturn(Collections.singletonList(cidade));
        List<Cidade> cidades = service().buscarCidades(new CidadeFetchRequest());
        assertEquals(1, cidades.size());
        assertEquals(cidade.getId(), cidades.get(0).getId());
    }

    @Test
    public void testarSalvarCidade() {
        Cidade cidade = getMock();
        when(cidadeRepository.save(Mockito.any())).thenReturn(cidade);
        Cidade cidadeSalva = service().salvarCidade(new CidadeCreateRequest());
        assertEquals(cidade.getId(), cidadeSalva.getId());
    }

}
