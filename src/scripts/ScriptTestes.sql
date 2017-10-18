DROP TABLE telas;
CREATE TABLE telas
(
  tela varchar(100) NOT NULL,
  tela_amigavel varchar(100) NOT NULL,
  acao varchar(100) NOT NULL,
  acao_amigavel varchar(100) NOT NULL,
   PRIMARY KEY (tela,acao)
  );

DELETE FROM telas;

COPY telas FROM 'C:/om/permissoes.csv'  using delimiters ';';
