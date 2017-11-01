/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package janelas;

import DAO.ClienteDAO;
import DAO.TarefaDAO;
import entidade.Cidade;
import entidade.Cliente;
import entidade.Fase;
import entidade.Modulo;
import entidade.Motivo;
import entidade.Prioridade;
import entidade.Projeto;
import entidade.Tarefa;
import entidade.Usuario;
import entidade.Versao;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pc05
 */
public class JdgListaTarefa extends javax.swing.JDialog {

    /**
     * Creates new form JdgListaCliente
     */
    Cliente cliente;
    Cidade cid;
    Versao versaoBug;
    Versao versaoCorrecao;
    Modulo modulo;
    Motivo motivo;
    Projeto projeto;
    Prioridade prioridade;
    Fase fase;
    Usuario autor;
    Usuario responsavel;
    Tarefa tarefa;

    public JdgListaTarefa(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

    }
    
    public JdgListaTarefa(java.awt.Frame parent, boolean modal, Tarefa tarefa) {
        super(parent, modal);
        initComponents();
        this.tarefa = tarefa;
        listarTarefas();
    }

    public JdgListaTarefa(java.awt.Frame parent, boolean modal, Tarefa tarefa, Motivo motivo,Usuario autor, Usuario responsavel, Projeto projeto, Prioridade prioridade, Modulo modulo, Versao versaoBug, Versao versaoCorrecao,Fase fase,Cliente cliente) {
        super(parent, modal);

        initComponents();
        this.tarefa = tarefa;
        this.motivo = motivo;
        tarefa.setMotivo(motivo);
        this.autor = autor;
        tarefa.setUsuarioByIdUsuarioAutor(autor);
        this.responsavel= responsavel;
        tarefa.setUsuarioByIdUsuarioResponsavel(responsavel);
        this.projeto=projeto;
        tarefa.setProjeto(projeto);
        this.prioridade = prioridade;
        tarefa.setPrioridade(prioridade);
        this.modulo =modulo;
        tarefa.setModulo(modulo);
        this.versaoBug = versaoBug;
        tarefa.setVersaoByIdVersaoBug(versaoBug);
        this.versaoCorrecao = versaoCorrecao;
        tarefa.setVersaoByIdVersaoCorrecao(versaoCorrecao);
        this.fase = fase;
        tarefa.setFase(fase);
     
        this.cliente = cliente;
        tarefa.setCliente(cliente);
                
        // this.cid = cid;
        listarTarefas();

    }

    private void listarTarefas() {
        try {
            //setar para tabela modelo de dados
            tblListaTarefas.setModel(this.obterDadosParaTabelaCompleto());
            tblListaTarefas.getColumnModel().getColumn(0).setPreferredWidth(1);
            tblListaTarefas.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblListaTarefas.getColumnModel().getColumn(2).setPreferredWidth(150);
            tblListaTarefas.getColumnModel().getColumn(3).setPreferredWidth(80);
            tblListaTarefas.getColumnModel().getColumn(4).setPreferredWidth(80);
            tblListaTarefas.getColumnModel().getColumn(5).setPreferredWidth(20);
            tblListaTarefas.getColumnModel().getColumn(5).setPreferredWidth(20);

        } catch (Exception ex) {
            System.out.println("Erro " + ex);
            janelas.TelaPrincipal.logH.gravaErro(this.getClass().getName(), ex.getMessage());
        }
    }

    private DefaultTableModel obterDadosParaTabelaCompleto() throws Exception {
        DefaultTableModel dtm = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

//        cid.setDescricao("");
//        cliente.setRazaoSocial(tfdFiltro.getText());
//        cliente.setCpfCnpj(tfdFiltro.getText());
        TarefaDAO tarDAO = new TarefaDAO();
        ArrayList<Tarefa> tarefas = tarDAO.listar(tarefa);

        dtm.addColumn("ID");
        dtm.addColumn("CLIENTE");
        dtm.addColumn("TÍTULO");
        dtm.addColumn("AUTOR");
        dtm.addColumn("RESPONSÁVEL");
        dtm.addColumn("FASE");
        dtm.addColumn("ÚLTIMA MODIFICAÇÃO");

        for (int i = 0; i < tarefas.size(); i++) {

            dtm.addRow(new String[]{String.valueOf(tarefas.get(i).getId()),
                tarefas.get(i).getCliente().getRazaoSocial(),
                tarefas.get(i).getTitulo(),
                String.valueOf(tarefas.get(i).getUsuarioByIdUsuarioAutor().getNome()),
                String.valueOf(tarefas.get(i).getUsuarioByIdUsuarioResponsavel().getNome()),
                String.valueOf(tarefas.get(i).getFase().getDescricao()),
                String.valueOf(tarefas.get(i).getDatahoraConclusao())
            });
        }
        return dtm;
    }

