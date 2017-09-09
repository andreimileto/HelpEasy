/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import DAO.FaseDAO;
import DAO.MotivoDAO;
import DAO.PrioridadeDAO;
import DAO.ProjetoDAO;
import entidade.Fase;
import entidade.Motivo;
import entidade.Prioridade;
import entidade.Projeto;
import java.util.ArrayList;

/**
 *
 * @author Mileto
 */
public class ControlePrioridade {

    Prioridade prioridade;

    public String salvar(Prioridade prioridade) {
        this.prioridade = prioridade;

        if (prioridade.getDescricao().length() < 3) {
            return "Erro ao salvar Prioridade\nÉ preciso que o nome tenha mais que dois caracteres na descrição";
        }

        PrioridadeDAO prioridadeDAO = new PrioridadeDAO();
        ArrayList<Prioridade> prioridades = new ArrayList<>();
        prioridades = listar(prioridade);

        //verifica se existe algum cadastro com o mesmo nome que seja um ID diferente do que está alterando.
        for (int i = 0; i < prioridades.size(); i++) {
            if (this.prioridade.getDescricao().equalsIgnoreCase(prioridades.get(i).getDescricao()) && prioridade.getId() != prioridades.get(i).getId()) {
                return "Erro ao salvar Prioridade\nJá existe um cadastro com esse nome!";
            }
        }

        //caso as duas validações acima não interfira no cadastro, será efetuado o cadasro
        if (prioridadeDAO.salvar(prioridade)) {
            return "ok";
        } else {
            return "Erro ao salvar Prioridade\nEntre em contato com o suporte";
        }

    }

    public ArrayList<Prioridade> listar(Prioridade prioridade) {
        this.prioridade = prioridade;
        PrioridadeDAO prioridadeDAO = new PrioridadeDAO();
        return prioridadeDAO.listar(prioridade);

    }
}
