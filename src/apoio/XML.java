/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apoio;

import DAO.ClienteDAO;
import com.thoughtworks.xstream.XStream;
import entidade.Cliente;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class XML {

    public static void ExportaXmlCliente() {
        String xml = "";
        try {
            XStream xStream = new XStream();
            xStream.alias("cliente", Cliente.class);
            ClienteDAO cliDAO = new ClienteDAO();
            ArrayList<Cliente> clientes = cliDAO.listarTodos();
            for (int i = 0; i < clientes.size(); i++) {
                xml = xStream.toXML(clientes.get(i));
                gravarArquivo("./exportacaoClientes/cliente_" + clientes.get(i).getCpfCnpj()+ ".XML", xml);
            }
        } catch (Exception e) {
            System.out.println("erro " + e.getMessage());
        }
    }

    public static void ImportaXmlCliente() {

        try {
            XStream xStream = new XStream();
            ClienteDAO cliDAO = new ClienteDAO();
            xStream.alias("cliente", Cliente.class);
            File arquivos[];
            File diretorio = new File("./exportacaoClientes/");
            arquivos = diretorio.listFiles();
            for (int i = 0; i < arquivos.length; i++) {
                Cliente novoCliente = (Cliente) xStream.fromXML(lerArquivo(arquivos[i].toString()));
                cliDAO.salvar(novoCliente);
            }
        } catch (Exception e) {
            System.out.println("erro " + e.getMessage());
        }
    }

    public static String lerArquivo(String sArquivo) {
        String sLinhaCompleta = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(sArquivo));
            while (br.ready()) {
                String linha = br.readLine();
                sLinhaCompleta = sLinhaCompleta + linha;
            }
            br.close();
        } catch (Exception e) {
            System.out.println("erro " + e.getMessage());
        }
        return sLinhaCompleta;
    }

    public static void gravarArquivo(String sArquivo, String xml) {
        try {
            File arquivo = new File(sArquivo);
            FileWriter fw = new FileWriter(arquivo);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(xml);
            bw.flush();
            bw.close();
        } catch (Exception e) {
            System.out.println("erro " + e.getMessage());
        }
    }
}
