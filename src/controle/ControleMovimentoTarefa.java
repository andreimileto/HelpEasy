/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import DAO.MovimentoTarefaDAO;
import DAO.TarefaDAO;
import entidade.MovimentoTarefa;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class ControleMovimentoTarefa {

    MovimentoTarefa movimentoTarefa;

    public String salvar(MovimentoTarefa movimentoTarefa) {
        this.movimentoTarefa = movimentoTarefa;
        String mensagem = "Erro ao salvar tarefa:\n";
        MovimentoTarefaDAO movtarefaDAO = new MovimentoTarefaDAO();

        if (movtarefaDAO.salvar(movimentoTarefa)) {
            return "ok";
        } else {
            return mensagem = mensagem + "\nEntre em contato com o suporte";

        }
    }

    public ArrayList<MovimentoTarefa> listar(MovimentoTarefa movimentoTarefa) {
        this.movimentoTarefa = movimentoTarefa;
        MovimentoTarefaDAO movimentotarefaDAO = new MovimentoTarefaDAO();
        return movimentotarefaDAO.listar(this.movimentoTarefa);
    }

}
