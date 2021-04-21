package com.compasso.desafio.models.dtos;

import com.compasso.desafio.helpers.Constants;
import com.compasso.desafio.models.Cliente;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ClienteDTO {

    private Integer id;
    private String nome;
    private String sexo;
    private Date dataNacimento;
    private Integer idade;
    private LocalDate dataCadastro;
    private LocalDate dataAtualizacao;
    private String nomeCidade;

}
