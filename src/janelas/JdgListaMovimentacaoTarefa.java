/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package janelas;

import DAO.MovimentoTarefaDAO;
import controle.ControleMovimentoTarefa;
import entidade.MovimentoTarefa;
import entidade.Tarefa;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mileto
 */
public class JdgListaMovimentacaoTarefa extends javax.swing.JDialog {

    MovimentoTarefa movTarefa;
    Tarefa tarefa;

    public JdgListaMovimentacaoTarefa(java.awt.Frame parent, boolean modal, Tarefa tarefa, MovimentoTarefa movTarefa) {
        super(parent, modal);
        initComponents();
        this.movTarefa = movTarefa;
        this.tarefa = tarefa;

        listaMovimento();
    }

    public JdgListaMovimentacaoTarefa(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        listaMovimento();
    }

    private void listaMovimento() {
        try {
            //setar para tabela modelo de dados
            tblGrid.setModel(this.obterDadosParaJTable());
            tblGrid.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblGrid.getColumnModel().getColumn(1).setPreferredWidth(270);

        } catch (Exception ex) {
            janelas.TelaPrincipal.logH.gravaErro(this.getClass().getName(), ex.getMessage());
        }
    }

    public DefaultTableModel obterDadosParaJTable() throws Exception {

        DefaultTableModel dtm = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        try {
            movTarefa.setDescricao(tfdDescricao.getText());
            movTarefa.setTarefa(tarefa);
            ControleMovimentoTarefa controleMov = new ControleMovimentoTarefa();
            ArrayList<MovimentoTarefa> movTarefas = controleMov.listar(movTarefa);
            dtm.addColumn("ID");
            dtm.addColumn("DESCRIÇÃO");
            dtm.addColumn("USUÁRIO");
            dtm.addColumn("DATA/HORA");
            dtm.addColumn("ANEXO");

            for (int i = 0; i < movTarefas.size(); i++) {
                dtm.addRow(new String[]{String.valueOf(movTarefas.get(i).getId()),
                    movTarefas.get(i).getDescricao(),
                    movTarefas.get(i).getUsuario().getNome(),
                    movTarefas.get(i).getDatahoraMovimento().toString(),
                    movTarefas.get(i).getAnexo()});
            }
        } catch (Exception e) {
            System.out.println("erro: " + e.getMessage());
        }
        return dtm;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblGrid = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnConfirmar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        tfdDescricao = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Help Easy - Lista de Movimento Tarefa");

        tblGrid.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Descrição", "Ativo", "Projeto"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblGrid.setFocusable(false);
        tblGrid.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tblGridAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tblGrid.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGridMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblGridMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblGridMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblGrid);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setText("Lista de Movimento Tarefa");

        btnConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/confirmar.png"))); // NOI18N
        btnConfirmar.setText("Selecionar");
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

        jLabel2.setText("Descrição:");

        tfdDescricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfdDescricaoActionPerformed(evt);
            }
        });
        tfdDescricao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfdDescricaoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfdDescricaoKeyReleased(evt);
            }
        });

        jLabel3.setText("Para exibir o anexo pressione duplo clique na grid ou clique no item e após selecionar.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(188, 188, 188)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdDescricao)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfdDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConfirmar)
                    .addComponent(btnSair)
                    .addComponent(jLabel3))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        selecionado();
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void tblGridMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGridMouseClicked
        if (evt.getClickCount() > 1) {
            int linhaSelecionada = tblGrid.getSelectedRow();
            selecionado();
        }
    }//GEN-LAST:event_tblGridMouseClicked

    private void tblGridMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGridMousePressed

    }//GEN-LAST:event_tblGridMousePressed

    private void tblGridAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tblGridAncestorAdded

    }//GEN-LAST:event_tblGridAncestorAdded

    private void tblGridMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGridMouseEntered

    }//GEN-LAST:event_tblGridMouseEntered
    private void listar() {
        try {
            movTarefa.setDescricao(tfdDescricao.getText().toUpperCase());
            ControleMovimentoTarefa controleMov = new ControleMovimentoTarefa();
            controleMov.listarDescricao(movTarefa);
        } catch (Exception e) {
            janelas.TelaPrincipal.logH.gravaErro(this.getClass().getName(), e.getMessage());
        }

        listaMovimento();
    }
    private void tfdDescricaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdDescricaoKeyPressed


    }//GEN-LAST:event_tfdDescricaoKeyPressed

    private void tfdDescricaoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdDescricaoKeyReleased
        listar();
    }//GEN-LAST:event_tfdDescricaoKeyReleased

    private void tfdDescricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfdDescricaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfdDescricaoActionPerformed

    //retorna item selecionado na taleba
    private void selecionado() {
        int row = tblGrid.getSelectedRow();
        this.movTarefa.setId(Integer.parseInt(tblGrid.getValueAt(row, 0).toString()));

        MovimentoTarefaDAO movDAO = new MovimentoTarefaDAO();
        ArrayList<MovimentoTarefa> movDAOs = movDAO.listar(movTarefa);
        if (movDAOs.get(0).getAnexo().length() > 0) {
            try {
                File patch = new File("tarefas");
                File diretorio = new File(patch.getAbsolutePath() + "\\" + movDAOs.get(0).getTarefa().getId() + "\\" + movDAOs.get(0).getId() + "_" + movDAOs.get(0).getAnexo());
                if (!diretorio.exists()) {
                    JOptionPane.showMessageDialog(rootPane, "Arquivo não existe nos nossos servidores");
                } else {
                    java.awt.Desktop.getDesktop().open(diretorio);
                }
            } catch (IOException ex) {
                Logger.getLogger(JdgCadastroTarefa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

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
            java.util.logging.Logger.getLogger(JdgListaMovimentacaoTarefa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JdgListaMovimentacaoTarefa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JdgListaMovimentacaoTarefa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JdgListaMovimentacaoTarefa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }


        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JdgListaMovimentacaoTarefa dialog = new JdgListaMovimentacaoTarefa(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblGrid;
    private javax.swing.JTextField tfdDescricao;
    // End of variables declaration//GEN-END:variables
}
