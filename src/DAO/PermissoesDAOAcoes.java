/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import apoio.HibernateUtil;
import entidade.Usuario;
import entidade.UsuarioPermissaoTelaAcoes;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Mileto
 */

public class PermissoesDAOAcoes {
    Usuario usuario;
    public ArrayList<UsuarioPermissaoTelaAcoes> listarPermissoes(Usuario usuario) {
        this.usuario = usuario;
        
        ArrayList<UsuarioPermissaoTelaAcoes> lista = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql = "";
            sql = "from UsuarioPermissaoTela upt, UsuarioPermissaoTelaAcoes upta "
                        +"where upt.id = id_tela "
                        +"and id_usuario="+usuario.getId()
                    +"order by id_tela";
            org.hibernate.Query q = session.createQuery(sql);

            List<Object[]> listResult = q.list();

            for (Object[] o : listResult) {
                UsuarioPermissaoTelaAcoes acoes = (UsuarioPermissaoTelaAcoes) o[1];
                lista.add(acoes);
            }

        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return lista;

    }
}
