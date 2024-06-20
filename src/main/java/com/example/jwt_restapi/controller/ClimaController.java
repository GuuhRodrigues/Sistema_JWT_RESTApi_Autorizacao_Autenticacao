package com.example.jwt_restapi.controller;

import com.example.jwt_restapi.service.ClimaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClimaController {

    //Classe de serviços
    @Autowired
    private ClimaService service;

    @Secured("USER")
    @GetMapping("/clima")
    public String preverTempo(){
        return service.preverTempo();
    }
}

