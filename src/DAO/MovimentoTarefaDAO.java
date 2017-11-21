/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import apoio.HibernateUtil;
import entidade.MovimentoTarefa;
import janelas.TelaPrincipal;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author User
 */
public class MovimentoTarefaDAO {

    MovimentoTarefa movimentoTarefa;

    public boolean salvar(MovimentoTarefa movimentoTarefa) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        boolean retorno = false;
        try {

            session = HibernateUtil.getSessionFactory().openSession();

            Transaction t = session.beginTransaction();
            Query query = session.createSQLQuery("set session \"myapp.user\" = " + TelaPrincipal.userH.getId());
            query.executeUpdate();

            session.merge(movimentoTarefa);
            t.commit();
            retorno = true;

        } catch (HibernateException he) {
            he.printStackTrace();
        } finally {
            session.close();
        }

        return retorno;
    }

    public ArrayList<MovimentoTarefa> listar(MovimentoTarefa movimentoTarefa) {
        this.movimentoTarefa = movimentoTarefa;
        List resultado = null;

        ArrayList<MovimentoTarefa> lista = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql = "";
            sql = "from MovimentoTarefa "
                    + "where 1=1 "
                    + " and situacao = 'A' "
                    + "order by id";

            String sel = sql;

            org.hibernate.Query q = session.createQuery(sql);

            resultado = q.list();

            for (Object o : resultado) {
                MovimentoTarefa tar = ((MovimentoTarefa) ((Object) o));
                lista.add(tar);
            }

        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return lista;
    }
}
