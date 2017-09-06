/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import DAO.FaseDAO;
import DAO.ProjetoDAO;
import DAO.UsuarioDAO;
import entidade.Fase;

import entidade.Usuario;
import java.util.ArrayList;

/**
 *
 * @author Mileto
 */
public class ControleUsuario {

    Usuario usuario;
    
    
    public String salvar(Usuario usuario) {
        this.usuario = usuario;
        
       
        
        //verifica se existe algum cadastro com o mesmo nome que seja um ID diferente do que está alterando.
//        for (int i = 0; i < fases.size(); i++) {
//            if (this.fase.getDescricao().equalsIgnoreCase(fases.get(i).getDescricao()) && fase.getId()!= fases.get(i).getId()) {
//                return "Erro ao salvar Fase\nJá existe um cadastro com esse nome!";
//            }
//
//        }
        
        //caso as duas validações acima não interfira no cadastro, será efetuado o cadasro
//        if(faseDAO.salvar(fase)){
//            return "ok";
//        }else{
//            return "Erro ao salvar Fase\nEntre em contato com o suporte";
//        }

    UsuarioDAO usuarioDAO = new UsuarioDAO();
        if (usuarioDAO.salvar(usuario)) {
            return "ok";
        }else{
            return "Erro ao salvar usuário";
        }
    }

    public ArrayList<Usuario> listar(Usuario usuario) {
        this.usuario = usuario;
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.listar(usuario);

    }
}
