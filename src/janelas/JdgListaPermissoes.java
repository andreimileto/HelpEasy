/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package janelas;

import DAO.PermissoesDAOAcoes;
import DAO.UsuarioDAO;
import DAO.UsuarioPermissaoTelaAcoesDAO;
import DAO.UsuarioPermissaoTelaDAO;
import apoio.HibernateUtil;
import controle.ControleUsuario;
import entidade.Usuario;
import entidade.UsuarioPermissaoTela;
import entidade.UsuarioPermissaoTelaAcoes;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Session;

/**
 *
 * @author Mileto
 */
public class JdgListaPermissoes extends javax.swing.JDialog {

    Usuario usuario;
//    ControleUsuario controleUsuario = new ControleUsuario();
    PermissoesDAOAcoes permissoesDAO = new PermissoesDAOAcoes();
    ArrayList<UsuarioPermissaoTelaAcoes> permissoes;
    ArrayList<UsuarioPermissaoTelaAcoes> permissoesEmEdicao = new ArrayList<>();
    String telaSelecionada;

    public JdgListaPermissoes(java.awt.Frame parent, boolean modal, Usuario usuario) {
        super(parent, modal);
        initComponents();
        this.usuario = usuario;
        permissoes = permissoesDAO.listarPermissoes(usuario);

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
            tblAcoes.setModel(this.obterDadosParaJTable());
            tblAcoes.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblAcoes.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblAcoes.getColumnModel().getColumn(2).setPreferredWidth(0);
            tblAcoes.getColumnModel().getColumn(3).setPreferredWidth(0);

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
//        if (permissoesEmEdicao.size()<1) {
//            permissoes = permissoesDAO.listarPermissoes(usuario);
//                       
//        }else{
//            permissoes = new ArrayList<>();
//            for (int i = 0; i < permissoesEmEdicao.size(); i++) {
//            permissoes.add(permissoesEmEdicao.get(i));    
//            }
//            
//        }
        dtm.addColumn("ID");
        dtm.addColumn("TELA");
        dtm.addColumn("AÇÃO");
        dtm.addColumn("PERMISSÃO");

        for (int i = 0; i < permissoes.size(); i++) {
            if (permissoes.get(i).getUsuarioPermissaoTela().getTelaAmigavel().equals(telaSelecionada)) {

                dtm.addRow(new Object[]{String.valueOf(permissoes.get(i).getId()), permissoes.get(i).getUsuarioPermissaoTela().getTelaAmigavel(),
                    permissoes.get(i).getAcaoAmigavel(), permissoes.get(i).isPermiteAcesso()});
            }
        }
        return dtm;
    }

    private void listarTelas() {
        try {
            //setar para tabela modelo de dados
            tblTelas.setModel(this.obterDadosParaJTableTela());
            tblTelas.getColumnModel().getColumn(0).setPreferredWidth(1);
            tblTelas.getColumnModel().getColumn(1).setPreferredWidth(500);
            tblTelas.getColumnModel().getColumn(2).setPreferredWidth(20);

        } catch (Exception ex) {
            janelas.TelaPrincipal.logH.gravaErro(this.getClass().getName(), ex.getMessage());
        }
    }

