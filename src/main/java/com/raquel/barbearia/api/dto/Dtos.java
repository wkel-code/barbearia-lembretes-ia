package com.raquel.barbearia.api.dto;

import com.raquel.barbearia.api.domain.Agendamento;
import com.raquel.barbearia.api.domain.Cliente;
import com.raquel.barbearia.api.domain.StatusAgendamento;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.OffsetDateTime;

public final class Dtos {

    private Dtos() {}

    public record ClienteRequest(
            @NotBlank @Size(max = 100) String nome,
            @NotBlank @Size(max = 20) String telefone) {}

    public record ClienteResponse(Long id, String nome, String telefone) {
        public static ClienteResponse de(Cliente c) {
            return new ClienteResponse(c.getId(), c.getNome(), c.getTelefone());
        }
    }

    public record AgendamentoRequest(
            @NotNull Long clienteId,
            @NotBlank @Size(max = 80) String servico,
            @Size(max = 80) String barbeiro,
            @NotNull @Future OffsetDateTime dataHora) {}

    public record AgendamentoResponse(Long id, Long clienteId, String servico,
                                      String barbeiro, OffsetDateTime dataHora,
                                      StatusAgendamento status) {
        public static AgendamentoResponse de(Agendamento a) {
            return new AgendamentoResponse(a.getId(), a.getCliente().getId(), a.getServico(),
                    a.getBarbeiro(), a.getDataHora(), a.getStatus());
        }
    }

    public record LembreteResponse(Long agendamentoId, String nomeCliente, String telefone,
                                   String servico, String barbeiro, OffsetDateTime dataHora) {
        public static LembreteResponse de(Agendamento a) {
            return new LembreteResponse(a.getId(), a.getCliente().getNome(),
                    a.getCliente().getTelefone(), a.getServico(), a.getBarbeiro(), a.getDataHora());
        }
    }

    public record StatusRequest(@NotNull StatusAgendamento status) {}
}