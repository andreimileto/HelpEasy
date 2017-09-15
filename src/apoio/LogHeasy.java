/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apoio;
import janelas.TelaPrincipal;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *
 * @author Userasdasdasdssss
 */

public class LogHeasy  {
    static final Logger logger = LogManager.getLogger(helpeasy.HelpEasy.class.getName());
    
    public void gravaInfo(String sClasse,String sMensagem) {
        logger.info("Classe: " + sClasse + " Usuário: " + TelaPrincipal.userH.getNome()+ " Mensagem: " + sMensagem);
    }
    
    public void gravaErro(String sClasse,String sMensagem) {
        logger.error("Classe: " + sClasse + " Usuário: " + TelaPrincipal.userH.getNome() + " Mensagem: " + sMensagem);
    }
        
    public void gravaDebug(String sClasse,String sMensagem) {
        logger.debug("Classe: " + sClasse + " Usuário: " + TelaPrincipal.userH.getNome() + " Mensagem: " + sMensagem);
    }
}
