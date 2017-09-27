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
public class CidadeDAO  extends DAO{

    Cidade cidade;
    
 public ArrayList<Cidade> listar(Cidade cidade) {
        this.cidade=cidade;
        List resultado = null;

        ArrayList<Cidade> lista = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql = "from Cidade  "
                    + "where upper(descricao)  like '"+cidade.getDescricao().toUpperCase()+"%' "
                    + "and situacao ='A'"
                    + " order by descricao";
            String sel = sql;
            System.out.println(sel);
            org.hibernate.Query q = session.createQuery(sql);

            resultado = q.list();

            for (Object o : resultado) {
                Cidade cid = ((Cidade) ((Object) o));
                lista.add(cid);
            }

        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return lista;
    }

}

