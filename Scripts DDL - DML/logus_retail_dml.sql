\c logus_retail;

INSERT INTO paciente (nome_paciente) VALUES ('Luis Fernando da Silva');
INSERT INTO paciente (nome_paciente) VALUES ('Dara Maria de Oliveira');
INSERT INTO paciente (nome_paciente) VALUES ('Diana Oliveira da Silva');

INSERT INTO consultorio (numero_consultorio) VALUES (1);
INSERT INTO consultorio (numero_consultorio) VALUES (2);
INSERT INTO consultorio (numero_consultorio) VALUES (3);


INSERT INTO medico (nome_medico, especialidade_medico, crm_medico, idade_medico) VALUES ('Dr. Washigton', 'Oftalmologista', '37.110', 37);
INSERT INTO medico (nome_medico, especialidade_medico, crm_medico, idade_medico) VALUES ('Dr. Roberto', 'Cirurgiao', '45.162', 28);
INSERT INTO medico (nome_medico, especialidade_medico, crm_medico, idade_medico) VALUES ('Dr. Cesar', 'Cirurgiao', '87.659', 55);


INSERT INTO consulta (id_paciente_fk, id_consultorio_fk, datahora_consulta) VALUES (1, 1, '2019-02-03 13:02:00');
INSERT INTO consulta (id_paciente_fk, id_consultorio_fk, datahora_consulta) VALUES (2, 2, '2019-02-03 14:02:00');
INSERT INTO consulta (id_paciente_fk, id_consultorio_fk, datahora_consulta) VALUES (3, 3, '2019-02-03 15:02:00');


INSERT INTO consulta (id_paciente_fk, id_consultorio_fk, datahora_consulta) VALUES (1, 2, '2019-02-03 16:02:00');

INSERT INTO consulta_medico (id_consulta_fk, id_medico_fk) VALUES (1, 1);
INSERT INTO consulta_medico (id_consulta_fk, id_medico_fk) VALUES (2, 2);
INSERT INTO consulta_medico (id_consulta_fk, id_medico_fk) VALUES (3, 3);

INSERT INTO consulta_medico (id_consulta_fk, id_medico_fk) VALUES (4, 2);
INSERT INTO consulta_medico (id_consulta_fk, id_medico_fk) VALUES (4, 3);