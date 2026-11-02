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

    @Column(nullable = false)
    private OffsetDateTime dataHora;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusAgendamento status = StatusAgendamento.AGENDADO;

    private OffsetDateTime lembreteEnviadoEm;

    protected Agendamento() {}

    public Agendamento(Cliente cliente, String servico, String barbeiro, OffsetDateTime dataHora) {
        this.cliente = cliente;
        this.servico = servico;
        this.barbeiro = barbeiro;
        this.dataHora = dataHora;
    }

    public void marcarLembreteEnviado() { this.lembreteEnviadoEm = OffsetDateTime.now(); }
    public void alterarStatus(StatusAgendamento novo) { this.status = novo; }

    public Long getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public String getServico() { return servico; }
    public String getBarbeiro() { return barbeiro; }
    public OffsetDateTime getDataHora() { return dataHora; }
    public StatusAgendamento getStatus() { return status; }
    public OffsetDateTime getLembreteEnviadoEm() { return lembreteEnviadoEm; }
}
