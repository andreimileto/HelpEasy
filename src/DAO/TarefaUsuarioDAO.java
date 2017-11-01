/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import apoio.HibernateUtil;
import entidade.TarefaUsuario;
import entidade.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Mileto
 */
public class TarefaUsuarioDAO extends DAO {

    TarefaUsuario tarefaUsuario;
    List<Object[]> listResult;

    public ArrayList<TarefaUsuario> listar(TarefaUsuario tarefaUsuario) {
        this.tarefaUsuario = tarefaUsuario;
        List resultado = null;

        ArrayList<TarefaUsuario> lista = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql = "";
    sql = "from TarefaUsuario "
            + " where id_tarefa = "+tarefaUsuario.getTarefa().getId();
            String sel = sql;
            System.out.println(sel);
            org.hibernate.Query q = session.createQuery(sql);

            resultado = q.list();

            for (Object o : resultado) {
                TarefaUsuario user = ((TarefaUsuario) ((Object) o));
                lista.add(user);
            }

        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return lista;
    }
    
//    public ArrayList<Usuario> listarTodos() {
//        
//        List resultado = null;
//
//        ArrayList<Usuario> lista = new ArrayList<>();
//        try {
//            Session session = HibernateUtil.getSessionFactory().openSession();
//            session.beginTransaction();
//            String sql = "";
//
//           
//                sql = "from Usuario  "
//                        + "where "
//                        + " situacao ='A'";
//           
//            String sel = sql;
//            System.out.println(sel);
//            org.hibernate.Query q = session.createQuery(sql);
//
//            resultado = q.list();
//
//            for (Object o : resultado) {
//                Usuario user = ((Usuario) ((Object) o));
//                lista.add(user);
//            }
//
//        } catch (HibernateException he) {
//            he.printStackTrace();
//        }
//        return lista;
//    }
//    
    

}
