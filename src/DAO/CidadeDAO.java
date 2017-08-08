/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import apoio.IDAO;
import apoio.NewHibernateUtil;
import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Mileto
 */
public class CidadeDAO implements IDAO{

    @Override
    public boolean salvar(Object o) {
         Session session = NewHibernateUtil.getSessionFactory().openSession();
        boolean retorno = false;
        try {

            session = NewHibernateUtil.getSessionFactory().openSession();

            Transaction t = session.beginTransaction();

            session.saveOrUpdate(o);

            t.commit();

            retorno = true;
        } catch (HibernateException he) {
//            LogHelper.makeLog(this, "algo de errado ocorreu" + he.getMessage());
            he.printStackTrace();
        } finally {
            session.close();
        }
        return retorno;
    }

    @Override
    public ArrayList<Object> consultar(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
