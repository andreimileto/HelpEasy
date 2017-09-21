/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apoio;

import DAO.PermissoesDAO;
import com.toedter.calendar.JDateChooser;
import entidade.Cliente;
import entidade.UsuarioPermissaoTela;
import entidade.UsuarioPermissaoTelaAcoes;
import janelas.TelaPrincipal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;

/**
 *
 * @author Fabricio Pretto
 */
public class Validacao {

    private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
    private static String sRetorno = "";

    private static int calcularDigito(String str, int[] peso) {
        int soma = 0;
        for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
            digito = Integer.parseInt(str.substring(indice, indice + 1));
            soma += digito * peso[peso.length - str.length() + indice];
        }
        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;
    }

    public static boolean validarCPF(String cpf) {
        if ((cpf == null) || (cpf.length() != 11)) {
            return false;
        }
        Integer digito1 = calcularDigito(cpf.substring(0, 9), pesoCPF);
        Integer digito2 = calcularDigito(cpf.substring(0, 9) + digito1, pesoCPF);
        return cpf.equals(cpf.substring(0, 9) + digito1.toString() + digito2.toString());
    }

    public static boolean validarCNPJ(String cnpj) {
        if ((cnpj == null) || (cnpj.length() != 14)) {
            return false;
        }
        Integer digito1 = calcularDigito(cnpj.substring(0, 12), pesoCNPJ);
        Integer digito2 = calcularDigito(cnpj.substring(0, 12) + digito1, pesoCNPJ);
        return cnpj.equals(cnpj.substring(0, 12) + digito1.toString() + digito2.toString());
    }

    public static boolean validarDataDMA(int d, int m, int a) {
        boolean correto = true;
        int[] dias = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (a < 0 || m < 1 || m > 12) {
            correto = false;
        } else {
            // valida o dia
            if (a % 4 == 0 && (a % 100 != 0 || a % 400 == 0)) {
                dias[1] = 29;
            }
            if (d < 1 || d > dias[m - 1]) {
                correto = false;
            }
        }
        return (correto);
    }

    public static boolean validarDataFormatada(String dataComFormato) {
        String[] data = dataComFormato.split("/");
        return (validarDataDMA(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[1])));
    }

    public static void validarTelefone(JFormattedTextField campo) {
        if (campo.getText().trim().length() < 13) {
            Formatacao.reformatarTelefone(campo);
        }
    }

    public static boolean validadeFiltroDeData(JDateChooser tffDataInicio, JDateChooser tffDataFim) {
        boolean ok = true;
        String dataInicio = "";
        String dataFim = "";

        dataInicio = Formatacao.ajustaDataDMAJCalendar(tffDataInicio);
        dataFim = Formatacao.ajustaDataDMAJCalendar(tffDataFim);

        if (!validarDataFormatada(dataInicio)) {
            ok = false;
        }

        if (!validarDataFormatada(dataFim)) {
            ok = false;
        }

        if (!verDataMaior(dataInicio, dataFim)) {
            ok = false;
        }

        return ok;
    }

    public static boolean validadeFiltroDeData(JDateChooser tffDataVencimento) {
        boolean ok = true;
        String dataVencimento = "";

        dataVencimento = Formatacao.ajustaDataDMAJCalendar(tffDataVencimento);

        if (!validarDataFormatada(dataVencimento)) {
            ok = false;
        }

        return ok;
    }

    private static boolean verDataMaior(String dataInicial, String dataFinal) {
        boolean ok = false;
        String arrayDataInicio[] = new String[3];
        arrayDataInicio = dataInicial.split("/");

        String arrayDataFim[] = new String[3];
        arrayDataFim = dataFinal.split("/");

        if (Integer.parseInt(arrayDataFim[2]) >= Integer.parseInt(arrayDataInicio[2])) {
            if (Integer.parseInt(arrayDataFim[1]) >= Integer.parseInt(arrayDataInicio[1])) {
                if (Integer.parseInt(arrayDataFim[0]) >= Integer.parseInt(arrayDataInicio[0])) {
                    ok = true;
                }
            }
        } else {
            ok = false;
        }

        return ok;
    }

    public static void setaPermissoes(String sClasse, JPanel panel) {
        for (int i = 0; i < panel.getComponentCount(); i++) {
            if (panel.getComponent(i) instanceof JButton) {
                panel.getComponent(i).setEnabled(pegaPermissao(sClasse, panel.getComponent(i).getName()));
            }
        }
    }

    public static boolean pegaPermissao(String sTela, String sAcao) {
        try {
            PermissoesDAO perissoesDAO = new PermissoesDAO();
            ArrayList<UsuarioPermissaoTelaAcoes> permissoes = new ArrayList<>();
            sRetorno = "";
            permissoes = perissoesDAO.listarPermissoes(TelaPrincipal.userH);
            for (int i = 0; i < permissoes.size(); i++) {
                System.out.println("acao"+permissoes.get(i).getAcao());
                System.out.println("tela"+permissoes.get(i).getUsuarioPermissaoTela().getTela());
                //System.out.println("idusuario"+permissoes.get(i).getUsuarioPermissaoTela().getUsuario());
                if (sTela.equals(permissoes.get(i).getUsuarioPermissaoTela().getTela()) && sAcao.equals(permissoes.get(i).getAcao())) {
                    sRetorno = "" + permissoes.get(i).getPermiteAcesso();
                }
            }
        } catch (Exception e) {
            System.out.println("ver erro" + e.getMessage());
        }
        if (sRetorno.equals("S")) {
            return true;
        } else {
            return false;
        }
    }
    
    public static void populaPermissao(boolean bTodos) {
         
        
    }
    
}
