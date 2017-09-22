/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import apoio.HibernateUtil;
import entidade.Usuario;
import entidade.UsuarioPermissaoTela;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Mileto
 */

public class PermissoesDAOTela {
    Usuario usuario;
    public ArrayList<UsuarioPermissaoTela> listarPermissoes(Usuario usuario) {
        this.usuario = usuario;
        List resultado = null;
        
        ArrayList<UsuarioPermissaoTela> lista = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql = "";
            sql = "from UsuarioPermissaoTela where id_usuario="+usuario.getId();
            
            org.hibernate.Query q = session.createQuery(sql);
            resultado = q.list();

            for (Object o : resultado) {
                UsuarioPermissaoTela tela = ((UsuarioPermissaoTela) ((Object) o));
                lista.add(tela);
            }

        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return lista;

    }
}
