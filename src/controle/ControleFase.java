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

     public String salvar(Fase fase) {
        this.fase = fase;
        
        //verifica se o tamanho do nome é <3, caso seja, não conseguirá cadastrar.
         if (fase.getDescricao().length()<3) {
            return "Erro ao salvar Fase\nÉ preciso que o nome tenha mais que dois caracteres na descrição";
        }
        
        FaseDAO faseDAO = new FaseDAO();
        ArrayList<Fase> fases = new ArrayList<>();
        fases= listar(fase);
        
        //verifica se existe algum cadastro com o mesmo nome que seja um ID diferente do que está alterando.
        for (int i = 0; i < fases.size(); i++) {
            if (this.fase.getDescricao().equalsIgnoreCase(fases.get(i).getDescricao()) && fase.getId()!= fases.get(i).getId()) {
                return "Erro ao salvar Fase\nJá existe um cadastro com esse nome!";
            }

        }
        
        //caso as duas validações acima não interfira no cadastro, será efetuado o cadasro
        if(faseDAO.salvar(fase)){
            return "ok";
        }else{
            return "Erro ao salvar Fase\nEntre em contato com o suporte";
        }
    }
    
    public ArrayList<Fase> listar(Fase fase) {
        this.fase = fase;
        FaseDAO faseDAO = new FaseDAO();
        return faseDAO.listar(this.fase);
    }
}
