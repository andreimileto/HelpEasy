/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import apoio.HibernateUtil;
import entidade.Usuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;


/**
 *
 * @author Mileto
 */
public class UsuarioDAO extends DAO {

    Usuario usuario;

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
        }// finally {
//            session.close();
//        }
        return lista;
    }

        public void setaUsuarioConexao() {
         try {
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();

            sessao.doWork(new Work() {
                public void execute(Connection connection) throws SQLException {
                    CallableStatement call = connection.prepareCall("{ set app.usuario to '2'; }");
                    call.execute();
                }
            });

            sessao.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("erro da função: " + e);
        }
    }    
    public void chamarView() {
    try {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();

        List resultado = sessao.createSQLQuery("select * from teste").list();

        for (Object o : resultado) {
            System.out.println("nome: " + o.toString());
        }

        sessao.getTransaction().commit();

    } catch (Exception e) {
        System.out.println("erro ao chamar view: " + e);
    }
    }

}
