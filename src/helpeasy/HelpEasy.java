/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpeasy;

import apoio.ConexaoBD;
import janelas.JdgCadastroCidade;
import janelas.TelaPrincipal;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author Mileto
 */
public class HelpEasy {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }

        if (ConexaoBD.getInstance()
                .getConnection() != null) {
//            JOptionPane.showMessageDialog(null, "conectou");
            TelaPrincipal telaPrincipal = new TelaPrincipal();
            telaPrincipal.setVisible(true);

            
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao abrir banco de dados, entre em contato com o suporte!");
        }

        
    }

}
