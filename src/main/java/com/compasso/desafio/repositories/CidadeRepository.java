package com.compasso.desafio.repositories;

import com.compasso.desafio.models.Cidade;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CidadeRepository extends CrudRepository<Cidade, Integer> {

    Optional<Cidade> findByNome(String nome);
    Optional<Cidade> findByEstado(String estado);
    List<Cidade> findAllByEstado(String estado);
}
