package com.fuelsoft.apifuel.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.fuelsoft.apifuel.requests.RegistroUsuarioRequest;
import com.fuelsoft.apifuel.responses.UsuarioResponse;
import com.fuelsoft.apifuel.service.UsuarioService;
import com.fuelsoft.apifuel.controllers.UsuariosController;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/api/usuarios")

public class UsuariosController {
	private final UsuarioService service;

    public UsuariosController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponse registrar(@Valid @RequestBody RegistroUsuarioRequest req) {
        return service.crear(req);
    }
}
