/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import DAO.FaseDAO;
import DAO.MotivoDAO;
import DAO.ProjetoDAO;
import entidade.Fase;
import entidade.Motivo;
import entidade.Projeto;
import java.util.ArrayList;

/**
 *
 * @author Mileto
 */
public class ControleProjeto {

    Projeto projeto;

    public String salvar(Projeto projeto) {
        this.projeto = projeto;

        if (projeto.getDescricao().length() < 3) {
            return "Erro ao salvar Projeto\nÉ preciso que o nome tenha mais que dois caracteres na descrição";
        }

        ProjetoDAO projetoDAO = new ProjetoDAO();
        ArrayList<Projeto> projetos = new ArrayList<>();
        projetos = listar(projeto);

        //verifica se existe algum cadastro com o mesmo nome que seja um ID diferente do que está alterando.
        for (int i = 0; i < projetos.size(); i++) {
            if (this.projeto.getDescricao().equalsIgnoreCase(projetos.get(i).getDescricao()) && projeto.getId() != projetos.get(i).getId()) {
                return "Erro ao salvar Projeto\nJá existe um cadastro com esse nome!";
            }
        }

        //caso as duas validações acima não interfira no cadastro, será efetuado o cadasro
        if (projetoDAO.salvar(projeto)) {
            return "ok";
        } else {
            return "Erro ao salvar Projeto\nEntre em contato com o suporte";
        }

    }

    public ArrayList<Projeto> listar(Projeto projeto) {
        this.projeto = projeto;
        ProjetoDAO projetoDAO = new ProjetoDAO();
        return projetoDAO.listar(projeto);

    }
}
