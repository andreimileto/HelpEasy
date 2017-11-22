/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import apoio.Formatacao;
import apoio.HibernateUtil;
import entidade.MovimentoTarefa;
import janelas.TelaPrincipal;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author User
 */
public class MovimentoTarefaDAO {

    MovimentoTarefa movimentoTarefa;

    public boolean salvar(MovimentoTarefa movimentoTarefa) throws IOException {

        Session session = HibernateUtil.getSessionFactory().openSession();
        boolean retorno = false;
        try {

            String sAnexo;
            sAnexo = movimentoTarefa.getAnexo();
            movimentoTarefa.setAnexo(sAnexo.substring(sAnexo.lastIndexOf("\\") + 1, sAnexo.length()));

            session = HibernateUtil.getSessionFactory().openSession();

            Transaction t = session.beginTransaction();
            Query query = session.createSQLQuery("set session \"myapp.user\" = " + TelaPrincipal.userH.getId());
            query.executeUpdate();

            session.merge(movimentoTarefa);
            t.commit();
            retorno = true;

            if (movimentoTarefa.getId() < 1) {
                int id = ultimoId();
                movimentoTarefa.setId(id);
                File patch = new File("tarefas");
                File diretorio = new File(patch.getAbsolutePath() + "\\" + movimentoTarefa.getTarefa().getId() + "\\" + id + "_" + movimentoTarefa.getAnexo());
                //s.substring(s.lastIndexOf("\\")+1,s.length());
                if (movimentoTarefa.getAnexo().length() > 0) {
                    Formatacao.copyFile(new File(sAnexo), diretorio);
                }

            }

        } catch (HibernateException he) {
            he.printStackTrace();
        } finally {
            session.close();
        }

        return retorno;
    }

    public ArrayList<MovimentoTarefa> listar(MovimentoTarefa movimentoTarefa) {
        this.movimentoTarefa = movimentoTarefa;
        List resultado = null;

        ArrayList<MovimentoTarefa> lista = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql = "";
            sql = "from MovimentoTarefa "
                    + "where 1=1 ";
            
            if (movimentoTarefa.getId() > 0 ) {
                sql = sql + " and id = " + movimentoTarefa.getId() + " ";
            }
            
            if (!(movimentoTarefa.getTarefa() == null)) {
                if (movimentoTarefa.getTarefa().getId() > 0) {
                    sql = sql + " and id_tarefa = " + movimentoTarefa.getTarefa().getId() + " ";
                }
            }
            sql = sql + " and upper(descricao) like '%" + movimentoTarefa.getDescricao().toUpperCase() + "%'"; 
            sql = sql + " and situacao = 'A' "
                    + "order by id desc";

            String sel = sql;

            org.hibernate.Query q = session.createQuery(sql);

            resultado = q.list();

            for (Object o : resultado) {
                MovimentoTarefa tar = ((MovimentoTarefa) ((Object) o));
                lista.add(tar);
            }

        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return lista;
    }
    
    public ArrayList<MovimentoTarefa> listarDescricao(MovimentoTarefa movimentoTarefa) {
        this.movimentoTarefa = movimentoTarefa;
        List resultado = null;

        ArrayList<MovimentoTarefa> lista = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql = "";
            sql = "from MovimentoTarefa "
                    + "where 1=1 "
                    + " and upper(descricao) like '%" + movimentoTarefa.getDescricao().toUpperCase() + "%'"
                    + " and situacao = 'A' ";
             if (!(movimentoTarefa.getTarefa() == null)) {
                if (movimentoTarefa.getTarefa().getId() > 0) {
                    sql = sql + " and id_tarefa = " + movimentoTarefa.getTarefa().getId() + " ";
                }
            }
                    sql = sql + "order by id desc";

            
            String sel = sql;

            org.hibernate.Query q = session.createQuery(sql);

            resultado = q.list();

            for (Object o : resultado) {
                MovimentoTarefa tar = ((MovimentoTarefa) ((Object) o));
                lista.add(tar);
            }

        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return lista;
    }

    public int ultimoId() {

        List resultado = null;
        int id = 0;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String sql = "";

            sql = "select max(id) from MovimentoTarefa  ";

            String sel = sql;
            org.hibernate.Query q = session.createQuery(sql);
            resultado = q.list();

            for (Object o : resultado) {
                id = ((Integer) ((Object) o));
            }

        } catch (HibernateException he) {
            he.printStackTrace();
        }

        return id;
    }
}
