/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package janelas;

import DAO.PermissoesDAO;
import DAO.UsuarioDAO;
import DAO.UsuarioPermissaoTelaAcoesDAO;
import DAO.UsuarioPermissaoTelaDAO;
import controle.ControleUsuario;
import entidade.Usuario;
import entidade.UsuarioPermissaoTelaAcoes;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mileto
 */
public class JdgListaPermissoes extends javax.swing.JDialog {

    Usuario usuario;
//    ControleUsuario controleUsuario = new ControleUsuario();
    PermissoesDAO permissoesDAO = new PermissoesDAO();
    ArrayList<UsuarioPermissaoTelaAcoes> permissoes;

    public JdgListaPermissoes(java.awt.Frame parent, boolean modal, Usuario usuario) {
        super(parent, modal);
        initComponents();
        this.usuario = usuario;
      //  listarPermissoes();
        //listarTelas();
    }

    public JdgListaPermissoes(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

       // listarPermissoes();
       // listarTelas();
    }

    private void listarPermissoes() {
        try {
            //setar para tabela modelo de dados
            tblUsuarios.setModel(this.obterDadosParaJTable());
            tblUsuarios.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblUsuarios.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblUsuarios.getColumnModel().getColumn(2).setPreferredWidth(0);
            tblUsuarios.getColumnModel().getColumn(3).setPreferredWidth(0);

        } catch (Exception ex) {
            janelas.TelaPrincipal.logH.gravaErro(this.getClass().getName(), ex.getMessage());
        }
    }

    public DefaultTableModel obterDadosParaJTable() throws Exception {
        DefaultTableModel dtm = new DefaultTableModel() {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
            boolean[] dtm = new boolean[]{
                false, false, false, true
            };

            public boolean isCellEditable(int row, int column) {
                return dtm[column];

            }
        };

       // UsuarioDAO usuarioDAO = new UsuarioDAO();
        permissoes = permissoesDAO.listarPermissoes(usuario);

        dtm.addColumn("ID");
        dtm.addColumn("TELA");
        dtm.addColumn("BOTÃO");
        dtm.addColumn("PERMISSÃO");

        for (int i = 0; i < permissoes.size(); i++) {

            dtm.addRow(new Object[]{String.valueOf(permissoes.get(i).getId()),permissoes.get(i).getUsuarioPermissaoTela().getTelaAmigavel(),
                 permissoes.get(i).getAcaoAmigavel(), permissoes.get(i).getPermiteAcesso()});

        }
        return dtm;
    }
    
    
     private void listarTelas() {
        try {
            //setar para tabela modelo de dados
            tblUsuarios2.setModel(this.obterDadosParaJTableTela());
            tblUsuarios2.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblUsuarios2.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblUsuarios2.getColumnModel().getColumn(2).setPreferredWidth(0);
            

        } catch (Exception ex) {
            janelas.TelaPrincipal.logH.gravaErro(this.getClass().getName(), ex.getMessage());
        }
    }

    public DefaultTableModel obterDadosParaJTableTela() throws Exception {
        DefaultTableModel dtm = new DefaultTableModel() {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class,  java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
            boolean[] dtm = new boolean[]{
                false, false, true,
            };

            public boolean isCellEditable(int row, int column) {
                return dtm[column];

            }
        };

        //UsuarioDAO usuarioDAO = new UsuarioDAO();
        permissoes = permissoesDAO.listarPermissoes(usuario);

        dtm.addColumn("ID");
        dtm.addColumn("TELA");        
        dtm.addColumn("PERMISSÃO");

        for (int i = 0; i < permissoes.size(); i = i+3) {

            dtm.addRow(new Object[]{String.valueOf(permissoes.get(i).getUsuarioPermissaoTela().getId()),
                permissoes.get(i).getUsuarioPermissaoTela().getTelaAmigavel(), permissoes.get(i).getUsuarioPermissaoTela().getPermiteAcesso()});

        }
        return dtm;
    }
    
    
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsuarios = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnConfirmar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblUsuarios2 = new javax.swing.JTable();
        btnLocalizar = new javax.swing.JButton();
        tfdUsuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Help Easy - Lista de usuários");

        tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
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
                "Id", "Descrição", "Ativo", "Título 4"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblUsuarios.setFocusable(false);
        tblUsuarios.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tblUsuariosAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tblUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsuariosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblUsuariosMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblUsuariosMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblUsuarios);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setText("Lista de permissões");

        btnConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Edit File-16.png"))); // NOI18N
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

        tblUsuarios2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "TELA", "PERMISSÃO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblUsuarios2.setFocusable(false);
        tblUsuarios2.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tblUsuarios2AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tblUsuarios2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsuarios2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblUsuarios2MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblUsuarios2MousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblUsuarios2);

        btnLocalizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Lupa3.png"))); // NOI18N
        btnLocalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocalizarActionPerformed(evt);
            }
        });

        tfdUsuario.setEditable(false);

        jLabel2.setText("Usuário:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(305, 305, 305)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLocalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(tfdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnLocalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConfirmar)
                    .addComponent(btnSair))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
