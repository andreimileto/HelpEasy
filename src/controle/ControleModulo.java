/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import DAO.ModuloDAO;
import entidade.Modulo;


import java.util.ArrayList;

/**
 *
 * @author Mileto
 */
public class ControleModulo {
     Modulo modulo;

     public String salvar(Modulo modulo) {
        this.modulo = modulo;
        
        //verifica se o tamanho do nome é <3, caso seja, não conseguirá cadastrar.
         if (modulo.getDescricao().length()<3) {
            return "Erro ao salvar Modulo\nÉ preciso que o nome tenha mais que dois caracteres na descrição";
        }
        
        ModuloDAO moduloDAO = new ModuloDAO();
        ArrayList<Modulo> modulos = new ArrayList<>();
        modulos= listar(modulo);
        
        //verifica se existe algum cadastro com o mesmo nome que seja um ID diferente do que está alterando.
        for (int i = 0; i < modulos.size(); i++) {
            if (this.modulo.getDescricao().equalsIgnoreCase(modulos.get(i).getDescricao()) && modulo.getId()!= modulos.get(i).getId()) {
                return "Erro ao salvar Modulo\nJá existe um cadastro com esse nome!";
            }

        }
        
        //caso as duas validações acima não interfira no cadastro, será efetuado o cadasro
        if(moduloDAO.salvar(modulo)){
            return "ok";
        }else{
            return "Erro ao salvar Modulo\nEntre em contato com o suporte";
        }
    }
    
    public ArrayList<Modulo> listar(Modulo modulo) {
        this.modulo = modulo;
        ModuloDAO moduloDAO = new ModuloDAO();
        return moduloDAO.listar(this.modulo);
    }
}
