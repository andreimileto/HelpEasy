/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package janelas;

import apoio.LogHeasy;
import entidade.Usuario;

/**
 *
 * @author Mileto
 */
public class TelaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form TelaPrincipal
     */
//    Usuario usuario;
    public static Usuario userH = new Usuario();
    

    public TelaPrincipal() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        
    }
    
    public TelaPrincipal(Usuario usuario) {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        lblUsuario.setText(usuario.getLogin());
        userH = usuario;
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblVersao = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        imnCidades = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        imnParametrosSistema = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        ImnCadastroUsuario = new javax.swing.JMenu();
        ImnCadastroFase = new javax.swing.JMenuItem();
        ImnCadastroProjeto = new javax.swing.JMenuItem();
        imnCadastroMotivo = new javax.swing.JMenuItem();
        imnCadastroUsuario = new javax.swing.JMenuItem();
        ImnCadastroPrioridade = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Help Easy");

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setText("Versão:");

        lblVersao.setText("XXX");

        jLabel2.setText("Usuário:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblVersao)
                .addGap(129, 129, 129)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 344, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblVersao)
                    .addComponent(jLabel2)
                    .addComponent(lblUsuario)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 387, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jMenu1.setText("Cadastros");

        imnCidades.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/IconLocation.png"))); // NOI18N
        imnCidades.setText("Cidades");
        imnCidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imnCidadesActionPerformed(evt);
            }
        });
        jMenu1.add(imnCidades);

        jMenuBar1.add(jMenu1);

        jMenu4.setText("Configurações");

        imnParametrosSistema.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/preferences_desktop_user_password.png"))); // NOI18N
        imnParametrosSistema.setText("Alterar senha");
        imnParametrosSistema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imnParametrosSistemaActionPerformed(evt);
            }
        });
        jMenu4.add(imnParametrosSistema);

        jMenuItem1.setText("Parâmetros do Sistema");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem1);

        jMenuBar1.add(jMenu4);

        ImnCadastroUsuario.setText("Gerência");
        ImnCadastroUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ImnCadastroUsuarioActionPerformed(evt);
            }
        });

        ImnCadastroFase.setText("Cadastro de fase");
        ImnCadastroFase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ImnCadastroFaseActionPerformed(evt);
            }
        });
        ImnCadastroUsuario.add(ImnCadastroFase);

        ImnCadastroProjeto.setText("Cadastro de projeto");
        ImnCadastroProjeto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ImnCadastroProjetoActionPerformed(evt);
            }
        });
        ImnCadastroUsuario.add(ImnCadastroProjeto);

        imnCadastroMotivo.setText("Cadastro de motivo");
        imnCadastroMotivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imnCadastroMotivoActionPerformed(evt);
            }
        });
        ImnCadastroUsuario.add(imnCadastroMotivo);

        imnCadastroUsuario.setText("Cadastro de usuário");
        imnCadastroUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imnCadastroUsuarioActionPerformed(evt);
            }
        });
        ImnCadastroUsuario.add(imnCadastroUsuario);

        ImnCadastroPrioridade.setText("Cadastro de prioridade");
        ImnCadastroPrioridade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ImnCadastroPrioridadeActionPerformed(evt);
            }
        });
        ImnCadastroUsuario.add(ImnCadastroPrioridade);

        jMenuBar1.add(ImnCadastroUsuario);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void imnCidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imnCidadesActionPerformed
        JdgCadastroCidade cadastroCidade = new JdgCadastroCidade(this, true);
        cadastroCidade.setVisible(true);
    }//GEN-LAST:event_imnCidadesActionPerformed

    private void imnParametrosSistemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imnParametrosSistemaActionPerformed
        JdgAlteracaoSenha alteracaoSenha = new JdgAlteracaoSenha(this, rootPaneCheckingEnabled, userH);
        alteracaoSenha.setVisible(true);
    }//GEN-LAST:event_imnParametrosSistemaActionPerformed

    private void ImnCadastroFaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ImnCadastroFaseActionPerformed
        JdgCadastroFase cadastroFase = new JdgCadastroFase(this, true);
        cadastroFase.setVisible(true);
    }//GEN-LAST:event_ImnCadastroFaseActionPerformed

    private void ImnCadastroProjetoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ImnCadastroProjetoActionPerformed
        JdgCadastroProjeto cadastroProjeto = new JdgCadastroProjeto(this, true);
        cadastroProjeto.setVisible(true);
    }//GEN-LAST:event_ImnCadastroProjetoActionPerformed

    private void imnCadastroMotivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imnCadastroMotivoActionPerformed
        JdgCadastroMotivo cadastroMotivo = new JdgCadastroMotivo(this, true);
        cadastroMotivo.setVisible(true);
    }//GEN-LAST:event_imnCadastroMotivoActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        JdbParametrosSistema parametrosSistema = new JdbParametrosSistema(this, true);
        parametrosSistema.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void ImnCadastroUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ImnCadastroUsuarioActionPerformed

        
    }//GEN-LAST:event_ImnCadastroUsuarioActionPerformed

    private void imnCadastroUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imnCadastroUsuarioActionPerformed
        // TODO add your handling code here:
        JdgCadastroUsuario cadastroUsuario = new JdgCadastroUsuario(this, true);
        cadastroUsuario.setVisible(true);
    }//GEN-LAST:event_imnCadastroUsuarioActionPerformed

    private void ImnCadastroPrioridadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ImnCadastroPrioridadeActionPerformed
        JdgCadastroPrioridade cadastroPrioridade = new JdgCadastroPrioridade(this,true);
        cadastroPrioridade.setVisible(true);
    }//GEN-LAST:event_ImnCadastroPrioridadeActionPerformed

    /**
     * @param args the command line arguments
     */
    static final LogHeasy logH = new LogHeasy();
    
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
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem ImnCadastroFase;
    private javax.swing.JMenuItem ImnCadastroPrioridade;
    private javax.swing.JMenuItem ImnCadastroProjeto;
    private javax.swing.JMenu ImnCadastroUsuario;
    private javax.swing.JMenuItem imnCadastroMotivo;
    private javax.swing.JMenuItem imnCadastroUsuario;
    private javax.swing.JMenuItem imnCidades;
    private javax.swing.JMenuItem imnParametrosSistema;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JLabel lblVersao;
    // End of variables declaration//GEN-END:variables
}
