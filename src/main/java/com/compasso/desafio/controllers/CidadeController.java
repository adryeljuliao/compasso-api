package com.compasso.desafio.controllers;

import com.compasso.desafio.mappers.CidadeMapper;
import com.compasso.desafio.models.Cidade;
import com.compasso.desafio.models.Cliente;
import com.compasso.desafio.models.dtos.CidadeDTO;
import com.compasso.desafio.services.CidadeService;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/cidades")
@CrossOrigin(origins = "*")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;


    @PostMapping
    @ApiOperation(value = "Salva uma cidade")
    public CidadeDTO salvar(@RequestBody @Valid CidadeDTO cidadeDTO) {
        cidadeDTO.setDataCadastro(LocalDate.now());
        cidadeDTO.setDataAtualizacao(LocalDate.now());

        Cidade novaCidade = CidadeMapper.cidadeDTOToCidade(cidadeDTO);

        CidadeDTO cidadeResponse = CidadeMapper.cidadeToCidadeDTO(
                cidadeService.criar(novaCidade)
        );
        return cidadeResponse;
    }

    @GetMapping("/nome/{nomeCidade}")
    @ApiOperation(value = "Retorna uma cidade pelo nome")
    public @ResponseBody
    CidadeDTO buscarCidadePorNome(@PathVariable String nomeCidade) {
        Cidade cidade = cidadeService.buscarCidadePorNome(nomeCidade);
        return CidadeMapper.cidadeToCidadeDTO(cidade);
    }

    @GetMapping("/estado/{estado}")
    @ApiOperation(value = "Retorna uma cidade pelo estado")
    public @ResponseBody
    List<Cidade> buscarCidadePorEstado(@PathVariable String estado) {
        List<Cidade> cidades = cidadeService.buscarCidadePorEstado(estado);
        return cidades;
    }

}
