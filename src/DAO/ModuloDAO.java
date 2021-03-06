/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import apoio.HibernateUtil;
import entidade.Modulo;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Mileto
 */
public class ModuloDAO extends DAO {

    Modulo modulo;

    public ArrayList<Modulo> listar(Modulo modulo) {
        this.modulo = modulo;
        List resultado = null;

        ArrayList<Modulo> lista = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql = "";
            try {

                if (modulo.getProjeto().getId() < 1) {
                    if (modulo.getId() == 0) {
                        sql = "from Modulo "
                                + "where upper(descricao)  like '" + modulo.getDescricao().toUpperCase() + "%' "
                                + "and situacao ='A'"
                                + " order by descricao";
                    } else {
                        sql = "from Modulo "
                                + "where id = " + modulo.getId()
                                + "and situacao ='A'"
                                + " order by descricao";
                    }

                } else {
                    if (modulo.getId() == 0) {
                        sql = "from Modulo "
                                + " where upper(descricao)  like '" + modulo.getDescricao().toUpperCase() + "%' "
                                + " and id_projeto = " + modulo.getProjeto().getId()
                                + " and situacao ='A'"
                                + " order by descricao";
                    } else {
                        sql = "from Modulo "
                                + " where id = " + modulo.getId()
                                + " and id_projeto = " + modulo.getProjeto().getId()
                                + " and situacao ='A'"
                                + " order by descricao";
                    }
                }
            } catch (Exception e) {
                if (modulo.getId() == 0) {
                    sql = "from Modulo "
                            + "where upper(descricao)  like '" + modulo.getDescricao().toUpperCase() + "%' "
                            + "and situacao ='A'"
                            + " order by descricao";
                } else {
                    sql = "from Modulo "
                            + "where id = " + modulo.getId()
                            + "and situacao ='A'"
                            + " order by descricao";
                }
            }
            String sel = sql;
            System.out.println(sel);
            org.hibernate.Query q = session.createQuery(sql);

            resultado = q.list();

            for (Object o : resultado) {
                Modulo mod = ((Modulo) ((Object) o));
                lista.add(mod);
            }

        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return lista;
    }

}
