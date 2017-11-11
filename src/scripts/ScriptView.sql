/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  User
 * Created: 25/08/2017
 */

--DROP VIEW viewPermissoes;
CREATE VIEW viewPermissoes as
SELECT 
	u.id as id_usuario
	,u.nome as usuario
	,upt.id as id_tela
	,upt.tela
	,upt.tela_amigavel
	,upt.permite_acesso as permite_acesso_tela
	,upta.id as id_tela_acao
	,upta.acao
	,upta.acao_amigavel
	,upta.permite_acesso as permite_acesso_acao
FROM usuario u
	LEFT JOIN usuario_permissao_tela upt on u.id = upt.id_usuario
	LEFT JOIN usuario_permissao_tela_acoes upta on upt.id = upta.id_tela;

--DROP VIEW viewTelas;
CREATE VIEW viewTelas as
SELECT t.*,u.id as id_usuario FROM telas t,usuario u;

--DROP VIEW viewTelasFaltantes;
create view viewTelasFaltantes as
select distinct vT.id_usuario,vT.tela,vT.tela_amigavel,true as permite_acesso 
from viewTelas vT
left join viewPermissoes vP on vP.tela = vT.tela and vP.id_usuario = vT.id_usuario
where vP.tela is null;

--DROP VIEW viewTelasAcoesFaltantes;
create view viewTelasAcoesFaltantes as
select 
	distinct upt.id as id_tela,vT.acao,vT.acao_amigavel,true as permite_acesso 
from viewTelas vT
	inner join usuario_permissao_tela upt on vT.tela = upt.Tela and vT.id_usuario = upt.id_usuario
	left join viewPermissoes vP on vP.tela = vT.tela and vP.id_usuario = vT.id_usuario and vP.acao = vT.acao
where 
	vP.tela is null;

--DROP VIEW viewUsuariosAlteracoes;
CREATE VIEW viewUsuariosAlteracoes as
SELECT 
	u.id as id_usuario
	, T.id as id_tarefa
	, 'Tarefa "' || cast(t.id as varchar) || '-' || t.titulo || '" foi criada pelo usuário "'
		     || ua.id || ' - ' || ua.nome || '" e você é o responsável.' as Texto
FROM usuario u
	INNER JOIN tarefa t on u.Id = t.id_usuario_responsavel 
	INNER JOIN usuario ua on ua.Id = t.id_usuario_autor
WHERE 
	u.datahora_ultimavisualizacao < t.datahora_criacao AND
	t.id_usuario_responsavel  <> t.id_usuario_autor

UNION ALL

SELECT
	u.id as id_usuario
	, T.id as id_tarefa
	, 'Tarefa "' || cast(t.id as varchar) || '-' || t.titulo || '" foi modificada pelo usuário "' 
		     || ua.id || ' - ' || ua.nome || '" e você é o ' || 
		     CASE WHEN id_usuario_responsavel = u.id AND id_usuario_autor = u.id THEN 'autor/responsável.' ELSE 
		     CASE WHEN id_usuario_responsavel = u.id THEN 'responsável.' ELSE 'autor.' END END as Texto
FROM usuario u
	INNER JOIN tarefa t on u.Id = t.id_usuario_responsavel or u.Id = t.id_usuario_autor
	INNER JOIN movimento_tarefa mt on t.Id = mt.id_tarefa
	INNER JOIN usuario ua on ua.Id = mt.id_usuario
WHERE 
	u.datahora_ultimavisualizacao < mt.datahora_movimento
	AND mt.id_usuario <> u.id

UNION ALL

SELECT
	u.id as id_usuario
	, T.id as id_tarefa
	, 'Tarefa "' || cast(t.id as varchar) || '-' || t.titulo || '" foi modificada pelo usuário "' 
		     || ua.id || ' - ' || ua.nome || '" e você é participante. ' as Texto
FROM tarefa_usuario tu
	INNER JOIN usuario u on tu.id_usuario = u.id
	INNER JOIN tarefa t on t.id = tu.id_tarefa and (tu.Id_usuario = t.id_usuario_responsavel or tu.Id = t.id_usuario_autor)
	INNER JOIN movimento_tarefa mt on t.Id = mt.id_tarefa
	INNER JOIN usuario ua on ua.Id = mt.id_usuario
WHERE 
	u.datahora_ultimavisualizacao < mt.datahora_movimento
	AND mt.id_usuario <> u.id