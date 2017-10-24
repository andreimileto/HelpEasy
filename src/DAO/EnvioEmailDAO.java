/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import apoio.IDAO;
import apoio.HibernateUtil;
import entidade.Cidade;
import entidade.EnvioEmail;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Mileto
 */
public class EnvioEmailDAO  extends DAO{

    EnvioEmail envioEmail;
    
 public ArrayList<EnvioEmail> listar(EnvioEmail envioEmail) {
        this.envioEmail=envioEmail;
        List resultado = null;

        ArrayList<EnvioEmail> lista = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql = "from EnvioEmail";
                    
            String sel = sql;
            System.out.println(sel);
            org.hibernate.Query q = session.createQuery(sql);

            resultado = q.list();

            for (Object o : resultado) {
                EnvioEmail email = ((EnvioEmail) ((Object) o));
                lista.add(email);
            }

        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return lista;
    }

}

