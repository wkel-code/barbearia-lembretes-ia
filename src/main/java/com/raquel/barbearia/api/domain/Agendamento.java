package com.raquel.barbearia.api.domain;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "agendamentos")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(nullable = false, length = 80)
    private String servico;

    @Column(length = 80)
    private String barbeiro;

    @Column(name = "data_hora", nullable = false)
    private OffsetDateTime dataHora;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusAgendamento status = StatusAgendamento.AGENDADO;

    @Column(name = "confirmacao_enviada_em")
    private OffsetDateTime confirmacaoEnviadaEm;

    @Column(name = "lembrete_1h_enviado_em")
    private OffsetDateTime lembrete1hEnviadoEm;

    protected Agendamento() {}

    public Agendamento(Cliente cliente, String servico, String barbeiro, OffsetDateTime dataHora) {
        this.cliente = cliente;
        this.servico = servico;
        this.barbeiro = barbeiro;
        this.dataHora = dataHora;
    }

    public void marcarConfirmacaoEnviada() { this.confirmacaoEnviadaEm = OffsetDateTime.now(); }
    public void marcarLembrete1hEnviado() { this.lembrete1hEnviadoEm = OffsetDateTime.now(); }
    public void alterarStatus(StatusAgendamento novo) { this.status = novo; }

    public Long getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public String getServico() { return servico; }
    public String getBarbeiro() { return barbeiro; }
    public OffsetDateTime getDataHora() { return dataHora; }
    public StatusAgendamento getStatus() { return status; }
    public OffsetDateTime getConfirmacaoEnviadaEm() { return confirmacaoEnviadaEm; }
    public OffsetDateTime getLembrete1hEnviadoEm() { return lembrete1hEnviadoEm; }
}