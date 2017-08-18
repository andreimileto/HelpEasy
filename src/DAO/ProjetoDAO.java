/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import apoio.HibernateUtil;

import entidade.Projeto;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Mileto
 */
public class ProjetoDAO extends DAO {

    Projeto projeto;

    public ArrayList<Projeto> listar(Projeto projeto) {
        this.projeto = projeto;
        List resultado = null;

        ArrayList<Projeto> lista = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql = "from Projeto  "
                    + "where upper(descricao)  like '" + projeto.getDescricao().toUpperCase() + "%' "
                    + "and situacao ='A'"
                    + " order by descricao";
            String sel = sql;
            System.out.println(sel);
            org.hibernate.Query q = session.createQuery(sql);

            resultado = q.list();

            for (Object o : resultado) {
                Projeto proj = ((Projeto) ((Object) o));
                lista.add(proj);
            }

        } catch (HibernateException he) {
            he.printStackTrace();
        }// finally {
//            session.close();
//        }
        return lista;
    }

}
