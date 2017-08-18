/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import DAO.FaseDAO;
import DAO.ProjetoDAO;
import entidade.Fase;
import entidade.Projeto;
import java.util.ArrayList;

/**
 *
 * @author Mileto
 */
public class ControleProjeto {
     Projeto projeto;

     public boolean salvar(Projeto projeto) {
        this.projeto = projeto;
        ProjetoDAO projetoDAO = new ProjetoDAO();
        
        if(projetoDAO.salvar(projeto)){
            return true;
        }else{
            return false;
        }
    }
    
    public ArrayList<Projeto> listar(Projeto projeto) {
        this.projeto = projeto;
        ProjetoDAO projetoDAO = new ProjetoDAO();
        return projetoDAO.listar(projeto);
        
    }
}
