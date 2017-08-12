/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import DAO.FaseDAO;
import DAO.MotivoDAO;
import entidade.Fase;
import entidade.Motivo;


import java.util.ArrayList;

/**
 *
 * @author Mileto
 */
public class ControleFase {
     Fase fase;

     public boolean salvar(Fase fase) {
        this.fase = fase;
        FaseDAO faseDAO = new FaseDAO();
        
        if(faseDAO.salvar(fase)){
            return true;
        }else{
            return false;
        }
    }
    
    public ArrayList<Fase> listar(Fase fase) {
        this.fase = fase;
        FaseDAO faseDAO = new FaseDAO();
        return faseDAO.listar(this.fase);
    }
}
