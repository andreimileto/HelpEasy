

-- -----------------------------------------------------
-- Table usuario
-- -----------------------------------------------------
CREATE TABLE usuario (
  id serial,
  nome VARCHAR(150) NOT NULL,
  login VARCHAR(100) NOT NULL,
  senha VARCHAR(45) NOT NULL,
  situacao CHAR(1) NOT NULL,
  PRIMARY KEY (id));

--Insere os Usuários Padrões;
--Senha: 123456--
insert into usuario values(default,'Andrei','andrei','e10adc3949ba59abbe56e057f20f883e','A');
--Senha: 123456--       
insert into usuario values (default,'Leandro','leandro','e10adc3949ba59abbe56e057f20f883e','A');


-- -----------------------------------------------------
-- Table envio_email
-- -----------------------------------------------------
create table envio_email( 
    id serial not null,
    dominio char(1) not null,
    email VARCHAR(100) not null,
    senha VARCHAR(100) not null,
    titulo VARCHAR(250) not null,
    mensagem TEXT,
    envio_ativo char(1),
    constraint pk PRIMARY key (id));


-- -----------------------------------------------------
-- Table projeto
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS projeto (
  id serial,
  descricao VARCHAR(60) NOT NULL,
  situacao CHAR(1) NOT NULL,
  PRIMARY KEY (id));
-- tste

