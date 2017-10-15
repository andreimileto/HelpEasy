/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  User
 * Created: 25/08/2017
 */
CREATE TRIGGER trgAuditoriaCidade AFTER INSERT OR UPDATE ON Cidade FOR EACH ROW EXECUTE PROCEDURE fnAuditoria(); 
CREATE TRIGGER trgAuditoriaProjeto AFTER INSERT OR UPDATE ON Projeto FOR EACH ROW EXECUTE PROCEDURE fnAuditoria(); 
CREATE TRIGGER trgAuditoriaMotivo AFTER INSERT OR UPDATE ON Motivo FOR EACH ROW EXECUTE PROCEDURE fnAuditoria(); 
CREATE TRIGGER trgAuditoriaVersao AFTER INSERT OR UPDATE ON Versao FOR EACH ROW EXECUTE PROCEDURE fnAuditoria(); 
CREATE TRIGGER trgAuditoriaPrioridade AFTER INSERT OR UPDATE ON Prioridade FOR EACH ROW EXECUTE PROCEDURE fnAuditoria(); 
CREATE TRIGGER trgAuditoriaFase AFTER INSERT OR UPDATE ON Fase FOR EACH ROW EXECUTE PROCEDURE fnAuditoria(); 
CREATE TRIGGER trgAuditoriaModulo AFTER INSERT OR UPDATE ON Modulo FOR EACH ROW EXECUTE PROCEDURE fnAuditoria(); 
CREATE TRIGGER trgAuditoriaCliente AFTER INSERT OR UPDATE ON Cliente FOR EACH ROW EXECUTE PROCEDURE fnAuditoria(); 
CREATE TRIGGER trgAuditoriaUsuario AFTER INSERT OR UPDATE ON Usuario FOR EACH ROW EXECUTE PROCEDURE fnAuditoria(); 
CREATE TRIGGER trgAuditoriaTarefa AFTER INSERT OR UPDATE ON Tarefa FOR EACH ROW EXECUTE PROCEDURE fnAuditoria(); 
CREATE TRIGGER trgAuditoriaTarefaUsuario AFTER INSERT OR UPDATE ON Tarefa_Usuario FOR EACH ROW EXECUTE PROCEDURE fnAuditoria(); 
CREATE TRIGGER trgAuditoriaMovimentoTarefa AFTER INSERT OR UPDATE ON Movimento_Tarefa FOR EACH ROW EXECUTE PROCEDURE fnAuditoria(); 