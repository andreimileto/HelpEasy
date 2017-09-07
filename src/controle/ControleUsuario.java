/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import DAO.FaseDAO;
import DAO.ProjetoDAO;
import DAO.UsuarioDAO;
import apoio.Formatacao;
import entidade.Fase;

import entidade.Usuario;
import java.util.ArrayList;

/**
 *
 * @author Mileto
 */
public class ControleUsuario {

    Usuario usuario;
    ArrayList<Usuario> usuarios;

    public String salvar(Usuario usuario) {
        usuarios = new ArrayList<>();
        this.usuario = usuario;

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        String mensagem = "Erro ao salvar usuário\n";

        if (usuario.getNome().length() < 2 || usuario.getNome().length() > 150) {
            mensagem = mensagem + "-Nome do usuário precisa ter 2 caracteres e não pode ultrapassar 150\n";
        }
        if (usuario.getLogin().length() < 2 || usuario.getLogin().length() > 100) {
            mensagem = mensagem + "-Login do usuário precisa ter 2 caracteres e não pode ultrapassar 150\n";
        }
        
        if (usuario.getSenha().length()<6) {
            mensagem = mensagem+"-Senha deve conter no mínimo 6 caracteres\n";
        }else{
            usuario.setSenha(Formatacao.getSenhaMD5(usuario.getSenha()));
        }

        usuarios = usuarioDAO.listar(usuario);
        try {
            if (usuarios.size() > 0) {
                if (usuarios.get(0).getLogin().equals(usuario.getLogin()) && usuario.getId()!= usuarios.get(0).getId()) {

                    mensagem = mensagem + "-Já existe um usuário cadastrado com esse login\n";
                }
            }
        } catch (Exception e) {
            mensagem = mensagem + e;
        }
        if (mensagem.length() < 25) {

            if (usuarioDAO.salvar(usuario)) {
                return "ok";
            } else {
                return mensagem;
            }
        } else {
            return mensagem;
        }
    }

    public ArrayList<Usuario> listar(Usuario usuario) {
        this.usuario = usuario;
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.listar(usuario);

    }
}
