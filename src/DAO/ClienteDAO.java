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
            String sql = "from Cliente  "
                    + "where upper(razao_social)  like '%" + cliente.getRazaoSocial().toUpperCase() + "%' "
                    + "or cnpj_cpf like '%" + cliente.getCpfCnpj().replace(".", "").replace("-", "").replace("/", "") + "%'"
                    + "and situacao ='A'"
                    + " order by razao_social";
            String sel = sql;
            System.out.println(sel);
            org.hibernate.Query q = session.createQuery(sql);

            resultado = q.list();

            for (Object o : resultado) {
                Cliente cli = ((Cliente) ((Object) o));
                lista.add(cli);
            }

        } catch (HibernateException he) {
            he.printStackTrace();
        }// finally {
//            session.close();
//        }
        return lista;
    }
}