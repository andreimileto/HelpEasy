/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import apoio.HibernateUtil;
import entidade.Cliente;
import entidade.Motivo;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Mileto
 */
public class ClienteDAO extends DAO {

    Cliente cliente;

    public ArrayList<Cliente> listar(Cliente cliente) {
        this.cliente = cliente;
        List resultado = null;

        ArrayList<Cliente> lista = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql = "";

            if (cliente.getId() == 0) {

                sql = "from Cliente ";
                        /*+ "where (upper(razao_social)  like '%" + cliente.getRazaoSocial().toUpperCase() + "%' "
                        + "or cpf_cnpj like '%" + cliente.getCpfCnpj().replace(".", "").replace("-", "").replace("/", "") + "%')"
                        + "and situacao ='A'"
                        + " order by razao_social";*/
            } else {
                sql = "from Cliente  "
                        + "where (upper(razao_social)  like '%" + cliente.getRazaoSocial().toUpperCase() + "%' "
                        + "or cpf_cnpj like '%" + cliente.getCpfCnpj().replace(".", "").replace("-", "").replace("/", "") + "%')"
                        + "and situacao ='A'"
                        + "and id = " + cliente.getId()
                        + " order by razao_social";
            }
            String sel = sql;
            System.out.println(sel + " select cliente");
            org.hibernate.Query q = session.createQuery(sql);

            resultado = q.list();

            for (Object o : resultado) {
                Cliente cli = ((Cliente) ((Object) o));
                lista.add(cli);
            }

        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return lista;
    }
    
    
    public ArrayList<Cliente> listarTodos() {
        this.cliente = cliente;
        List resultado = null;

        ArrayList<Cliente> lista = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql = "";

                sql = "from Cliente where situacao ='A'";
            String sel = sql;
            System.out.println(sel + " select cliente");
            org.hibernate.Query q = session.createQuery(sql);

            resultado = q.list();

            for (Object o : resultado) {
                Cliente cli = ((Cliente) ((Object) o));
                lista.add(cli);
            }

        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return lista;
    }

}
