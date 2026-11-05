package com.raquel.barbearia.api.controller;

import com.raquel.barbearia.api.domain.LembreteLog;
import com.raquel.barbearia.api.dto.Dtos.LogRequest;
import com.raquel.barbearia.api.repository.AgendamentoRepository;
import com.raquel.barbearia.api.repository.LembreteLogRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/lembretes-log")
public class LembreteLogController {

    private final LembreteLogRepository logs;
    private final AgendamentoRepository agendamentos;

    public LembreteLogController(LembreteLogRepository logs, AgendamentoRepository agendamentos) {
        this.logs = logs;
        this.agendamentos = agendamentos;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registrar(@Valid @RequestBody LogRequest req) {
        if (!agendamentos.existsById(req.agendamentoId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Agendamento não encontrado");
        }
        logs.save(new LembreteLog(req.agendamentoId(), req.tipo(), req.mensagem(),
                req.modelo(), req.tokens(), req.statusEnvio(), req.erro()));
    }
}