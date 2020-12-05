package br.compasso.uol.backend.services.impl;


import br.compasso.uol.backend.configs.ModelMapperConfig;
import br.compasso.uol.backend.dtos.ClienteCreateRequest;
import br.compasso.uol.backend.dtos.ClienteFetchRequest;
import br.compasso.uol.backend.dtos.ClienteNameChangeRequest;
import br.compasso.uol.backend.exceptions.GenericException;
import br.compasso.uol.backend.exceptions.NotFoundException;
import br.compasso.uol.backend.models.Cliente;
import br.compasso.uol.backend.repositories.ClienteRepository;
import br.compasso.uol.backend.services.CidadeService;
import br.compasso.uol.backend.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ClienteServiceTest {

    private static final String MOCK_FOLDER = "mocks/cliente";
    private static final String MOCK_OBJECT = "cliente.json";

    @MockBean
    private ClienteRepository clienteRepository;
    @MockBean
    private CidadeService cidadeService;
    private final ModelMapper mapper = new ModelMapperConfig().modelMapper();

    private ClienteServiceImpl service() {
        return new ClienteServiceImpl(clienteRepository, cidadeService, mapper);
    }

    /**
     * Retorna um Mock de {@link Cliente} completo e válido
     *
     * @return objeto {@link Cliente} completo e válido
     */
    protected static Cliente getMock() {
        return TestUtils.getMock(MOCK_FOLDER, MOCK_OBJECT, Cliente.class);
    }

    @Test
    public void testarBuscaPorId_RegistroExiste() {
        Cliente cliente = getMock();
        when(clienteRepository.findById(cliente.getId())).thenReturn(Optional.of(cliente));
        Cliente clienteBuscado = service().buscarPorId(cliente.getId());
        assertEquals(cliente.getId(), clienteBuscado.getId());
    }

    @Test
    public void testarBuscaPorId_RegistroNaoExiste() {
        Cliente cliente = getMock();
        when(clienteRepository.findById(cliente.getId())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> service().buscarPorId(cliente.getId()));
    }

    @Test
    public void testarBuscarClientes() {
        Cliente cliente = getMock();
        when(clienteRepository.findAll(Mockito.any())).thenReturn(Collections.singletonList(cliente));
        List<Cliente> clientes = service().buscarClientes(new ClienteFetchRequest());
        assertEquals(1, clientes.size());
        assertEquals(cliente.getId(), clientes.get(0).getId());
    }

    @Test
    public void testarSalvarCliente_CidadeExiste() {
        Cliente cliente = getMock();
        when(cidadeService.buscarPorId(Mockito.anyInt())).thenReturn(CidadeServiceTest.getMock());
        when(clienteRepository.save(Mockito.any())).thenReturn(cliente);
        Cliente clienteSalvo = service().salvarCliente(new ClienteCreateRequest());
        assertEquals(cliente.getId(), clienteSalvo.getId());
    }

    @Test
    public void testarSalvarCliente_CidadeNaoExiste() {
        Cliente cliente = getMock();
        when(cidadeService.buscarPorId(Mockito.anyLong())).thenThrow(NotFoundException.class);
        when(clienteRepository.save(Mockito.any())).thenReturn(cliente);
        assertThrows(NotFoundException.class, () -> service().salvarCliente(new ClienteCreateRequest()));
    }

    @Test
    public void testaAlterarNomeCliente_ClienteExiste() {
        ClienteNameChangeRequest clienteNameChangeRequest = new ClienteNameChangeRequest();
        clienteNameChangeRequest.setNomeCompleto("NOME_NOVO");
        Cliente cliente = getMock();
        when(clienteRepository.findById(cliente.getId())).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(Mockito.any())).thenReturn(cliente);
        Cliente clienteSalvo = service().alterarNomeCliente(cliente.getId(), clienteNameChangeRequest);
        assertEquals(clienteNameChangeRequest.getNomeCompleto(), clienteSalvo.getNomeCompleto());
    }

    @Test
    public void testaAlterarNomeCliente_ClienteNaoExiste() {
        Cliente cliente = getMock();
        when(clienteRepository.findById(cliente.getId())).thenReturn(Optional.empty());
        when(clienteRepository.save(Mockito.any())).thenReturn(cliente);
        assertThrows(NotFoundException.class, () -> service().alterarNomeCliente(cliente.getId(), new ClienteNameChangeRequest()));
    }

    @Test
    public void testaDeletarCliente_ClienteExiste() {
        Cliente cliente = getMock();
        service().deletarCliente(cliente.getId());
        verify(clienteRepository, Mockito.times(1)).deleteById(cliente.getId());
    }

    @Test
    public void testaDeletarCliente_ClienteNaoExiste() {
        Cliente cliente = getMock();
        doThrow(EmptyResultDataAccessException.class).when(clienteRepository).deleteById(cliente.getId());
        assertThrows(NotFoundException.class, () -> service().deletarCliente(cliente.getId()));
    }

    @Test
    public void testaDeletarCliente_ErroInterno() {
        Cliente cliente = getMock();
        doThrow(GenericException.class).when(clienteRepository).deleteById(cliente.getId());
        assertThrows(GenericException.class, () -> service().deletarCliente(cliente.getId()));
    }
}
