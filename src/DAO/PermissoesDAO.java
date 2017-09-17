/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import apoio.HibernateUtil;
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

public class PermissoesDAO {
    Usuario usuario;
    public ArrayList<UsuarioPermissaoTelaAcoes> listarPermissoes(Usuario usuario) {
        this.usuario = usuario;
        List resultado = null;

         ArrayList<UsuarioPermissaoTelaAcoes> listaPermissoes = new ArrayList<>();
        try {
           Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
          
            String hql;          
                hql = "from Usuario u, UsuarioPermissaoTela upt, UsuarioPermissaoTelaAcoes upta "
                        +"where u.id="+usuario.getId();
             Query query = session.createQuery(hql);
            List<Object[]> listResult = query.list();

            for (Object[] aRow : listResult) {
                Usuario usu = (Usuario) aRow[0];
                UsuarioPermissaoTela usuPermissaoTela = (UsuarioPermissaoTela) aRow[1];
                UsuarioPermissaoTelaAcoes usuPermissaoTelaAcoes = (UsuarioPermissaoTelaAcoes) aRow[2];

                
                listaPermissoes.add(usuPermissaoTelaAcoes);
            }

        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return listaPermissoes;

    }
}
