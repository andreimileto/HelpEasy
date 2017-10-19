/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import DAO.VersaoDAO;
import entidade.Versao;


import java.util.ArrayList;

/**
 *
 * @author Mileto
 */
public class ControleVersao {
     Versao versao;

     public String salvar(Versao versao) {
        this.versao = versao;
        
        //verifica se o tamanho do nome é <3, caso seja, não conseguirá cadastrar.
         if (versao.getDescricao().length()<3) {
            return "Erro ao salvar Versao\nÉ preciso que o nome tenha mais que dois caracteres na descrição";
        }
         if (versao.getProjeto().getId()<1) {
             return "Erro ao salvar versão\nProjeto precisa ser selecionado";
         }
        
        VersaoDAO versaoDAO = new VersaoDAO();
        ArrayList<Versao> versoes = new ArrayList<>();
        versoes= listar(versao);
        
        //verifica se existe algum cadastro com o mesmo nome que seja um ID diferente do que está alterando.
        for (int i = 0; i < versoes.size(); i++) {
            if (this.versao.getDescricao().equalsIgnoreCase(versoes.get(i).getDescricao()) 
                    && versao.getId()!= versoes.get(i).getId()
                    && versao.getProjeto().getId() == versoes.get(i).getProjeto().getId()) {
                return "Erro ao salvar Versao\nJá existe um cadastro com esse nome e mesmo projeto!";
            }

        }
        
        //caso as duas validações acima não interfira no cadastro, será efetuado o cadasro
        if(versaoDAO.salvar(versao)){
            return "ok";
        }else{
            return "Erro ao salvar Versao\nEntre em contato com o suporte";
        }
    }
    
    public ArrayList<Versao> listar(Versao versao) {
        this.versao = versao;
        VersaoDAO versaoDAO = new VersaoDAO();
        return versaoDAO.listar(this.versao);
    }
}