//        selecionado();
        for (int i = 0; i < tblUsuarios.getRowCount(); i++) {

            UsuarioPermissaoTelaAcoes permiss = permissoes.get(tblUsuarios.convertRowIndexToModel(i));
                    //convertColumnIndexToModel(i));

            if (Boolean.parseBoolean(String.valueOf(tblUsuarios.getValueAt(i, 3)))) {
                permiss.setPermiteAcesso(true);
            } else {
                permiss.setPermiteAcesso(false);
            }
            UsuarioPermissaoTelaAcoesDAO usuarioPermissaoTelaAcoesDAO = new UsuarioPermissaoTelaAcoesDAO();
            usuarioPermissaoTelaAcoesDAO.salvar(permiss); //get().salvar(g);
            
//            
//            
//              permiss = permissoes.get(tblUsuarios2.convertColumnIndexToModel(i));
//
//            if (Boolean.parseBoolean(String.valueOf(tblUsuarios2.getValueAt(i, 2)))) {
//                permiss.setPermiteAcesso(true);
//            } else {
//                permiss.setPermiteAcesso(false);
//            }
//          //  UsuarioPermissaoTelaDAO usuarioPermissaoTelaDAO = new UsuarioPermissaoTelaDAO();
//            usuarioPermissaoTelaAcoesDAO.salvar(permiss);
//            
//            
            
            
        }
        
//        for (int i = 0; i < tblUsuarios2.getRowCount(); i++) {
//
//            UsuarioPermissaoTelaAcoes permiss = permissoes.get(tblUsuarios.convertColumnIndexToModel(i));
//            //  System.out.println("parametros:"+g.getParametros());
//            System.out.println(permiss.getPermiteAcesso());
////              permiss.setPermiteAcesso(false);
//            if (Boolean.parseBoolean(String.valueOf(tblUsuarios.getValueAt(i, 3)))) {
//                permiss.setPermiteAcesso(true);
//            } else {
//                permiss.setPermiteAcesso(false);
//            }
//            UsuarioPermissaoTelaAcoesDAO usuarioPermissaoTelaAcoesDAO = new UsuarioPermissaoTelaAcoesDAO();
//            usuarioPermissaoTelaAcoesDAO.salvar(permiss); //get().salvar(g);
//        }

    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void tblUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsuariosMouseClicked
        if (evt.getClickCount() > 1) {
            int linhaSelecionada = tblUsuarios.getSelectedRow();
            selecionado();
            dispose();
        }
    }//GEN-LAST:event_tblUsuariosMouseClicked

    private void tblUsuariosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsuariosMousePressed

    }//GEN-LAST:event_tblUsuariosMousePressed

    private void tblUsuariosAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tblUsuariosAncestorAdded

    }//GEN-LAST:event_tblUsuariosAncestorAdded

    private void tblUsuariosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsuariosMouseEntered

    }//GEN-LAST:event_tblUsuariosMouseEntered

    private void tblUsuarios2AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tblUsuarios2AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tblUsuarios2AncestorAdded

    private void tblUsuarios2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsuarios2MouseClicked
        
    }//GEN-LAST:event_tblUsuarios2MouseClicked

    private void tblUsuarios2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsuarios2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblUsuarios2MouseEntered

    private void tblUsuarios2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsuarios2MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tblUsuarios2MousePressed

    private void btnLocalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocalizarActionPerformed

        usuario.setLogin("");
        usuario.setNome("");
        JdgListaUsuario listaUsuario = new JdgListaUsuario(null, true, usuario);
        listaUsuario.setVisible(true);

        tfdUsuario.setText(usuario.getLogin());
        if (tfdUsuario.getText().length() > 0) {
            //listarpermissoes();
            listarTelas();
            listarPermissoes();
        }
    }//GEN-LAST:event_btnLocalizarActionPerformed
//    private void listar() {
//        try {
//            usuario.setNome(tfdDescricao.getText().toUpperCase());
//            usuario.setLogin(tfdDescricao.getText().toUpperCase());
//            ControleUsuario controleUsuario = new ControleUsuario();
//            controleUsuario.listar(usuario);
//        } catch (Exception e) {
//            janelas.TelaPrincipal.logH.gravaErro(this.getClass().getName(), e.getMessage());
//        }
//
//        listarPermissoes();
//    }
    //retorna item selecionado na taleba
    private void selecionado() {

        int row = tblUsuarios.getSelectedRow();
        //   Usuario usuarioSelecionado = new Usuario();
        usuario.setId(Integer.parseInt(tblUsuarios.getValueAt(row, 0).toString()));
        usuario.setNome(tblUsuarios.getValueAt(row, 1).toString());
        usuario.setLogin(tblUsuarios.getValueAt(row, 2).toString());
//        permissoes = controleUsuario.listar(usuario);
//        usuario.setSenha(permissoes.get(0).getSenha());
        dispose();
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
            java.util.logging.Logger.getLogger(JdgListaPermissoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JdgListaPermissoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JdgListaPermissoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JdgListaPermissoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JdgListaPermissoes dialog = new JdgListaPermissoes(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnLocalizar;
    private javax.swing.JButton btnSair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblUsuarios;
    private javax.swing.JTable tblUsuarios2;
    private javax.swing.JTextField tfdUsuario;
    // End of variables declaration//GEN-END:variables
}