    public DefaultTableModel obterDadosParaJTableTela() throws Exception {
        DefaultTableModel dtm = new DefaultTableModel() {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
            boolean[] dtm = new boolean[]{
                false, false, true,};

            public boolean isCellEditable(int row, int column) {
                return dtm[column];

            }
        };

        //UsuarioDAO usuarioDAO = new UsuarioDAO();
        //  permissoes = permissoesDAO.listarPermissoes(usuario);
        dtm.addColumn("ID");
        dtm.addColumn("TELA");
        dtm.addColumn("PERMISSÃO");
        //W  ArrayList<UsuarioPermissaoTelaAcoes> acoesListadas = new ArrayList<>();
        for (int i = 0; i < permissoes.size(); i++) {
            //  acoesListadas.add(permissoes.get(i));
            boolean ok = true;
//            for (int j = permissoes.size(); j > 0; j--) {
//                if (permissoes.get(i).getUsuarioPermissaoTela().getTelaAmigavel().equals(permissoes.get(j).getUsuarioPermissaoTela().getTelaAmigavel())) {
//                    ok = false;
//                }

            try {
                if (permissoes.get(i).getUsuarioPermissaoTela().getId() == permissoes.get(i - 1).getUsuarioPermissaoTela().getId()) {
                    ok = false;
                }
            } catch (Exception e) {
                ok = true;
            }
//                if (permissoes.get(i).getId() == acoesListadas.get(i).getId()) {
//                    ok = false;
//                }
            if (ok) {
                dtm.addRow(new Object[]{String.valueOf(permissoes.get(i).getUsuarioPermissaoTela().getId()),
                    permissoes.get(i).getUsuarioPermissaoTela().getTelaAmigavel(), permissoes.get(i).getUsuarioPermissaoTela().isPermiteAcesso()});
            }

        }
        return dtm;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblAcoes = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnSalvar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTelas = new javax.swing.JTable();
        btnLocalizar = new javax.swing.JButton();
        tfdUsuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Help Easy - Lista de usuários");

        tblAcoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "TELA", "AÇÃO", "PERMISSÃO"
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
        tblAcoes.setFocusable(false);
        tblAcoes.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tblAcoesAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tblAcoes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAcoesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblAcoesMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblAcoesMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblAcoes);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setText("Lista de permissões");

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/confirmar.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon_Schutdown16.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        tblTelas.setModel(new javax.swing.table.DefaultTableModel(
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
        tblTelas.setFocusable(false);
        tblTelas.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tblTelasAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tblTelas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTelasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblTelasMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblTelasMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblTelas);

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28))
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
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnSair))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
//        selecionado();
        //  UsuarioPermissaoTela tela = new UsuarioPermissaoTela();
        UsuarioPermissaoTelaAcoesDAO usuarioPermissaoTelaAcoesDAO = new UsuarioPermissaoTelaAcoesDAO();
        UsuarioPermissaoTelaDAO usuarioPermissaoTelaDAO = new UsuarioPermissaoTelaDAO();
        //UsuarioPermissaoTela tela2 = new UsuarioPermissaoTela();

        for (int i = 0; i < permissoesEmEdicao.size(); i++) {
//            Session session = HibernateUtil.getSessionFactory().openSession(); 
//                   session.close();
            System.out.println("edição......................." + permissoesEmEdicao.get(i).getUsuarioPermissaoTela().isPermiteAcesso());
        }
        for (int i = 0; i < permissoesEmEdicao.size(); i++) {
//tblAcoes.getRowCount()
            UsuarioPermissaoTelaAcoes permiss = permissoesEmEdicao.get(tblAcoes.convertRowIndexToModel(i));
            //convertColumnIndexToModel(i));

//            if (Boolean.parseBoolean(String.valueOf(tblAcoes.getValueAt(i, 3)))) {
//                permiss.setPermiteAcesso(true);
//            } else {
//                permiss.setPermiteAcesso(false);
//            }
//            if (i<tblTelas) {
//                
//            }
            if (i < tblTelas.getRowCount()) {
                if (Boolean.parseBoolean(String.valueOf(tblTelas.getValueAt(i, 2)))) {
                    permiss.getUsuarioPermissaoTela().setPermiteAcesso(true);
                } else {
                    permiss.getUsuarioPermissaoTela().setPermiteAcesso(false);
                }

                boolean ok = true;

//                tela2.setId(permiss.getUsuarioPermissaoTela().getId());
//                tela2.setPermiteAcesso(permiss.getUsuarioPermissaoTela().isPermiteAcesso());
//                tela2.setTela(permiss.getUsuarioPermissaoTela().getTela());
//                tela2.setTelaAmigavel(permiss.getUsuarioPermissaoTela().getTelaAmigavel());
//                tela2.setUsuario(permiss.getUsuarioPermissaoTela().getUsuario());
                for (int j = 0; j < permissoesEmEdicao.size(); j++) {
                    System.out.println("edição......................." + permissoesEmEdicao.get(j).getUsuarioPermissaoTela().isPermiteAcesso());
                }

                permiss.getUsuarioPermissaoTela().setId(permissoesEmEdicao.get(i).getUsuarioPermissaoTela().getId());
                permiss.getUsuarioPermissaoTela().setPermiteAcesso(permissoesEmEdicao.get(i).getUsuarioPermissaoTela().isPermiteAcesso());
                permiss.getUsuarioPermissaoTela().setTela(permissoesEmEdicao.get(i).getUsuarioPermissaoTela().getTela());
                permiss.getUsuarioPermissaoTela().setTelaAmigavel(permissoesEmEdicao.get(i).getUsuarioPermissaoTela().getTelaAmigavel());
                permiss.getUsuarioPermissaoTela().setUsuario(permissoesEmEdicao.get(i).getUsuarioPermissaoTela().getUsuario());
                permiss.getUsuarioPermissaoTela().setUsuario(usuario);
                if (ok) {

                    usuarioPermissaoTelaDAO.salvar(permiss.getUsuarioPermissaoTela());
                }

            }

            usuarioPermissaoTelaAcoesDAO.salvar(permiss);

        }


    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void tblAcoesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAcoesMouseClicked

        int linhaSelecionada = tblAcoes.getSelectedRow();
        int idAcao = Integer.parseInt(tblAcoes.getValueAt(linhaSelecionada, 0).toString());
        for (int i = 0; i < permissoes.size(); i++) {
            if (permissoes.get(i).getId() == idAcao) {

                if (Boolean.parseBoolean(String.valueOf(tblAcoes.getValueAt(linhaSelecionada, 3)))) {
                    permissoes.get(i).setPermiteAcesso(true);
                } else {
                    permissoes.get(i).setPermiteAcesso(false);
                }
            }
        }

    }//GEN-LAST:event_tblAcoesMouseClicked

    private void tblAcoesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAcoesMousePressed

    }//GEN-LAST:event_tblAcoesMousePressed

    private void tblAcoesAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tblAcoesAncestorAdded

    }//GEN-LAST:event_tblAcoesAncestorAdded

    private void tblAcoesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAcoesMouseEntered

    }//GEN-LAST:event_tblAcoesMouseEntered

    private void tblTelasAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tblTelasAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tblTelasAncestorAdded

    private void tblTelasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTelasMouseClicked
        int linhaSelecionada = tblTelas.getSelectedRow();
        int idTela = Integer.parseInt(tblTelas.getValueAt(linhaSelecionada, 0).toString());
        for (int i = 0; i < permissoes.size(); i++) {
            if (permissoes.get(i).getUsuarioPermissaoTela().getId() == idTela) {

                if (Boolean.parseBoolean(String.valueOf(tblTelas.getValueAt(linhaSelecionada, 2)))) {
                    permissoes.get(i).getUsuarioPermissaoTela().setPermiteAcesso(true);
                } else {
                    permissoes.get(i).getUsuarioPermissaoTela().setPermiteAcesso(false);
                }
                permissoesEmEdicao.add(permissoes.get(i));
            }
        }
        //--
