package com.compasso.desafio.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/sources")
@CrossOrigin(origins = "*")
public class SourceController {

    @GetMapping
    public String buscarCodigoFonte() {
        return "https://github.com/adryeljuliao/compasso-api";
    }

}
