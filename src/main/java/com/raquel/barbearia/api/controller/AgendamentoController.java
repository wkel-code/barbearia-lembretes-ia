package com.raquel.barbearia.api.controller;

import com.raquel.barbearia.api.dto.Dtos.AgendamentoRequest;
import com.raquel.barbearia.api.dto.Dtos.AgendamentoResponse;
import com.raquel.barbearia.api.dto.Dtos.LembreteResponse;
import com.raquel.barbearia.api.dto.Dtos.StatusRequest;
import com.raquel.barbearia.api.service.AgendamentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    private final AgendamentoService service;

    public AgendamentoController(AgendamentoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AgendamentoResponse criar(@Valid @RequestBody AgendamentoRequest req) {
        return service.criar(req);
    }

    @GetMapping("/pendentes-lembrete")
    public List<LembreteResponse> pendentes() {
        return service.pendentesDeLembrete();
    }

    @PatchMapping("/{id}/lembrete-enviado")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void lembreteEnviado(@PathVariable Long id) {
        service.marcarLembreteEnviado(id);
    }

    @PatchMapping("/{id}/status")
    public AgendamentoResponse status(@PathVariable Long id, @Valid @RequestBody StatusRequest req) {
        return service.alterarStatus(id, req.status());
    }
}