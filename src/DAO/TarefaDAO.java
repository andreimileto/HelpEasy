/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import apoio.ConexaoBD;
import apoio.Formatacao;
import apoio.HibernateUtil;
import entidade.Tarefa;
import janelas.TelaPrincipal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
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

            if (tarefa.getId() < 1) {
                int id = ultimoId(tarefa);
                Formatacao.criarDiretorioTarefa(id + "");
                tarefa.setId(id);
                
                gerarRelatorio(id);
               // gerarRelatorio(id);
            }else{
              Formatacao.criarDiretorioTarefa(tarefa.getId() + "");
                tarefa.setId(tarefa.getId());  
                
                gerarRelatorio(tarefa.getId());  
            }           
        } catch (HibernateException he) {
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

            if (tarefa.getId() > 0) {
                sql = sql + " and id = " + tarefa.getId() + " ";
            }

            if (tarefa.getCliente().getId() > 0) {
                sql = sql + " and id_cliente = " + tarefa.getCliente().getId() + " ";
            }
            if (tarefa.getFase().getId() > 0) {
                sql = sql + " and id_fase = " + tarefa.getFase().getId() + " ";
            }

            if (tarefa.getModulo().getId() > 0) {
                sql = sql + " and id_modulo = " + tarefa.getModulo().getId() + " ";
            }
            if (tarefa.getMotivo().getId() > 0) {
                sql = sql + " and id_motivo = " + tarefa.getMotivo().getId() + " ";
            }

            if (tarefa.getPrioridade().getId() > 0) {
                sql = sql + " and id_prioridade = " + tarefa.getPrioridade().getId() + " ";
            }
            if (tarefa.getProjeto().getId() > 0) {
                sql = sql + " and id_projeto = " + tarefa.getProjeto().getId() + " ";
            }

            if (tarefa.getUsuarioByIdUsuarioAutor().getId() > 0) {
                sql = sql + " and id_usuario_autor = " + tarefa.getUsuarioByIdUsuarioAutor().getId() + " ";
            }
            if (tarefa.getUsuarioByIdUsuarioResponsavel().getId() > 0) {
                sql = sql + " and id_usuario_responsavel = " + tarefa.getUsuarioByIdUsuarioResponsavel().getId() + " ";
            }

            if (tarefa.getVersaoByIdVersaoBug().getId() > 0) {
                sql = sql + " and id_versao_bug = " + tarefa.getVersaoByIdVersaoBug().getId() + " ";
            }
            if (tarefa.getVersaoByIdVersaoCorrecao().getId() > 0) {
                sql = sql + " and id_versao_correcao = " + tarefa.getVersaoByIdVersaoCorrecao().getId() + " ";
            }

            sql = sql + " and (upper (titulo) like '%" + tarefa.getTitulo().toUpperCase() + "%'"
                    + " or upper (descricao) like '%" + tarefa.getDescricao().toUpperCase() + "%') "
                    + " and situacao = 'A' "
                    + "order by id";

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
            sql = "from Tarefa where id =  " + tarefa.getId();

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

    private void gerarRelatorio(int id) {
       
        
        try {

            JasperReport relatorio = JasperCompileManager.compileReport(getClass().getResourceAsStream("/relatorios/relatoriopadraotarefa.jrxml"));

            Map parametros = new HashMap();
            parametros.put("idtarefa", id);

            // Executa relatoio
            JasperPrint impressao = JasperFillManager.fillReport(relatorio, parametros, ConexaoBD.getInstance().getConnection());

            // Exibe resultado em video
          //  JasperViewer.viewReport(impressao, false);
            
            String path=System.getProperty("user.dir");
            System.out.println("patch =" +path);
            JasperExportManager.exportReportToPdfFile(impressao,path+"\\tarefas\\"+id+"\\"+impressao.getName()+".pdf");

        } catch (Exception e) {
            // JOptionPane.showMessageDialog(null, "Erro ao gerar relatório: " + e);
            System.out.println("Erro ao gerar relatório =" + e);

        }

    }

}
