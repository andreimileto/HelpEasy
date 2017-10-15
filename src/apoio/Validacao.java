/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apoio;

import DAO.*;
import entidade.*;
import com.toedter.calendar.JDateChooser;
import janelas.TelaPrincipal;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

/**
 *
 * @author Fabricio Pretto
 */
public class Validacao {

    private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
    private static boolean bRetornoAcao = false;
    private static boolean bRetornoTela = false;

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
                panel.getComponent(i).setEnabled(pegaPermissaoAcao(sClasse, panel.getComponent(i).getName()));
            }
        }
    }

    public static void setaPermissoes(JMenuBar jMenu) {
        javax.swing.JMenuItem menuItem = null;
        javax.swing.JMenu menuPai = null;
        javax.swing.JMenu menuPaiSub = null;
        java.awt.Component[] components;
        try {
            for (int i = 0; i < jMenu.getMenuCount(); i++) {
                menuPai = jMenu.getMenu(i);
                //System.out.println(menuPai.getText());
                components = menuPai.getMenuComponents();
                /*if (menuPai.getName() == null) {
                 menuPai.setEnabled(false);
            }
            else {
                 menuPai.setEnabled(pegaPermissaoTela(menuPai.getName()));
            }*/
                for (int x = 0; x < components.length; x++) {
                    if (components[x] instanceof javax.swing.JMenu) {
                        menuPaiSub = ((javax.swing.JMenu) components[x]);
                        //System.out.println("-> " + menuPaiSub.getText());
                        /*if (menuPaiSub.getName() == null) {
                         menuPaiSub.setEnabled(false);
                    }
                    else {
                         menuPaiSub.setEnabled(pegaPermissaoTela(menuPaiSub.getName()));
                    }*/
                    } else if (components[x] instanceof javax.swing.JMenuItem) {
                        menuItem = ((javax.swing.JMenuItem) components[x]);
                        //System.out.println("-> " + menuItem.getText());
                        if (menuItem.getName() == null) {
                            menuItem.setEnabled(false);
                        } else {
                            menuItem.setEnabled(pegaPermissaoTela(menuItem.getName()));
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("erro " + e.getMessage());
        }
    }

    public static boolean pegaPermissaoAcao(String sTela, String sAcao) {
        if (sAcao.equals("btnSair")) {
            bRetornoAcao = true;
        } else {
            try {
                PermissoesDAOAcoes perissoesDAO = new PermissoesDAOAcoes();
                ArrayList<UsuarioPermissaoTelaAcoes> permissoes = new ArrayList<>();
                bRetornoAcao = false;
                permissoes = perissoesDAO.listarPermissoes(TelaPrincipal.userH);
                for (int i = 0; i < permissoes.size(); i++) {
                    //System.out.println("acao"+permissoes.get(i).getAcao());
                    //System.out.println("tela"+permissoes.get(i).getUsuarioPermissaoTela().getTela());
                    //System.out.println("idusuario"+permissoes.get(i).getUsuarioPermissaoTela().getUsuario());
                    if (sTela.equals(permissoes.get(i).getUsuarioPermissaoTela().getTela()) && sAcao.equals(permissoes.get(i).getAcao())) {
                        bRetornoAcao = permissoes.get(i).isPermiteAcesso();
                    }
                }
            } catch (Exception e) {
                System.out.println("ver erro" + e.getMessage());
            }
        }
        return bRetornoAcao;
    }

    public static boolean pegaPermissaoTela(String sTela) {
        if (sTela.equals("menuSempreVisivel")) {
            bRetornoTela = true;
        } else {
            try {
                PermissoesDAOTela perissoesTelaDAO = new PermissoesDAOTela();
                ArrayList<UsuarioPermissaoTela> permissoesTela = new ArrayList<>();
                bRetornoTela = false;
                permissoesTela = perissoesTelaDAO.listarPermissoes(TelaPrincipal.userH);
                //System.out.println("tela " + sTela);
                for (int i = 0; i < permissoesTela.size(); i++) {
                    // System.out.println("tela for" + permissoesTela.get(i).getTela());
                    if (sTela.equals(permissoesTela.get(i).getTela())) {
                        bRetornoTela = permissoesTela.get(i).isPermiteAcesso();
                    }
                }
            } catch (Exception e) {
                System.out.println("ver erro" + e.getMessage());
            }
        }
        return bRetornoTela;
    }

    public static void populaPermissao() {
        String arquivoCSV = "permissoes.csv";
        BufferedReader br = null;
        String linha = "";
        String csvDivisor = ";";
        try {
            ArrayList<Usuario> usuarios;
            ArrayList<UsuarioPermissaoTela> userPermTela;
            ArrayList<UsuarioPermissaoTelaAcoes> userPermTelaAcoes;

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            UsuarioPermissaoTelaDAO usuarioPermissaoTelaDAO = new UsuarioPermissaoTelaDAO();
            UsuarioPermissaoTelaAcoesDAO usuarioPermissaoTelaAcoesDAO = new UsuarioPermissaoTelaAcoesDAO();

            usuarios = usuarioDAO.listarTodos();
            userPermTela = usuarioPermissaoTelaDAO.listarTodos();
            userPermTelaAcoes = usuarioPermissaoTelaAcoesDAO.listarTodos();

            br = new BufferedReader(new FileReader(arquivoCSV));
            while ((linha = br.readLine()) != null) {

                String[] permissoes = linha.split(csvDivisor);

                /*for (int i = 0; i < usuarios.size(); i++) {
                    for (int i2 = 0; i2 < userPermTela.size(); i2++) {
                        if (userPermTela.get(i2).getId() == usuarios.get(i).getId() )
                        for (int i3 = 0; i3 < userPermTela.size(); i3++) {
                            
                        }
                    }
                }*/
                System.out.println("tela " + permissoes[0] + " tela_am " + permissoes[1]
                        + " acao " + permissoes[2] + " acao_am " + permissoes[3]);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
