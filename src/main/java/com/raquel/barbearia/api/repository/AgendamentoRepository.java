package com.raquel.barbearia.api.repository;

import com.raquel.barbearia.api.domain.Agendamento;
import com.raquel.barbearia.api.domain.StatusAgendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    @Query("""
        select a from Agendamento a join fetch a.cliente
        where a.status = com.raquel.barbearia.api.domain.StatusAgendamento.AGENDADO
          and a.confirmacaoEnviadaEm is null
          and a.dataHora >= :inicio and a.dataHora < :fim
        """)
    List<Agendamento> buscarParaConfirmacao(@Param("inicio") OffsetDateTime inicio,
                                            @Param("fim") OffsetDateTime fim);

    @Query("""
        select a from Agendamento a join fetch a.cliente
        where a.status in (:statuses)
          and a.lembrete1hEnviadoEm is null
          and a.dataHora > :de and a.dataHora <= :ate
        """)
    List<Agendamento> buscarParaLembrete1h(@Param("de") OffsetDateTime de,
                                           @Param("ate") OffsetDateTime ate,
                                           @Param("statuses") Collection<StatusAgendamento> statuses);
}