-- -----------------------------------------------------
-- Table versao
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS versao (
  id serial,
  id_projeto INT NOT NULL,
  descricao VARCHAR(45) NOT NULL,
  situacao CHAR(1) NOT NULL,
  PRIMARY KEY (id),
  -- INDEX fk_versao_projeto1_idx (id_projeto),
  CONSTRAINT fk_versao_projeto1
    FOREIGN KEY (id_projeto)
    REFERENCES projeto (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table prioridade
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS prioridade (
  id serial,
  descricao VARCHAR(45) NOT NULL,
  situacao CHAR(1) NOT NULL,
  PRIMARY KEY (id))
;


-- -----------------------------------------------------
-- Table motivo
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS motivo (
  id serial,
  descricao VARCHAR(45) NOT NULL,
  situacao CHAR(1) NOT NULL,
  PRIMARY KEY (id))
;


-- -----------------------------------------------------
-- Table cidade
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS cidade (
  id serial,
  descricao VARCHAR(150) NULL,
  situacao CHAR(1) NOT NULL,
  PRIMARY KEY (id))
;


-- -----------------------------------------------------
-- Table cliente
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS cliente (
  id serial,
  id_cidade INT NOT NULL,
  razao_social VARCHAR(150) NOT NULL,
  cpf_cnpj VARCHAR(18) NULL,
  tipo_cadastro CHAR(1) NOT NULL,
  clientecol VARCHAR(45) NULL,
  endereco VARCHAR(150) NULL,
  telefone VARCHAR(15) NULL,
  email VARCHAR(150) NULL,
  situacao CHAR(1) NOT NULL,
  PRIMARY KEY (id),
  --INDEX fk_cliente_cidade1_idx (id_cidade),
  CONSTRAINT fk_cliente_cidade1
    FOREIGN KEY (id_cidade)
    REFERENCES cidade (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table fase
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS fase (
  id serial,
  descricao VARCHAR(45) NOT NULL,
  situacao CHAR(1) NOT NULL,
  PRIMARY KEY (id))
;


-- -----------------------------------------------------
-- Table modulo
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS modulo (
  id serial,
  id_projeto INT NOT NULL,
  descricao VARCHAR(45) NOT NULL,
  situacao CHAR(1) NOT NULL,
  PRIMARY KEY (id),
 -- INDEX fk_modulo_projeto1_idx (id_projeto),
  CONSTRAINT fk_modulo_projeto1
    FOREIGN KEY (id_projeto)
    REFERENCES projeto (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table tarefa
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS tarefa (
  id serial,
  id_cliente INT,
  id_projeto INT NOT NULL,
  id_motivo INT NOT NULL,
  id_prioridade INT NOT NULL,
  id_fase INT NOT NULL,
  id_modulo INT NOT NULL,
  id_versao_bug INT,
  id_versao_correcao INT,
  id_usuario_autor INT NOT NULL,
  id_usuario_responsavel INT NOT NULL,
  titulo VARCHAR(150) NOT NULL,
  descricao text NULL,
  datahora_criacao timestamp default now(),
  datahora_previsao TIMESTAMP,
  datahora_conclusao TIMESTAMP NULL,
  situacao CHAR(1) NOT NULL,
  PRIMARY KEY (id),
 
  CONSTRAINT fk_tarefa_projeto1
    FOREIGN KEY (id_projeto)
    REFERENCES projeto (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_tarefa_motivo1
    FOREIGN KEY (id_motivo)
    REFERENCES motivo (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_tarefa_versao1
    FOREIGN KEY (id_versao_bug)
    REFERENCES versao (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_tarefa_versao2
    FOREIGN KEY (id_versao_correcao)
    REFERENCES versao (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_tarefa_prioridade1
    FOREIGN KEY (id_prioridade)
    REFERENCES prioridade (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_tarefa_cliente1
    FOREIGN KEY (id_cliente)
    REFERENCES cliente (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_tarefa_fase1
    FOREIGN KEY (id_fase)
    REFERENCES fase (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_tarefa_usuario1
    FOREIGN KEY (id_usuario_autor)
    REFERENCES usuario (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_tarefa_usuario2
    FOREIGN KEY (id_usuario_responsavel)
    REFERENCES usuario (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_tarefa_modulo1
    FOREIGN KEY (id_modulo)
    REFERENCES modulo (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table movimento_tarefa
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS movimento_tarefa (
  id serial,
  id_tarefa INT NOT NULL,
  descricao text NOT NULL,
  datahora_movimento timestamp default now(),
  situacao CHAR(1) NOT NULL,
  anexo VARCHAR(45) NULL,
  PRIMARY KEY (id),
--  INDEX fk_movimento_tarefa_tarefa_idx (id_tarefa),
  CONSTRAINT fk_movimento_tarefa_tarefa
    FOREIGN KEY (id_tarefa)
    REFERENCES tarefa (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table tarefa_usuario
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS tarefa_usuario (
  id serial,
  id_tarefa INT NOT NULL,
  id_usuario INT NOT NULL,
  PRIMARY KEY (id),
--  INDEX fk_tarefa_has_usuario_usuario1_idx (id_usuario),
--  INDEX fk_tarefa_has_usuario_tarefa1_idx (id_tarefa),
  CONSTRAINT fk_tarefa_has_usuario_tarefa1
    FOREIGN KEY (id_tarefa)
    REFERENCES tarefa (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_tarefa_has_usuario_usuario1
    FOREIGN KEY (id_usuario)
    REFERENCES usuario (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


CREATE EXTENSION hstore;
CREATE TABLE IF NOT EXISTS auditoria
(
  id serial,
  id_usuario INT NOT NULL,
  tabela VARCHAR(100) NOT NULL,
  data_hora timestamp default now(),
  tipo CHAR(1) NOT NULL,
  campos hstore,
  PRIMARY KEY (id),
  CONSTRAINT fk_auditoria_usuario
    FOREIGN KEY (id_usuario)
    REFERENCES usuario (id)
  
);


--DROP TABLE usuario_permissao_tela;
CREATE TABLE usuario_permissao_tela (
        id bigserial,
        id_usuario INT NOT NULL,
        tela VARCHAR(100) NOT NULL,
        tela_amigavel VARCHAR(100) NOT NULL,
	permite_acesso boolean NOT NULL,
        PRIMARY KEY (id),
        CONSTRAINT fk_usuario_permissao_tela_id_usuario
            FOREIGN KEY (id_usuario)
            REFERENCES usuario (id)
);

--DROP TABLE usuario_permissao_tela_acoes;
CREATE TABLE usuario_permissao_tela_acoes (
        id bigserial,
        id_tela INT NOT NULL,
        acao VARCHAR(100) NOT NULL,
        acao_amigavel VARCHAR(100) NOT NULL,
	permite_acesso boolean NOT NULL,
        PRIMARY KEY (id),
                CONSTRAINT usuario_permissao_tela_id_tela
            FOREIGN KEY (id_tela)
            REFERENCES usuario_permissao_tela (id)
);

--DROP TABLE telas;
CREATE TABLE telas
(
	id bigserial,
	tela varchar(100) NOT NULL,
	tela_amigavel varchar(100) NOT NULL,
	acao varchar(100) NOT NULL,
	acao_amigavel varchar(100) NOT NULL,
	PRIMARY KEY (id)
);


delete from telas;


INSERT INTO public.telas(id, tela, tela_amigavel, acao, acao_amigavel) VALUES (1, 'janelas.JdgCadastroFase', 'Cadastro de Fase', 'btnSalvar', 'Salvar');
INSERT INTO public.telas(id, tela, tela_amigavel, acao, acao_amigavel) VALUES (2, 'janelas.JdgCadastroFase', 'Cadastro de Fase', 'btnLocalizar', 'Localizar');
INSERT INTO public.telas(id, tela, tela_amigavel, acao, acao_amigavel) VALUES (3, 'janelas.JdgCadastroFase', 'Cadastro de Fase', 'btnExcluir', 'Excluir');
INSERT INTO public.telas(id, tela, tela_amigavel, acao, acao_amigavel) VALUES (4, 'janelas.JdgCadastroModulo', 'Cadastro de Modulo', 'btnSalvar', 'Salvar');
INSERT INTO public.telas(id, tela, tela_amigavel, acao, acao_amigavel) VALUES (5, 'janelas.JdgCadastroModulo', 'Cadastro de Modulo', 'btnLocalizar', 'Localizar');
INSERT INTO public.telas(id, tela, tela_amigavel, acao, acao_amigavel) VALUES (6, 'janelas.JdgCadastroModulo', 'Cadastro de Modulo', 'btnExcluir', 'Excluir');
INSERT INTO public.telas(id, tela, tela_amigavel, acao, acao_amigavel) VALUES (7, 'janelas.JdgCadastroMotivo', 'Cadastro de Motivo', 'btnSalvar', 'Salvar');
INSERT INTO public.telas(id, tela, tela_amigavel, acao, acao_amigavel) VALUES (8, 'janelas.JdgCadastroMotivo', 'Cadastro de Motivo', 'btnLocalizar', 'Localizar');
INSERT INTO public.telas(id, tela, tela_amigavel, acao, acao_amigavel) VALUES (9, 'janelas.JdgCadastroMotivo', 'Cadastro de Motivo', 'btnExcluir', 'Excluir');
INSERT INTO public.telas(id, tela, tela_amigavel, acao, acao_amigavel) VALUES (10, 'janelas.JdgCadastroPrioridade', 'Cadastro de Prioridade', 'btnSalvar', 'Salvar');
INSERT INTO public.telas(id, tela, tela_amigavel, acao, acao_amigavel) VALUES (11, 'janelas.JdgCadastroPrioridade', 'Cadastro de Prioridade', 'btnLocalizar', 'Localizar');
INSERT INTO public.telas(id, tela, tela_amigavel, acao, acao_amigavel) VALUES (12, 'janelas.JdgCadastroPrioridade', 'Cadastro de Prioridade', 'btnExcluir', 'Excluir');
INSERT INTO public.telas(id, tela, tela_amigavel, acao, acao_amigavel) VALUES (13, 'janelas.JdgCadastroProjeto', 'Cadastro de Projeto', 'btnSalvar', 'Salvar');
INSERT INTO public.telas(id, tela, tela_amigavel, acao, acao_amigavel) VALUES (14, 'janelas.JdgCadastroProjeto', 'Cadastro de Projeto', 'btnLocalizar', 'Localizar');
INSERT INTO public.telas(id, tela, tela_amigavel, acao, acao_amigavel) VALUES (15, 'janelas.JdgCadastroProjeto', 'Cadastro de Projeto', 'btnExcluir', 'Excluir');
INSERT INTO public.telas(id, tela, tela_amigavel, acao, acao_amigavel) VALUES (16, 'janelas.JdgCadastroUsuario', 'Cadastro de Usuario', 'btnSalvar', 'Salvar');
INSERT INTO public.telas(id, tela, tela_amigavel, acao, acao_amigavel) VALUES (17, 'janelas.JdgCadastroUsuario', 'Cadastro de Usuario', 'btnLocalizar', 'Localizar');
INSERT INTO public.telas(id, tela, tela_amigavel, acao, acao_amigavel) VALUES (18, 'janelas.JdgCadastroUsuario', 'Cadastro de Usuario', 'btnExcluir', 'Excluir');
INSERT INTO public.telas(id, tela, tela_amigavel, acao, acao_amigavel) VALUES (19, 'janelas.JdgCadastroVersao', 'Cadastro de Versao', 'btnSalvar', 'Salvar');
INSERT INTO public.telas(id, tela, tela_amigavel, acao, acao_amigavel) VALUES (20, 'janelas.JdgCadastroVersao', 'Cadastro de Versao', 'btnLocalizar', 'Localizar');
INSERT INTO public.telas(id, tela, tela_amigavel, acao, acao_amigavel) VALUES (21, 'janelas.JdgCadastroVersao', 'Cadastro de Versao', 'btnExcluir', 'Excluir');
INSERT INTO public.telas(id, tela, tela_amigavel, acao, acao_amigavel) VALUES (22, 'janelas.JdgListaPermissoes', 'Cadastro de Permissao', 'btnSalvar', 'Salvar');
INSERT INTO public.telas(id, tela, tela_amigavel, acao, acao_amigavel) VALUES (23, 'janelas.JdgCadastroCliente', 'Cadastro da Cliente', 'btnSalvar', 'Salvar');
INSERT INTO public.telas(id, tela, tela_amigavel, acao, acao_amigavel) VALUES (24, 'janelas.JdgCadastroCliente', 'Cadastro da Cliente', 'btnLocalizar', 'Localizar');
INSERT INTO public.telas(id, tela, tela_amigavel, acao, acao_amigavel) VALUES (25, 'janelas.JdgCadastroCliente', 'Cadastro da Cliente', 'btnExcluir', 'Excluir');
INSERT INTO public.telas(id, tela, tela_amigavel, acao, acao_amigavel) VALUES (26, 'janelas.JdgCadastroCidade', 'Cadastro de Cidade', 'btnSalvar', 'Salvar');
INSERT INTO public.telas(id, tela, tela_amigavel, acao, acao_amigavel) VALUES (27, 'janelas.JdgCadastroCidade', 'Cadastro de Cidade', 'btnLocalizar', 'Localizar');
INSERT INTO public.telas(id, tela, tela_amigavel, acao, acao_amigavel) VALUES (28, 'janelas.JdgCadastroCidade', 'Cadastro de Cidade', 'btnExcluir', 'Excluir');
INSERT INTO public.telas(id, tela, tela_amigavel, acao, acao_amigavel) VALUES (29, 'janelas.JdgAlteracaoSenha', 'Alterar Senha', 'btnConfirmar', 'Confirmar');
INSERT INTO public.telas(id, tela, tela_amigavel, acao, acao_amigavel) VALUES (30, 'janelas.JdgParametrosSistema', 'Parametros do Sistema', 'btnEnableAuditoria', 'Ativar Auditoria');
INSERT INTO public.telas(id, tela, tela_amigavel, acao, acao_amigavel) VALUES (31, 'janelas.JdgParametrosSistema', 'Parametros do Sistema', 'btnDisableAuditoria', 'Desativar Auditoria');
INSERT INTO public.telas(id, tela, tela_amigavel, acao, acao_amigavel) VALUES (32, 'janelas.JdgParametrosSistema', 'Parametros do Sistema', 'btnExportaXMLClientes', 'Exporta XML Cliente');
INSERT INTO public.telas(id, tela, tela_amigavel, acao, acao_amigavel) VALUES (33, 'janelas.JdgParametrosSistema', 'Parametros do Sistema', 'btnImportaXMLClientes', 'Importa XML Cliente');
INSERT INTO public.telas(id, tela, tela_amigavel, acao, acao_amigavel) VALUES (34, 'janelas.JdgCadastroTarefa', 'Cadastro de Tarefa', 'btnSalvar', 'Salvar');
INSERT INTO public.telas(id, tela, tela_amigavel, acao, acao_amigavel) VALUES (35, 'janelas.JdgCadastroTarefa', 'Cadastro de Tarefa', 'btnLocalizar', 'Localizar');
INSERT INTO public.telas(id, tela, tela_amigavel, acao, acao_amigavel) VALUES (36, 'janelas.JdgCadastroTarefa', 'Cadastro de Tarefa', 'btnExcluir', 'Excluir');
INSERT INTO public.telas(id, tela, tela_amigavel, acao, acao_amigavel) VALUES (37, 'janelas.JdgCadastroEmail', 'Cadastro de Email', 'btnSalvar', 'btnSalvar');