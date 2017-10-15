/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import apoio.HibernateUtil;
import entidade.Versao;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Mileto
 */
public class VersaoDAO extends DAO{

    Versao versao;
     public ArrayList<Versao> listar(Versao versao) {
        this.versao = versao;
        List resultado = null;

        ArrayList<Versao> lista = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql = "from versao  "                    
                    + "where upper(descricao)  like '" + versao.getDescricao().toUpperCase() + "%' "
                    + "and situacao ='A'"
                    + " order by descricao";
            String sel = sql;
            System.out.println(sel);
            org.hibernate.Query q = session.createQuery(sql);

            resultado = q.list();

            for (Object o : resultado) {
                Versao ver = ((Versao) ((Object) o));
                lista.add(ver);
            }

        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return lista;
    }
    
}
