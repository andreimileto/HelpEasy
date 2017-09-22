/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import DAO.FaseDAO;
import DAO.ProjetoDAO;
import DAO.UsuarioDAO;
import DAO.UsuarioPermissaoTelaAcoesDAO;
import DAO.UsuarioPermissaoTelaDAO;
import apoio.Formatacao;
import entidade.Fase;

import entidade.Usuario;
import entidade.UsuarioPermissaoTela;
import entidade.UsuarioPermissaoTelaAcoes;
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

        if (usuario.getSenha().length() < 6) {
            mensagem = mensagem + "-Senha deve conter no mínimo 6 caracteres\n";
        } else {
            usuario.setSenha(Formatacao.getSenhaMD5(usuario.getSenha()));
        }

        usuarios = usuarioDAO.listar(usuario);
        try {
            if (usuarios.size() > 0) {
                if (usuarios.get(0).getLogin().equals(usuario.getLogin()) && usuario.getId() != usuarios.get(0).getId()) {

                    mensagem = mensagem + "-Já existe um usuário cadastrado com esse login\n";
                }
            }
        } catch (Exception e) {
            mensagem = mensagem + e;
        }
        if (mensagem.length() < 25) {

            if (usuarioDAO.salvar(usuario)) {
//                try {
//                    UsuarioPermissaoTela usuarioPermissaoTela = new UsuarioPermissaoTela();
//                UsuarioPermissaoTelaAcoes usuarioPermissaoTelaAcoes = new UsuarioPermissaoTelaAcoes();
//                UsuarioPermissaoTelaDAO usuarioPermissaoTelaDAO = new UsuarioPermissaoTelaDAO();
//                UsuarioPermissaoTelaAcoesDAO usuarioPermissaoTelaAcoesDAO = new UsuarioPermissaoTelaAcoesDAO();
//                usuarioPermissaoTela.setPermiteAcesso(true);
//                usuarioPermissaoTelaAcoes.setPermiteAcesso(true);
//                usuarioPermissaoTelaAcoes.setId(0);
//                usuarioPermissaoTela.setUsuario(usuario);
//                
//                usuarioPermissaoTela.setTela("janelas.JdgCadastroCidade");
//                usuarioPermissaoTelaDAO.salvar(usuarioPermissaoTela);
//                
//                usuarioPermissaoTelaAcoes.setAcao("btnSalvar");
//                usuarioPermissaoTelaAcoes.setUsuarioPermissaoTela(usuarioPermissaoTela);
//                usuarioPermissaoTelaAcoesDAO.salvar(usuarioPermissaoTelaAcoes);
//                usuarioPermissaoTelaAcoes.setId(0);
//                usuarioPermissaoTelaAcoes.setAcao("btnLocalizar");
//                usuarioPermissaoTelaAcoesDAO.salvar(usuarioPermissaoTelaAcoes);
//                usuarioPermissaoTelaAcoes.setId(0);
//                usuarioPermissaoTelaAcoes.setAcao("btnExcluir");
//                usuarioPermissaoTelaAcoesDAO.salvar(usuarioPermissaoTelaAcoes);
//                usuarioPermissaoTelaAcoes.setId(0);
//                
//                usuarioPermissaoTela.setId(0);
//                usuarioPermissaoTela.setTela("janelas.JdgAlteracaoSenha");
//                usuarioPermissaoTelaDAO.salvar(usuarioPermissaoTela);
//                usuarioPermissaoTelaAcoes.setAcao("btnSalvar");
//                usuarioPermissaoTelaAcoesDAO.salvar(usuarioPermissaoTelaAcoes);
//                usuarioPermissaoTelaAcoes.setId(0);
//                usuarioPermissaoTela.setId(0);
//                
//                
//                usuarioPermissaoTela.setTela("janelas.JdgParametrosSistema");
//                usuarioPermissaoTelaDAO.salvar(usuarioPermissaoTela);
//                usuarioPermissaoTelaAcoes.setAcao("btnEnableAuditoria");
//                usuarioPermissaoTelaAcoes.setUsuarioPermissaoTela(usuarioPermissaoTela);
//                usuarioPermissaoTelaAcoesDAO.salvar(usuarioPermissaoTelaAcoes);
//                usuarioPermissaoTelaAcoes.setId(0);
//                usuarioPermissaoTelaAcoes.setAcao("btnDisableAuditoria");
//                usuarioPermissaoTelaAcoesDAO.salvar(usuarioPermissaoTelaAcoes);
//                usuarioPermissaoTelaAcoes.setId(0);
//                usuarioPermissaoTela.setId(0);
//                
//                
//                
//                usuarioPermissaoTela.setTela("janelas.JdgCadastroCliente");
//                usuarioPermissaoTelaDAO.salvar(usuarioPermissaoTela);
//                usuarioPermissaoTelaAcoes.setAcao("btnSalvar");
//                usuarioPermissaoTelaAcoes.setUsuarioPermissaoTela(usuarioPermissaoTela);
//                usuarioPermissaoTelaAcoesDAO.salvar(usuarioPermissaoTelaAcoes);
//                usuarioPermissaoTelaAcoes.setId(0);
//                usuarioPermissaoTelaAcoes.setAcao("btnLocalizar");
//                usuarioPermissaoTelaAcoesDAO.salvar(usuarioPermissaoTelaAcoes);
//                usuarioPermissaoTelaAcoes.setId(0);
//                usuarioPermissaoTelaAcoes.setAcao("btnExcluir");
//                usuarioPermissaoTelaAcoesDAO.salvar(usuarioPermissaoTelaAcoes);
//                usuarioPermissaoTelaAcoes.setId(0);
//                usuarioPermissaoTela.setId(0);
//                
//                
//                
//                usuarioPermissaoTela.setTela("janelas.JdgCadastroProjeto");
//                usuarioPermissaoTelaDAO.salvar(usuarioPermissaoTela);
//                usuarioPermissaoTelaAcoes.setAcao("btnSalvar");
//                usuarioPermissaoTelaAcoes.setUsuarioPermissaoTela(usuarioPermissaoTela);
//                usuarioPermissaoTelaAcoesDAO.salvar(usuarioPermissaoTelaAcoes);
//                usuarioPermissaoTelaAcoes.setId(0);
//                usuarioPermissaoTelaAcoes.setAcao("btnLocalizar");
//                usuarioPermissaoTelaAcoesDAO.salvar(usuarioPermissaoTelaAcoes);
//                usuarioPermissaoTelaAcoes.setId(0);
//                usuarioPermissaoTelaAcoes.setAcao("btnExcluir");
//                usuarioPermissaoTelaAcoesDAO.salvar(usuarioPermissaoTelaAcoes);
//                usuarioPermissaoTelaAcoes.setId(0);
//                usuarioPermissaoTela.setId(0);
//                
//                
//                usuarioPermissaoTela.setTela("janelas.JdgCadastroMotivo");
//                usuarioPermissaoTelaDAO.salvar(usuarioPermissaoTela);
//                usuarioPermissaoTelaAcoes.setAcao("btnSalvar");
//                usuarioPermissaoTelaAcoes.setUsuarioPermissaoTela(usuarioPermissaoTela);
//                usuarioPermissaoTelaAcoesDAO.salvar(usuarioPermissaoTelaAcoes);
//                usuarioPermissaoTelaAcoes.setId(0);
//                usuarioPermissaoTelaAcoes.setAcao("btnLocalizar");
//                usuarioPermissaoTelaAcoesDAO.salvar(usuarioPermissaoTelaAcoes);
//                usuarioPermissaoTelaAcoes.setId(0);
//                usuarioPermissaoTelaAcoes.setAcao("btnExcluir");
//                usuarioPermissaoTelaAcoesDAO.salvar(usuarioPermissaoTelaAcoes);
//                usuarioPermissaoTelaAcoes.setId(0);
//                usuarioPermissaoTela.setId(0);
//                
//                
//                usuarioPermissaoTela.setTela("janelas.JdgCadastroUsuario");
//                usuarioPermissaoTelaDAO.salvar(usuarioPermissaoTela);
//                usuarioPermissaoTelaAcoes.setAcao("btnSalvar");
//                usuarioPermissaoTelaAcoes.setUsuarioPermissaoTela(usuarioPermissaoTela);
//                usuarioPermissaoTelaAcoesDAO.salvar(usuarioPermissaoTelaAcoes);
//                usuarioPermissaoTelaAcoes.setId(0);
//                usuarioPermissaoTelaAcoes.setAcao("btnLocalizar");
//                usuarioPermissaoTelaAcoesDAO.salvar(usuarioPermissaoTelaAcoes);
//                usuarioPermissaoTelaAcoes.setId(0);
//                usuarioPermissaoTelaAcoes.setAcao("btnExcluir");
//                usuarioPermissaoTelaAcoesDAO.salvar(usuarioPermissaoTelaAcoes);
//                usuarioPermissaoTelaAcoes.setId(0);
//                usuarioPermissaoTela.setId(0);
//                
//                
//                usuarioPermissaoTela.setTela("janelas.JdgCadastroPrioridade");
//                usuarioPermissaoTelaDAO.salvar(usuarioPermissaoTela);
//                usuarioPermissaoTelaAcoes.setAcao("btnSalvar");
//                usuarioPermissaoTelaAcoes.setUsuarioPermissaoTela(usuarioPermissaoTela);
//                usuarioPermissaoTelaAcoesDAO.salvar(usuarioPermissaoTelaAcoes);
//                usuarioPermissaoTelaAcoes.setId(0);
//                usuarioPermissaoTelaAcoes.setAcao("btnLocalizar");
//                usuarioPermissaoTelaAcoesDAO.salvar(usuarioPermissaoTelaAcoes);
//                usuarioPermissaoTelaAcoes.setId(0);
//                usuarioPermissaoTelaAcoes.setAcao("btnExcluir");
//                usuarioPermissaoTelaAcoesDAO.salvar(usuarioPermissaoTelaAcoes);
//                usuarioPermissaoTelaAcoes.setId(0);
//                usuarioPermissaoTela.setId(0);
//                
//                
//                usuarioPermissaoTela.setTela("janelas.JdgCadastroFase");
//                usuarioPermissaoTelaDAO.salvar(usuarioPermissaoTela);
//                usuarioPermissaoTelaAcoes.setAcao("btnSalvar");
//                usuarioPermissaoTelaAcoes.setUsuarioPermissaoTela(usuarioPermissaoTela);
//                usuarioPermissaoTelaAcoesDAO.salvar(usuarioPermissaoTelaAcoes);
//                usuarioPermissaoTelaAcoes.setId(0);
//                usuarioPermissaoTelaAcoes.setAcao("btnLocalizar");
//                usuarioPermissaoTelaAcoesDAO.salvar(usuarioPermissaoTelaAcoes);
//                usuarioPermissaoTelaAcoes.setId(0);
//                usuarioPermissaoTelaAcoes.setAcao("btnExcluir");
//                usuarioPermissaoTelaAcoesDAO.salvar(usuarioPermissaoTelaAcoes);
//                usuarioPermissaoTelaAcoes.setId(0);
//                usuarioPermissaoTela.setId(0);
//                
//                
//                
//                usuarioPermissaoTela.setTela("janelas.JdgPermissoesUsuario");
//                usuarioPermissaoTelaDAO.salvar(usuarioPermissaoTela);
//                usuarioPermissaoTelaAcoes.setAcao("btnSalvar");
//                usuarioPermissaoTelaAcoes.setUsuarioPermissaoTela(usuarioPermissaoTela);
//                usuarioPermissaoTelaAcoesDAO.salvar(usuarioPermissaoTelaAcoes);
//                usuarioPermissaoTelaAcoes.setId(0);
//                usuarioPermissaoTela.setId(0);
//                } catch (Exception e) {
//                    mensagem = mensagem+"Erro ao gravar as permissões do usuário\n"+e;
//                    return mensagem;
//                }
//                
                
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
