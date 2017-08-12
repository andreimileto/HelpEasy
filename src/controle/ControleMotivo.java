/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import DAO.CidadeDAO;
import DAO.MotivoDAO;
import entidade.Cidade;
import entidade.Motivo;
import java.util.ArrayList;

/**
 *
 * @author Mileto
 */
public class ControleMotivo {

    Motivo motivo;

     public boolean salvar(Motivo motivo) {
        this.motivo = motivo;
        MotivoDAO motivoDAO = new MotivoDAO();
        
        if(motivoDAO.salvar(motivo)){
            return true;
        }else{
            return false;
        }
    }
    
    public ArrayList<Motivo> listar(Motivo motivo) {
        this.motivo = motivo;
        MotivoDAO motivoDAO = new MotivoDAO();
        return motivoDAO.listar(this.motivo);
    }
}
