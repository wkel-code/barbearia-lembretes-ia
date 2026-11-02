CREATE TABLE clientes (
                          id BIGSERIAL PRIMARY KEY,
                          nome VARCHAR(100) NOT NULL,
                          telefone VARCHAR(20) NOT NULL
);

CREATE TABLE agendamentos (
                              id BIGSERIAL PRIMARY KEY,
                              cliente_id BIGINT NOT NULL REFERENCES clientes(id),
                              servico VARCHAR(80) NOT NULL,
                              barbeiro VARCHAR(80),
                              data_hora TIMESTAMPTZ NOT NULL,
                              status VARCHAR(20) NOT NULL DEFAULT 'AGENDADO',
                              lembrete_enviado_em TIMESTAMPTZ
);

CREATE TABLE lembretes_log (
                               id BIGSERIAL PRIMARY KEY,
                               agendamento_id BIGINT NOT NULL REFERENCES agendamentos(id),
                               mensagem TEXT,
                               modelo VARCHAR(50),
                               tokens INT,
                               status_envio VARCHAR(20),
                               erro TEXT,
                               criado_em TIMESTAMPTZ DEFAULT now()
);