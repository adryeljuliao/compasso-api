package com.compasso.desafio.models.dtos;

import lombok.*;
import org.apache.tomcat.jni.Local;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CidadeDTO {

    private Integer id;
    private String nome;
    private String estado;
    private LocalDate dataCadastro;
    private LocalDate dataAtualizacao;

}
