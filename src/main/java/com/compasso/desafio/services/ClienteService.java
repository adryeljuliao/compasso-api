package com.compasso.desafio.services;

import com.compasso.desafio.models.Cidade;
import com.compasso.desafio.models.Cliente;
import com.compasso.desafio.repositories.CidadeRepository;
import com.compasso.desafio.repositories.ClienteRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ClienteService {
    private static final Logger LOG = LoggerFactory.getLogger(Cliente.class);

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CidadeService cidadeService;

    public List<Cliente> buscarTodosClientes() {
        return clienteRepository.findAll();
    }

    public Cliente criar(@Valid Cliente novoCliente) {
        try {
            if (novoCliente.getCidade() != null) {
                Cidade cidade = buscarCidadePorNome(novoCliente.getCidade().getNome());
                novoCliente.setCidade(cidade);
            }
            return clienteRepository.save(novoCliente);
        } catch (RuntimeException e) {
            LOG.error("Ocorreu um erro ao salvar os dados no banco --------------------- " + e);
            throw new RuntimeException("Error ao cadastrar dados");
        }
    }



    public Cliente alterar(@Valid Cliente novoCliente, Integer id) {
        try {
            Cliente clientePersistido = buscarClientePorId(id);
            verificaExisteCliente(clientePersistido);
            atualizarDados(clientePersistido, novoCliente);
            return clienteRepository.save(clientePersistido);
        } catch (RuntimeException e) {
            LOG.error("Ocorreu um erro ao salvar os dados no banco --------------------- " + e);
            throw new RuntimeException("Error ao salvar dados");
        }
    }

    public void remover(Integer id) {
        try {
            Cliente clientePersistido = buscarClientePorId(id);
            verificaExisteCliente(clientePersistido);
            clienteRepository.delete(clientePersistido);
        } catch (RuntimeException e) {
            LOG.error("Ocorreu um erro ao remover os dados no banco --------------------- " + e);
            throw new RuntimeException("Error ao remover dados");
        }
    }

    public Cliente buscarClientePorId(Integer id) {
        try {
            Optional<Cliente> cliente = clienteRepository.findById(id);
            return cliente.orElse(null);
        } catch (RuntimeException e) {
            LOG.error("Erro ao consultar dados ----------------------------- ", e);
            throw new RuntimeException("error", e);
        }
    }

    public Cliente buscarClientePorNome(String nome) {
        try {
            Optional<Cliente> cliente = clienteRepository.findByNome(nome);
            return cliente.orElse(null);
        } catch (RuntimeException e) {
            LOG.error("Erro ao consultar dados ----------------------------- ", e);
            throw new RuntimeException("error", e);
        }
    }

    private void verificaExisteCliente(Cliente cliente) {
        if (cliente == null) {
            throw new RuntimeException("Cliente não existe no banco de dados --------------------- ");
        }
    }

    private Cidade buscarCidadePorNome(String nomeCidade) {
        return cidadeService.buscarCidadePorNome(nomeCidade);
    }

    private void atualizarDados(Cliente clientePersistido, Cliente novoCliente) {
        clientePersistido.setNome(novoCliente.getNome());
        clientePersistido.setDataAtualizacao(LocalDate.now());
    }

}
