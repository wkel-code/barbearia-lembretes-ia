package com.raquel.barbearia.api.controller;

import com.raquel.barbearia.api.domain.Cliente;
import com.raquel.barbearia.api.dto.Dtos.ClienteRequest;
import com.raquel.barbearia.api.dto.Dtos.ClienteResponse;
import com.raquel.barbearia.api.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteRepository clientes;

    public ClienteController(ClienteRepository clientes) {
        this.clientes = clientes;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponse criar(@Valid @RequestBody ClienteRequest req) {
        return ClienteResponse.de(clientes.save(new Cliente(req.nome(), req.telefone())));
    }
}