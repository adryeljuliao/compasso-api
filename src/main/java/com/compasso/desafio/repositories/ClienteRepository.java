package com.compasso.desafio.repositories;

import com.compasso.desafio.models.Cliente;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface ClienteRepository extends CrudRepository<Cliente, Integer> {

    List<Cliente> findAll();
    Optional<Cliente> findByNome(String nome);
}
