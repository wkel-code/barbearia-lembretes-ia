ALTER TABLE agendamentos RENAME COLUMN lembrete_enviado_em TO confirmacao_enviada_em;
ALTER TABLE agendamentos ADD COLUMN lembrete_1h_enviado_em TIMESTAMPTZ;
ALTER TABLE lembretes_log ADD COLUMN tipo VARCHAR(20) NOT NULL DEFAULT 'CONFIRMACAO';