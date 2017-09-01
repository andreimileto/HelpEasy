/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  User
 * Created: 01/09/2017
 */

CREATE SEQUENCE session_id; 


CREATE OR REPLACE FUNCTION get_sessid() RETURNS bigint AS $$ 
DECLARE 
        ss BIGINT; 
BEGIN 
        SELECT currval('session_id') INTO ss; 
        RETURN ss; 
        
        EXCEPTION WHEN object_not_in_prerequisite_state THEN 
                SELECT nextval('session_id') INTO ss; 
        RETURN ss; 
END 
$$ 
LANGUAGE 'plpgsql'; 

CREATE TABLE sessao_usuario
( 
        sessid bigint, 
        id_usuario	int, 
        nome	varchar, 
        tmsp    time default current_timestamp 
); 


CREATE OR REPLACE FUNCTION fnGravaUsuarioSessao(int,varchar) RETURNS void AS $$ 
        INSERT INTO sessao_usuario VALUES(get_sessid(),$1,$2); 
$$ 
LANGUAGE 'sql'; 

CREATE OR REPLACE FUNCTION fnPegaUsuarioSessao() RETURNS Integer AS $$ 
        SELECT id_usuario FROM sessao_usuario WHERE sessid = get_sessid();
$$ 
LANGUAGE 'sql'; 

CREATE OR REPLACE FUNCTION fnLimpaSessao() RETURNS void AS $$ 
        DELETE FROM sessao_usuario WHERE sessid = get_sessid();
$$ 
LANGUAGE 'sql'; 


select fnEnableAuditoria();
select fnGravaUsuarioSessao(2,'Leandro'); 
INSERT INTO cidade values (default,'Teste','A');
select fnPegaUsuarioSessao()
