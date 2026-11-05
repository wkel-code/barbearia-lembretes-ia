package com.raquel.barbearia.api.service;

import com.raquel.barbearia.api.domain.Agendamento;
import com.raquel.barbearia.api.domain.Cliente;
import com.raquel.barbearia.api.domain.StatusAgendamento;
import com.raquel.barbearia.api.dto.Dtos.AgendamentoRequest;
import com.raquel.barbearia.api.dto.Dtos.AgendamentoResponse;
import com.raquel.barbearia.api.dto.Dtos.LembreteResponse;
import com.raquel.barbearia.api.repository.AgendamentoRepository;
import com.raquel.barbearia.api.repository.ClienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class AgendamentoService {

    private static final ZoneId FUSO = ZoneId.of("America/Sao_Paulo");

    private final AgendamentoRepository agendamentos;
    private final ClienteRepository clientes;

    public AgendamentoService(AgendamentoRepository agendamentos, ClienteRepository clientes) {
        this.agendamentos = agendamentos;
        this.clientes = clientes;
    }

    @Transactional
    public AgendamentoResponse criar(AgendamentoRequest req) {
        Cliente cliente = clientes.findById(req.clienteId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
        Agendamento salvo = agendamentos.save(
                new Agendamento(cliente, req.servico(), req.barbeiro(), req.dataHora()));
        return AgendamentoResponse.de(salvo);
    }

    @Transactional(readOnly = true)
    public List<LembreteResponse> pendentesDeConfirmacao() {
        LocalDate amanha = LocalDate.now(FUSO).plusDays(1);
        OffsetDateTime inicio = amanha.atStartOfDay(FUSO).toOffsetDateTime();
        OffsetDateTime fim = amanha.plusDays(1).atStartOfDay(FUSO).toOffsetDateTime();
        return agendamentos.buscarParaConfirmacao(inicio, fim)
                .stream().map(LembreteResponse::de).toList();
    }

    @Transactional(readOnly = true)
    public List<LembreteResponse> pendentesDeLembrete1h() {
        OffsetDateTime agora = OffsetDateTime.now();
        return agendamentos.buscarParaLembrete1h(
                        agora.plusMinutes(45), agora.plusMinutes(60),
                        List.of(StatusAgendamento.AGENDADO, StatusAgendamento.CONFIRMADO))
                .stream().map(LembreteResponse::de).toList();
    }

    @Transactional
    public void marcarConfirmacaoEnviada(Long id) {
        buscar(id).marcarConfirmacaoEnviada();
    }

    @Transactional
    public void marcarLembrete1hEnviado(Long id) {
        buscar(id).marcarLembrete1hEnviado();
    }

    @Transactional
    public AgendamentoResponse alterarStatus(Long id, StatusAgendamento status) {
        Agendamento a = buscar(id);
        a.alterarStatus(status);
        return AgendamentoResponse.de(a);
    }

    private Agendamento buscar(Long id) {
        return agendamentos.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Agendamento não encontrado"));
    }
}