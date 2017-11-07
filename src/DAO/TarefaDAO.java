/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import apoio.Formatacao;
import apoio.HibernateUtil;
import entidade.Tarefa;
import janelas.TelaPrincipal;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Mileto
 */
public class TarefaDAO extends DAO {

    Tarefa tarefa;
    
    public boolean salvar(Tarefa tarefa) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        boolean retorno = false;
        try {

            session = HibernateUtil.getSessionFactory().openSession();

            Transaction t = session.beginTransaction();
            Query query = session.createSQLQuery("set session \"myapp.user\" = " + TelaPrincipal.userH.getId());
            query.executeUpdate();

            session.merge(tarefa);
            t.commit();
            retorno = true;
            
            if (tarefa.getId()<1) {
            int id = ultimoId(tarefa);    
            Formatacao.criarDiretorioTarefa(id + "");
            }
            

        } catch (HibernateException he) {
            //janelas.TelaPrincipal.logH.gravaErro(o.getClass().getName(),janelas.TelaPrincipal.userH.getLogin(),"err");
            he.printStackTrace();
        } finally {
            session.close();
        }

        return retorno;
    }
    

    public ArrayList<Tarefa> listar(Tarefa tarefa) {
        this.tarefa = tarefa;
        List resultado = null;

        ArrayList<Tarefa> lista = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql = "";

  //          if (tarefa.getId() == 0) {

                sql = "from Tarefa "
                        + "where 1=1 ";
                
                if (tarefa.getId()>0) {
                sql = sql + " and id = "+tarefa.getId()+" ";
            }
                
                if (tarefa.getCliente().getId()>0) {
                sql = sql + " and id_cliente = "+tarefa.getCliente().getId()+" ";
            }
                if (tarefa.getFase().getId()>0) {
                sql = sql + " and id_fase = "+tarefa.getFase().getId()+" ";
            }

                 if (tarefa.getModulo().getId()>0) {
                sql = sql + " and id_modulo = "+tarefa.getModulo().getId()+" ";
            }
                  if (tarefa.getMotivo().getId()>0) {
                sql = sql + " and id_motivo = "+tarefa.getMotivo().getId()+" ";
            }
                  
                   if (tarefa.getPrioridade().getId()>0) {
                sql = sql + " and id_prioridade = "+tarefa.getPrioridade().getId()+" ";
            }
                    if (tarefa.getProjeto().getId()>0) {
                sql = sql + " and id_projeto = "+tarefa.getProjeto().getId()+" ";
            }
                  
                     if (tarefa.getUsuarioByIdUsuarioAutor().getId()>0) {
                sql = sql + " and id_usuario_autor = "+tarefa.getUsuarioByIdUsuarioAutor().getId()+" ";
            }
                     if (tarefa.getUsuarioByIdUsuarioResponsavel().getId()>0) {
                sql = sql + " and id_usuario_responsavel = "+tarefa.getUsuarioByIdUsuarioResponsavel().getId()+" ";
            }
                     
                       if (tarefa.getVersaoByIdVersaoBug().getId()>0) {
                sql = sql + " and id_versao_bug = "+tarefa.getVersaoByIdVersaoBug().getId()+" ";
            }
                     if (tarefa.getVersaoByIdVersaoCorrecao().getId()>0) {
                sql = sql + " and id_versao_correcao = "+tarefa.getVersaoByIdVersaoCorrecao().getId()+" ";
            }
                     
                     sql = sql + " and (upper (titulo) like '%"+tarefa.getTitulo().toUpperCase()+"%'"
                             + " or upper (descricao) like '%"+tarefa.getDescricao().toUpperCase()+"%')";
                  
            String sel = sql;
         
            org.hibernate.Query q = session.createQuery(sql);

            resultado = q.list();

            for (Object o : resultado) {
                Tarefa tar = ((Tarefa) ((Object) o));
                lista.add(tar);
            }

        } catch (HibernateException he) {
            he.printStackTrace();
        }// finally {
//            session.close();
//        }
        return lista;
    }
    public int ultimoId(Tarefa tarefa) {

        this.tarefa = tarefa;
        List resultado = null;
        ArrayList<Tarefa> lista = new ArrayList<>();
        int id = 0;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String sql = "";

            sql = "select max(id) from Tarefa  ";

            String sel = sql;
            // System.out.println(sel + " select cliente");
            org.hibernate.Query q = session.createQuery(sql);
            resultado = q.list();
          
            for (Object o : resultado) {
                 id = ((Integer) ((Object) o));
                //lista.add(tar);
            }

//
        } catch (HibernateException he) {
            he.printStackTrace();
        }// finally {
//            session.close();

        return id;
    }
    
     public ArrayList<Tarefa> listarUmId(Tarefa tarefa) {
        this.tarefa = tarefa;
        List resultado = null;

        ArrayList<Tarefa> lista = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql = "";

  //          if (tarefa.getId() == 0) {

                sql = "from Tarefa where id =  "+tarefa.getId();

            String sel = sql;
         
            org.hibernate.Query q = session.createQuery(sql);

            resultado = q.list();

            for (Object o : resultado) {
                Tarefa tar = ((Tarefa) ((Object) o));
                lista.add(tar);
            }

        } catch (HibernateException he) {
            he.printStackTrace();
        }// finally {
//            session.close();
//        }
        return lista;
    }
}
