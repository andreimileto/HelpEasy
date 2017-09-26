/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import apoio.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 *
 * @author Mileto
 */
public abstract class DAO {
     public boolean salvar(Object o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        boolean retorno = false;
        try {

            session = HibernateUtil.getSessionFactory().openSession();

            Transaction t = session.beginTransaction();

            session.merge(o);

            t.commit();

            retorno = true;
        } catch (HibernateException he) {
            //janelas.TelaPrincipal.logH.gravaErro(o.getClass().getName(),janelas.TelaPrincipal.userH.getLogin(),"err");
            he.printStackTrace();
        } 
        finally {
          //  session.close();
        }
        
        return retorno;
    }
}