//        int linhaSelecionada = tblAcoes.getSelectedRow();
//        int idAcao = Integer.parseInt(tblAcoes.getValueAt(linhaSelecionada, 0).toString());
//        for (int i = 0; i < permissoes.size(); i++) {
//            if (permissoes.get(i).getId() == idAcao) {
//                
//                if (Boolean.parseBoolean(String.valueOf(tblAcoes.getValueAt(linhaSelecionada, 3)))) {
//                    permissoes.get(i).setPermiteAcesso(true);
//                } else {
//                    permissoes.get(i).setPermiteAcesso(false);
//                }
//            }
//        }
        //--

        for (int i = 0; i < permissoesEmEdicao.size(); i++) {
            System.out.println("edição!!!!!!!!!!!!!!!!! " + permissoesEmEdicao.get(i).getUsuarioPermissaoTela().isPermiteAcesso());
            System.out.println("edição!!!!!!!!!!!!!!!!! permissao " + permissoes.get(i).getUsuarioPermissaoTela().isPermiteAcesso());
        }

        int linha = tblTelas.getSelectedRow();
        telaSelecionada = tblTelas.getValueAt(linha, 1).toString();

        //JOptionPane.showMessageDialog(rootPane, telaSelecionada);
        //  listarPermissoes();

    }//GEN-LAST:event_tblTelasMouseClicked

    private void tblTelasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTelasMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblTelasMouseEntered

    private void tblTelasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTelasMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tblTelasMousePressed

    private void btnLocalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocalizarActionPerformed

        usuario.setLogin("");
        usuario.setNome("");
        JdgListaUsuario listaUsuario = new JdgListaUsuario(null, true, usuario);
        listaUsuario.setVisible(true);

        tfdUsuario.setText(usuario.getLogin());
        if (tfdUsuario.getText().length() > 0) {
            permissoes = new ArrayList<>();
            //listarpermissoes();
            permissoes = permissoesDAO.listarPermissoes(usuario);
            permissoesEmEdicao = new ArrayList<>();
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

        int row = tblAcoes.getSelectedRow();
        //   Usuario usuarioSelecionado = new Usuario();
        usuario.setId(Integer.parseInt(tblAcoes.getValueAt(row, 0).toString()));
        usuario.setNome(tblAcoes.getValueAt(row, 1).toString());
        usuario.setLogin(tblAcoes.getValueAt(row, 2).toString());
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
            java.util.logging.Logger.getLogger(JdgListaPermissoes.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JdgListaPermissoes.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JdgListaPermissoes.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JdgListaPermissoes.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JButton btnLocalizar;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblAcoes;
    private javax.swing.JTable tblTelas;
    private javax.swing.JTextField tfdUsuario;
    // End of variables declaration//GEN-END:variables
}
