package com.compasso.desafio.mappers;

import com.compasso.desafio.models.Cidade;
import com.compasso.desafio.models.Cliente;
import com.compasso.desafio.models.dtos.CidadeDTO;
import com.compasso.desafio.models.dtos.ClienteDTO;

import java.time.LocalDate;

public final class CidadeMapper {

    public static Cidade cidadeDTOToCidade(CidadeDTO cidadeDTO) {
        if (cidadeDTO != null) {
            Cidade cidade = Cidade.builder()
                    .nome(cidadeDTO.getNome())
                    .estado(cidadeDTO.getEstado())
                    .dataAtualizacao(LocalDate.now())
                    .dataCadastro(cidadeDTO.getDataCadastro())
                    .build();
            return cidade;
        }
        return null;
    }

    public static CidadeDTO cidadeToCidadeDTO(Cidade cidade) {
       if (cidade != null) {
           CidadeDTO cidadeDTO = CidadeDTO.builder()
                   .id(cidade.getId())
                   .nome(cidade.getNome())
                   .estado(cidade.getEstado())
                   .dataAtualizacao(cidade.getDataAtualizacao())
                   .dataCadastro(cidade.getDataCadastro())
                   .build();
           return cidadeDTO;
       }
       return null;
    }
}
