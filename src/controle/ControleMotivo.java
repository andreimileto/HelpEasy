/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import DAO.CidadeDAO;
import DAO.FaseDAO;
import DAO.MotivoDAO;
import entidade.Cidade;
import entidade.Fase;
import entidade.Motivo;
import java.util.ArrayList;

/**
 *
 * @author Mileto
 */
public class ControleMotivo {

    Motivo motivo;

     public String salvar(Motivo motivo) {
        this.motivo = motivo;
         //verifica se o tamanho do nome é <3, caso seja, não conseguirá cadastrar.
         if (motivo.getDescricao().length()<3) {
            return "Erro ao salvar Motivo\nÉ preciso que o nome tenha mais que dois caracteres na descrição";
        }
        
        MotivoDAO motivoDAO = new MotivoDAO();
        ArrayList<Motivo> motivos = new ArrayList<>();
        motivos= listar(motivo);
        
        //verifica se existe algum cadastro com o mesmo nome que seja um ID diferente do que está alterando.
        for (int i = 0; i < motivos.size(); i++) {
            if (this.motivo.getDescricao().equalsIgnoreCase(motivos.get(i).getDescricao()) && motivo.getId()!= motivos.get(i).getId()) {
                return "Erro ao salvar Motivo\nJá existe um cadastro com esse nome!";
            }

        }
        
        //caso as duas validações acima não interfira no cadastro, será efetuado o cadasro
        if(motivoDAO.salvar(motivo)){
            return "ok";
        }else{
            return "Erro ao salvar Motivo\nEntre em contato com o suporte";
        }
    }
    
    public ArrayList<Motivo> listar(Motivo motivo) {
        this.motivo = motivo;
        MotivoDAO motivoDAO = new MotivoDAO();
        return motivoDAO.listar(this.motivo);
    }
}
