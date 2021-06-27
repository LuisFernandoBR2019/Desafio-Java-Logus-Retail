DROP DATABASE logus_retail;

CREATE DATABASE logus_retail WITH ENCODING 'UTF-8';

\c logus_retail;

CREATE TABLE medico (
	id_medico BIGSERIAL PRIMARY KEY,
	nome_medico TEXT NOT NULL,
	especialidade_medico TEXT NOT NULL,
	crm_medico TEXT NOT NULL,
	idade_medico BIGINT NOT NULL
);

CREATE TABLE paciente (
	id_paciente BIGSERIAL PRIMARY KEY,
	nome_paciente TEXT NOT NULL
);

CREATE TABLE consultorio (
	id_consultorio BIGSERIAL PRIMARY KEY,
	numero_consultorio BIGINT NOT NULL
);

CREATE TABLE consulta (
	id_consulta BIGSERIAL PRIMARY KEY,
	id_paciente_fk BIGINT REFERENCES paciente(id_paciente) ON UPDATE CASCADE ON DELETE CASCADE NOT NULL,
	id_consultorio_fk BIGINT REFERENCES consultorio(id_consultorio) ON UPDATE CASCADE ON DELETE CASCADE NOT NULL,
	datahora_consulta TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

CREATE TABLE consulta_medico(
	id_consulta_fk  BIGINT REFERENCES consulta(id_consulta) ON UPDATE CASCADE ON DELETE CASCADE NOT NULL,
	id_medico_fk BIGINT REFERENCES medico(id_medico) ON UPDATE CASCADE ON DELETE CASCADE NOT NULL
);