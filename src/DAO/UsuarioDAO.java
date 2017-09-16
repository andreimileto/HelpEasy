/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import apoio.HibernateUtil;
import entidade.Modulo;
import entidade.Projeto;
import entidade.Usuario;
import entidade.UsuarioPermissaoTela;
import entidade.UsuarioPermissaoTelaAcoes;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Mileto
 */
public class UsuarioDAO extends DAO {

    Usuario usuario;
    List<Object[]> listResult;

    public ArrayList<Usuario> listar(Usuario usuario) {
        this.usuario = usuario;
        List resultado = null;

        ArrayList<Usuario> lista = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql = "";

            if (usuario.getLogin().equals(usuario.getNome())) {
                sql = "from Usuario  "
                        + "where (upper(login) like '%" + usuario.getLogin().toUpperCase() + "%'"
                        + "or upper(nome) like '%" + usuario.getNome().toUpperCase() + "%')"
                        + "and situacao ='A'";
            } else {

                sql = "from Usuario  "
                        + "where login = '" + usuario.getLogin() + "' "
                        + "and situacao ='A'";
            }
            String sel = sql;
            System.out.println(sel);
            org.hibernate.Query q = session.createQuery(sql);

            resultado = q.list();

            for (Object o : resultado) {
                Usuario user = ((Usuario) ((Object) o));
                lista.add(user);
            }

        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return lista;
    }

    public ArrayList<Usuario> listarPermissoes(Usuario usuario) {
        this.usuario = usuario;
        List resultado = null;

         ArrayList<Usuario> listaUsuario = new ArrayList<>();
        try {
           Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
          
            String hql;
//"SELECT pt.id_usuario,pt.tela,pt.permite_acesso permite_acesso_tela,pa.acao,pa.permite_acesso permite_acesso_acao "
//                                    + "FROM usuario_permissao_tela pt	"
//                                    + "INNER JOIN usuario_permissao_tela_acoes pa "
//                                    + "on pt.id = pa.id_usuario_permissao_tela "
//                                    + "WHERE pt.id_usuario = 1";
           
                hql = "from Usuario u, UsuarioPermissaoTela upt, UsuarioPermissaoTelaAcoes upta"
                        +"where u.id="+usuario.getId();
             Query query = session.createQuery(hql);
            List<Object[]> listResult = query.list();

            for (Object[] aRow : listResult) {
                Usuario usu = (Usuario) aRow[0];
                UsuarioPermissaoTela usuPermissaoTela = (UsuarioPermissaoTela) aRow[1];
                UsuarioPermissaoTelaAcoes usuPermissaoTelaAcoes = (UsuarioPermissaoTelaAcoes) aRow[2];

                
                listaUsuario.add(usu);
            }

        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return listaUsuario;

    }

}
