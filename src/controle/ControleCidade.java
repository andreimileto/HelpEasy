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

    public String salvar(Cidade cidade) {
        this.cidade = cidade;

        //verifica se o tamanho do nome é <3, caso seja, não conseguirá cadastrar.
        if (cidade.getDescricao().length() < 3) {
            return "Erro ao salvar Cidade\nÉ preciso que o nome tenha mais que dois caracteres na descrição";
        }

        CidadeDAO cidadeDAO = new CidadeDAO();
        ArrayList<Cidade> cidades = new ArrayList<>();

        cidades = listar(cidade);

        //verifica se existe algum cadastro com o mesmo nome que seja um ID diferente do que está alterando.
        for (int i = 0; i < cidades.size(); i++) {
            if (this.cidade.getDescricao().equalsIgnoreCase(cidades.get(i).getDescricao()) && cidade.getId() != cidades.get(i).getId()) {
                return "Erro ao salvar Cidade\nJá existe um cadastro com esse nome!";
            }

        }

        //caso as duas validações acima não interfira no cadastro, será efetuado o cadasro
        if (cidadeDAO.salvar(cidade)) {
            return "ok";
        } else {
            return "Erro ao salvar Cidade\nEntre em contato com o suporte";
        }
    }

    public ArrayList<Cidade> listar(Cidade cidade) {
        this.cidade = cidade;
        CidadeDAO cidadeDAO = new CidadeDAO();
        return cidadeDAO.listar(this.cidade);
    }

}
