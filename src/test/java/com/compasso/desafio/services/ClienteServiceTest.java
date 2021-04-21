package com.compasso.desafio.services;

import com.compasso.desafio.models.Cidade;
import com.compasso.desafio.models.Cliente;
import com.compasso.desafio.repositories.CidadeRepository;
import com.compasso.desafio.repositories.ClienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    private ClienteService clienteService;
    @Mock
    private ClienteRepository clienteRepository;
    @Mock
    private CidadeService cidadeService;

    @BeforeEach
    public void initService() {
        clienteService = new ClienteService(
                clienteRepository,
                cidadeService
        );
    }

    @Test
    public void deveCriarClienteComSucesso() {
        Cliente mockCliente = mockGerarCliente();
        mockCliente.setId(1);
        mockCliente.setCidade(mockGerarCidade());
        Mockito.when(cidadeService.buscarCidadePorNome(Mockito.any(String.class))).thenReturn(mockGerarCidade());
        Mockito.when(clienteRepository.save(Mockito.any(Cliente.class))).thenReturn(mockCliente);

        Cliente clientePersistida = clienteService.criar(mockCliente);
        Assertions.assertNotNull(clientePersistida);
    }

    @Test
    public void deveBuscarTodosClientesComSucesso() {
        List<Cliente> mockListaClientes = mockGerarListaClientes();
        Mockito.when(clienteRepository.findAll()).thenReturn(mockListaClientes);

        var listaClientesResponse = clienteService.buscarTodosClientes();
        Assertions.assertNotNull(listaClientesResponse);
        Assertions.assertFalse(listaClientesResponse.isEmpty());
    }

    @Test
    public void deveAlterarClienteComSucesso() {
        Cliente mockCliente = mockGerarCliente();
        mockCliente.setId(1);
        Mockito.when(clienteRepository.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(mockCliente));
        Mockito.when(clienteRepository.save(Mockito.any(Cliente.class))).thenReturn(mockCliente);

        Cliente clientePersistida = clienteService.alterar(mockCliente, 1);
        Assertions.assertNotNull(clientePersistida);
    }

    @Test
    public void deveRemoverClienteComSucesso() {
        Cliente mockCliente = mockGerarCliente();
        mockCliente.setId(1);
        Mockito.when(clienteRepository.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(mockCliente));
        clienteService.remover(1);
        Mockito.verify(clienteRepository, Mockito.times(1))
                .delete(Mockito.eq(mockCliente));
    }


    @Test
    public void deveCapturarExcecaoAoBuscarPorId() {
        try {
            Cliente mockCliente = mockGerarCliente();
            mockCliente.setId(1);
            Mockito.when(clienteRepository.findById(Mockito.any(Integer.class))).thenReturn(null);
            clienteService.alterar(mockCliente, 1);
            Assertions.fail();
        } catch (RuntimeException e) {
            Assertions.assertEquals(e.getMessage(), "Error ao salvar dados");
        }
    }

    @Test
    public void deveFalharAoAlterarDadosCliente() {
        try {
            Cliente mockCliente = mockGerarCliente();
            mockCliente.setId(1);
            Mockito.when(clienteRepository.findById(Mockito.any(Integer.class))).thenReturn(Optional.empty());
            clienteService.alterar(mockCliente, 1);
            Assertions.fail();
        } catch (RuntimeException e) {
            Assertions.assertEquals(e.getMessage(), "Error ao salvar dados");
        }
    }

    @Test
    public void deveFalharAoRemoverDadosCliente() {
        try {
            Cliente mockCliente = mockGerarCliente();
            mockCliente.setId(1);
            clienteService.remover(1);
            Assertions.fail();
        } catch (RuntimeException e) {
            Assertions.assertEquals(e.getMessage(), "Error ao remover dados");
            Mockito.verify(clienteRepository, Mockito.times(0))
                    .delete(Mockito.any(Cliente.class));
        }
    }

    private Cliente mockGerarCliente() {
        Cliente cliente = Cliente.builder()
                .nome("Julião")
                .dataCadastro(LocalDate.now())
                .dataAtualizacao(LocalDate.now())
                .dataNacimento(new Date(1997, Calendar.SEPTEMBER, 23))
                .sexo("M")
                .build();
        return cliente;
    }

    private Cidade mockGerarCidade() {
        Cidade cidade = Cidade.builder()
                .nome("Maraba")
                .dataCadastro(LocalDate.now())
                .dataAtualizacao(LocalDate.now())
                .build();
        return cidade;
    }

    private List<Cliente> mockGerarListaClientes() {
        var primeiraCliente = mockGerarCliente();
        var segundaCliente = mockGerarCliente();

        primeiraCliente.setNome("Fernanda Batista");
        primeiraCliente.setSexo("F");

        return Arrays.asList(primeiraCliente, segundaCliente);
    }


}