package com.raquel.barbearia.api.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "lembretes_log")
public class LembreteLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "agendamento_id", nullable = false)
    private Long agendamentoId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TipoLembrete tipo;

    @Column(columnDefinition = "text")
    private String mensagem;

    @Column(length = 50)
    private String modelo;

    private Integer tokens;

    @Column(name = "status_envio", length = 20)
    private String statusEnvio;

    @Column(columnDefinition = "text")
    private String erro;

    protected LembreteLog() {}

    public LembreteLog(Long agendamentoId, TipoLembrete tipo, String mensagem,
                       String modelo, Integer tokens, String statusEnvio, String erro) {
        this.agendamentoId = agendamentoId;
        this.tipo = tipo;
        this.mensagem = mensagem;
        this.modelo = modelo;
        this.tokens = tokens;
        this.statusEnvio = statusEnvio;
        this.erro = erro;
    }

    public Long getId() { return id; }
}
