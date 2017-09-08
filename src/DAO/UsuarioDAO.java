/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import apoio.HibernateUtil;
import entidade.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;


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
        } 
        return lista;
    }
}
