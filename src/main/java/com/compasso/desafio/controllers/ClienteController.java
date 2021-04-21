package com.compasso.desafio.controllers;

import com.compasso.desafio.models.Cliente;
import com.compasso.desafio.mappers.ClienteMapper;
import com.compasso.desafio.models.dtos.ClienteDTO;
import com.compasso.desafio.services.ClienteService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/nome/{nome}")
    @ApiOperation(value = "Retorna um cliente através do nome")
    public @ResponseBody
    ClienteDTO buscarClientePorNome(@PathVariable String nome) {
        return ClienteMapper.clienteToClienteDTO(clienteService.buscarClientePorNome(nome));
    }

    @GetMapping("/id/{id}")
    @ApiOperation(value = "Retorna um cliente através do id")
    public @ResponseBody
    ClienteDTO buscarClientePorId(@PathVariable Integer id) {
        return ClienteMapper.clienteToClienteDTO(clienteService.buscarClientePorId(id));
    }

    @PostMapping
    @ApiOperation(value = "Salva um cliente")
    public ClienteDTO salvar(@RequestBody @Valid ClienteDTO clienteDTO) {
        clienteDTO.setDataCadastro(LocalDate.now());
        clienteDTO.setDataAtualizacao(LocalDate.now());

        Cliente novoCliente = ClienteMapper.clienteDTOToCliente(clienteDTO);

        ClienteDTO clienteResponse = ClienteMapper.clienteToClienteDTO(
                clienteService.criar(novoCliente)
        );
        return clienteResponse;
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Altera os dados um cliente")
    public ClienteDTO alterar(@RequestBody @Valid ClienteDTO clienteDTO, @PathVariable Integer id) {
        clienteDTO.setDataAtualizacao(LocalDate.now());

        Cliente novoCliente = ClienteMapper.clienteDTOToCliente(clienteDTO);

        ClienteDTO clienteResponse = ClienteMapper.clienteToClienteDTO(
                clienteService.alterar(novoCliente, id)
        );
        return clienteResponse;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Remove um cliente")
    public void remover(@PathVariable Integer id) {
        clienteService.remover(id);
    }
}