    private void selecionado() throws Exception {

        int row = tblListaTarefas.getSelectedRow();
        this.tarefa.setId(Integer.parseInt(tblListaTarefas.getValueAt(row, 0).toString()));

        TarefaDAO tarDAO = new TarefaDAO();
        ArrayList<Tarefa> tarefas = tarDAO.listarUmId(tarefa);
        
       // JOptionPane.showMessageDialog(null, "data previsao "+ tarefas.get(0).getDatahoraPrevisao());

        this.tarefa = tarefas.get(0);
        tarefa.setDatahoraPrevisao(tarefas.get(0).getDatahoraPrevisao());
       // JOptionPane.showMessageDialog(rootPane, tarefa.getDatahoraPrevisao());
        motivo.setId(tarefas.get(0).getMotivo().getId());
        motivo.setDescricao(tarefas.get(0).getMotivo().getDescricao());
        tarefa.setMotivo(motivo);
        
        autor.setId(tarefas.get(0).getUsuarioByIdUsuarioAutor().getId());
       autor.setNome(tarefas.get(0).getUsuarioByIdUsuarioAutor().getNome());
       tarefa.setUsuarioByIdUsuarioAutor(autor);
       
       responsavel.setId(tarefas.get(0).getUsuarioByIdUsuarioResponsavel().getId());
       responsavel.setNome(tarefas.get(0).getUsuarioByIdUsuarioResponsavel().getNome());
       tarefa.setUsuarioByIdUsuarioResponsavel(responsavel);
       
       projeto.setId(tarefas.get(0).getProjeto().getId());
       projeto.setDescricao(tarefas.get(0).getProjeto().getDescricao());
       tarefa.setProjeto(projeto);
       
         prioridade.setId(tarefas.get(0).getPrioridade().getId());
       prioridade.setDescricao(tarefas.get(0).getPrioridade().getDescricao());
       tarefa.setPrioridade(prioridade);
       
       modulo.setId(tarefas.get(0).getModulo().getId());
        modulo.setDescricao(tarefas.get(0).getModulo().getDescricao());
       tarefa.setModulo(modulo);
       
           
       versaoBug.setId(tarefas.get(0).getVersaoByIdVersaoBug().getId());
       versaoBug.setDescricao(tarefas.get(0).getVersaoByIdVersaoBug().getDescricao());
       tarefa.setVersaoByIdVersaoBug(versaoBug);
       
       versaoCorrecao.setId(tarefas.get(0).getVersaoByIdVersaoCorrecao().getId());
       versaoCorrecao.setDescricao(tarefas.get(0).getVersaoByIdVersaoCorrecao().getDescricao());
       tarefa.setVersaoByIdVersaoCorrecao(versaoCorrecao);
       
       fase.setId(tarefas.get(0).getFase().getId());
       fase.setDescricao(tarefas.get(0).getFase().getDescricao());
       tarefa.setFase(fase);
       
       
       cliente.setId(tarefas.get(0).getCliente().getId());
       cliente.setRazaoSocial(tarefas.get(0).getCliente().getRazaoSocial());
       cliente.setEmail(tarefas.get(0).getCliente().getEmail());
       tarefa.setCliente(cliente);
        
   
        this.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tfdFiltro = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cbxStatus = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListaTarefas = new javax.swing.JTable();
        btnConfirmar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("EasyPDV - Lista de clientes");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setText("Lista Cadastro de Cliente");

        tfdFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfdFiltroKeyReleased(evt);
            }
        });

        jLabel3.setText("Status:");

        cbxStatus.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxStatusItemStateChanged(evt);
            }
        });

        tblListaTarefas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblListaTarefas.setFocusable(false);
        tblListaTarefas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListaTarefasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblListaTarefas);

        btnConfirmar.setText("Editar");
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon_Schutdown16.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(434, 434, 434)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tfdFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfdFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(cbxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConfirmar)
                    .addComponent(btnSair))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        try {
            selecionado();
        } catch (Exception ex) {
            System.out.println("Erro " + ex);
            janelas.TelaPrincipal.logH.gravaErro(this.getClass().getName(), ex.getMessage());
        }
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void cbxStatusItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxStatusItemStateChanged

    }//GEN-LAST:event_cbxStatusItemStateChanged

    private void tfdFiltroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdFiltroKeyReleased

    }//GEN-LAST:event_tfdFiltroKeyReleased

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void tblListaTarefasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListaTarefasMouseClicked
        if (evt.getClickCount() > 1) {
            int linhaSelecionada = tblListaTarefas.getSelectedRow();
            try {
                selecionado();
            } catch (Exception ex) {
                janelas.TelaPrincipal.logH.gravaErro(this.getClass().getName(), ex.getMessage());
            }
            this.setVisible(false);
        }
    }//GEN-LAST:event_tblListaTarefasMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JdgListaTarefa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JdgListaTarefa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JdgListaTarefa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JdgListaTarefa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JdgListaTarefa dialog = new JdgListaTarefa(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JButton btnSair;
    private javax.swing.JComboBox<String> cbxStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblListaTarefas;
    private javax.swing.JTextField tfdFiltro;
    // End of variables declaration//GEN-END:variables
}
