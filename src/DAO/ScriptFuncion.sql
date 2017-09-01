/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  User
 * Created: 25/08/2017
 */

CREATE EXTENSION hstore;

--Função de Auditoria;
CREATE OR REPLACE FUNCTION fnAuditoria() RETURNS trigger LANGUAGE plpgsql AS $function$
DECLARE
	v_new text[];
	v_old text[];
	v_fields hstore;
	v_fields_old hstore;
	pk_field record;
BEGIN
	CASE TG_OP WHEN 'INSERT' THEN
		v_fields = hstore(NEW);
	WHEN 'UPDATE' THEN
		v_fields = hstore(array[]::text[]);
		v_fields_old = hstore(array[]::text[]);
		v_new = hstore_to_matrix(hstore(NEW));
		v_old = hstore_to_matrix(hstore(OLD));

	FOR i IN 1..array_upper(v_new, 1) LOOP
		RAISE NOTICE 'Value of % is %', v_new[i][1], v_new[i][2];

	IF (v_new[i][2] <> v_old[i][2]) THEN
		v_fields = v_fields || hstore(v_new[i][1], v_new[i][2]);
		v_fields_old = v_fields_old || hstore(v_old[i][1], v_old[i][2]);
	ELSE
		FOR pk_field IN (
			SELECT kcu.column_name
			FROM INFORMATION_SCHEMA.TABLES t
			LEFT JOIN INFORMATION_SCHEMA.TABLE_CONSTRAINTS tc
			ON tc.table_catalog = t.table_catalog
			AND tc.table_schema = t.table_schema
			AND tc.table_name = t.table_name
			AND tc.constraint_type = 'PRIMARY KEY'
			LEFT JOIN INFORMATION_SCHEMA.KEY_COLUMN_USAGE kcu
			ON kcu.table_catalog = tc.table_catalog
			AND kcu.table_schema = tc.table_schema
			AND kcu.table_name = tc.table_name
			AND kcu.constraint_name = tc.constraint_name
			WHERE t.table_schema = TG_TABLE_SCHEMA AND t.table_name = TG_TABLE_NAME
			ORDER BY t.table_catalog,
			t.table_schema,
			t.table_name,
			kcu.constraint_name,
				kcu.ordinal_position) 
		LOOP
			IF (v_new[i][1] = pk_field.column_name) THEN
				v_fields = v_fields || hstore(v_new[i][1], v_new[i][2]);
				v_fields_old = v_fields_old || hstore(v_old[i][1], v_old[i][2]);
			END IF;
		END LOOP;
	END IF;
END LOOP;
WHEN 'DELETE' THEN
v_fields_old = hstore(old);
END CASE;

IF (TG_OP = 'INSERT') THEN
	INSERT INTO auditoria(
	id_usuario,
	tabela,
	tipo,
	ordem,
	campos)
	VALUES(
	1,
	TG_TABLE_NAME,
	substr(TG_OP, 1, 1),
	'INSERT',
	v_fields);
END IF;

IF (TG_OP = 'UPDATE') THEN
	INSERT INTO auditoria(
	id_usuario,
	tabela,
	tipo,
	ordem,
	campos)
	VALUES(
	1,
	TG_TABLE_NAME,
	substr(TG_OP, 1, 1),
	'UPDATE',
	v_fields_old);
END IF;
RETURN NULL;
END;
$function$;

--Função para Desativar Auditoria;
CREATE OR REPLACE FUNCTION fnDisableAuditoria() RETURNS integer AS $$
    BEGIN
	ALTER TABLE Cidade DISABLE TRIGGER trgAuditoriaCidade;
	ALTER TABLE Projeto DISABLE TRIGGER trgAuditoriaProjeto;
	ALTER TABLE Motivo DISABLE TRIGGER trgAuditoriaMotivo;
	ALTER TABLE Versao DISABLE TRIGGER trgAuditoriaVersao;
	ALTER TABLE Prioridade DISABLE TRIGGER trgAuditoriaPrioridade;
	ALTER TABLE Fase DISABLE TRIGGER trgAuditoriaFase;
	ALTER TABLE Modulo DISABLE TRIGGER trgAuditoriaModulo;
	ALTER TABLE Cliente DISABLE TRIGGER trgAuditoriaCliente;
	ALTER TABLE Usuario DISABLE TRIGGER trgAuditoriaUsuario;
	ALTER TABLE Tarefa DISABLE TRIGGER trgAuditoriaTarefa;
	ALTER TABLE Tarefa_Usuario DISABLE TRIGGER trgAuditoriaTarefaUsuario;
	ALTER TABLE Movimento_Tarefa DISABLE TRIGGER trgAuditoriaMovimentoTarefa;
	RETURN 0;
        END;
$$ LANGUAGE plpgsql;

--Função para Ativar Auditoria;
CREATE OR REPLACE FUNCTION fnEnableAuditoria() RETURNS integer AS $$
    BEGIN
	ALTER TABLE Cidade ENABLE TRIGGER trgAuditoriaCidade;
	ALTER TABLE Projeto ENABLE TRIGGER trgAuditoriaProjeto;
	ALTER TABLE Motivo ENABLE TRIGGER trgAuditoriaMotivo;
	ALTER TABLE Versao ENABLE TRIGGER trgAuditoriaVersao;
	ALTER TABLE Prioridade ENABLE TRIGGER trgAuditoriaPrioridade;
	ALTER TABLE Fase ENABLE TRIGGER trgAuditoriaFase;
	ALTER TABLE Modulo ENABLE TRIGGER trgAuditoriaModulo;
	ALTER TABLE Cliente ENABLE TRIGGER trgAuditoriaCliente;
	ALTER TABLE Usuario ENABLE TRIGGER trgAuditoriaUsuario;
	ALTER TABLE Tarefa ENABLE TRIGGER trgAuditoriaTarefa;
	ALTER TABLE Tarefa_Usuario ENABLE TRIGGER trgAuditoriaTarefaUsuario;
	ALTER TABLE Movimento_Tarefa ENABLE TRIGGER trgAuditoriaMovimentoTarefa;
	RETURN 1;
        END;
$$ LANGUAGE plpgsql;