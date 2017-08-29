/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import DAO.ProjetoDAO;
import DAO.UsuarioDAO;

import entidade.Usuario;
import java.util.ArrayList;

/**
 *
 * @author Mileto
 */
public class ControleUsuario {

    Usuario usuario;

    public ArrayList<Usuario> listar(Usuario usuario) {
        this.usuario = usuario;
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.listar(usuario);

    }
}
