/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import DAO.CidadeDAO;
import entidade.Cidade;
import java.util.ArrayList;

/**
 *
 * @author Mileto
 */
public class ControleCidade {

    Cidade cidade;

    public boolean salvar(Cidade cidade) {
        this.cidade = cidade;

        CidadeDAO cidadeDAO = new CidadeDAO();
            ArrayList<Cidade> cidades = new ArrayList<>();
            cidades = cidadeDAO.listar(cidade);

        for (int i = 0; i < cidades.size(); i++) {
            if (this.cidade.getDescricao().equalsIgnoreCase(cidades.get(i).getDescricao()) && cidade.getId()!= cidades.get(i).getId()) {
                return false;
            }

        }

        if (cidadeDAO.salvar(cidade)) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Cidade> listar(Cidade cidade) {
        this.cidade = cidade;
        CidadeDAO cidadeDAO = new CidadeDAO();
        return cidadeDAO.listar(this.cidade);
    }

}
