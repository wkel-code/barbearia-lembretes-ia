package com.raquel.barbearia.api.repository;

import com.raquel.barbearia.api.domain.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    @Query("""
        select a from Agendamento a join fetch a.cliente
        where a.status = com.raquel.barbearia.api.domain.StatusAgendamento.AGENDADO
          and a.lembreteEnviadoEm is null
          and a.dataHora between :agora and :limite
        """)
    List<Agendamento> buscarPendentes(@Param("agora") OffsetDateTime agora,
                                      @Param("limite") OffsetDateTime limite);
}