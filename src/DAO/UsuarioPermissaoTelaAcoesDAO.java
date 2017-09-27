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
public class UsuarioPermissaoTelaAcoesDAO extends DAO {

   
      UsuarioPermissaoTelaAcoes userPerTela;

    public ArrayList<UsuarioPermissaoTelaAcoes> listarTodos() {
        this.userPerTela = userPerTela;
        List resultado = null;

        ArrayList<UsuarioPermissaoTelaAcoes> lista = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql = "from usuario_permissao_tela_acao";

            org.hibernate.Query q = session.createQuery(sql);

            resultado = q.list();

            for (Object o : resultado) {
                UsuarioPermissaoTelaAcoes cid = ((UsuarioPermissaoTelaAcoes) ((Object) o));
                lista.add(cid);
            }

        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return lista;
    }
    

}
