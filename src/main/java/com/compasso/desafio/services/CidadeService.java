package com.compasso.desafio.services;

import com.compasso.desafio.models.Cidade;
import com.compasso.desafio.models.Cliente;
import com.compasso.desafio.repositories.CidadeRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CidadeService {
    private static final Logger LOG = LoggerFactory.getLogger(Cliente.class);

    @Autowired
    private CidadeRepository cidadeRepository;

    public Cidade buscarCidadePorNome(String nome) {
        Optional<Cidade> cidade = cidadeRepository.findByNome(nome);
//        if (cidade.isEmpty()) {
////            Tratar e lançar uma exceção de notfound
//            throw new RuntimeException("Error 404");
//        }
        return cidade.orElse(null);
    }
    public List<Cidade> buscarCidadePorEstado(String estado) {
        List<Cidade> cidades = cidadeRepository.findAllByEstado(estado);
        return cidades;
    }

    public Cidade criar(Cidade novaCidade) {
        return cidadeRepository.save(novaCidade);
    }
}
