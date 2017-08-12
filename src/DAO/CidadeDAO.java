/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import apoio.IDAO;
import apoio.HibernateUtil;
import entidade.Cidade;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Mileto
 */
public class CidadeDAO implements IDAO {

    Cidade cidade;
    
    @Override
    public boolean salvar(Object o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        boolean retorno = false;
        try {

            session = HibernateUtil.getSessionFactory().openSession();

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.F
    }

    public ArrayList<Cidade> listar(Cidade cidade) {
        this.cidade=cidade;
        List resultado = null;

        ArrayList<Cidade> lista = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql = "from Cidade  "
//                    + "where 1=1 "
//                    + parametro
                    + "where upper(descricao)  like '"+cidade.getDescricao().toUpperCase()+"%' "
                    + "and situacao ='A'"
                    + " order by descricao";
            String sel = sql;
            System.out.println(sel);
org.hibernate.Query q = session.createQuery(sql);
//            System.out.println(sql);
//            org.hibernate.Query q = session.createQuery(sql);
            resultado = q.list();
//            System.out.println(q);
            for (Object o : resultado) {
                Cidade cid = ((Cidade) ((Object) o));
                lista.add(cid);
            }

        } catch (HibernateException he) {
            he.printStackTrace();
        }// finally {
//            session.close();
//        }
        return lista;
    }

}

