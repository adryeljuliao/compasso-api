package com.compasso.desafio.mappers;

import com.compasso.desafio.models.Cidade;
import com.compasso.desafio.models.Cliente;
import com.compasso.desafio.models.dtos.ClienteDTO;

import java.time.LocalDate;

public final class ClienteMapper {

    public static Cliente clienteDTOToCliente(ClienteDTO clienteDTO) {
        if (clienteDTO != null) {
            Cidade cidade = Cidade.builder().nome(clienteDTO.getNomeCidade()).build();

            Cliente cliente = Cliente.builder()
                    .nome(clienteDTO.getNome())
                    .sexo(clienteDTO.getSexo())
                    .dataNacimento(clienteDTO.getDataNacimento())
                    .dataAtualizacao(LocalDate.now())
                    .cidade(cidade)
                    .idade(clienteDTO.getIdade())
                    .dataCadastro(clienteDTO.getDataCadastro())
                    .build();
            return cliente;
        }
        return null;
    }

    public static ClienteDTO clienteToClienteDTO(Cliente cliente) {
       if (cliente != null) {
           String nomeCidade = null;
           if (cliente.getCidade() != null) {
               nomeCidade = cliente.getCidade().getNome();
           }
           ClienteDTO clienteDTO = ClienteDTO.builder()
                   .id(cliente.getId())
                   .nome(cliente.getNome())
                   .sexo(cliente.getSexo())
                   .dataNacimento(cliente.getDataNacimento())
                   .idade(cliente.getIdade())
                   .nomeCidade(nomeCidade)
                   .dataAtualizacao(cliente.getDataAtualizacao())
                   .dataCadastro(cliente.getDataCadastro())
                   .build();
           return clienteDTO;
       }
       return null;
    }
}
