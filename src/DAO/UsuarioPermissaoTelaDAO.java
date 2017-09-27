/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import apoio.HibernateUtil;
import entidade.Modulo;
import entidade.Projeto;
import entidade.Usuario;
import entidade.UsuarioPermissaoTela;
import entidade.UsuarioPermissaoTelaAcoes;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Mileto
 */
public class UsuarioPermissaoTelaDAO extends DAO {

    UsuarioPermissaoTela userPerTela;

    public ArrayList<UsuarioPermissaoTela> listarTodos() {
        this.userPerTela = userPerTela;
        List resultado = null;

        ArrayList<UsuarioPermissaoTela> lista = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql = "from usuario_permissao_tela";

            org.hibernate.Query q = session.createQuery(sql);

            resultado = q.list();

            for (Object o : resultado) {
                UsuarioPermissaoTela cid = ((UsuarioPermissaoTela) ((Object) o));
                lista.add(cid);
            }

        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return lista;
    }

}
