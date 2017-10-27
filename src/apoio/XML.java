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
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class XML {

    public static void ExportaXmlCliente() {
        String xml="";
        try {
            XStream xStream = new XStream();
            xStream.alias("cliente", Cliente.class);

            ClienteDAO cliDAO = new ClienteDAO();
            ArrayList<Cliente> clientes = cliDAO.listarTodos();

            for (int i = 0; i < clientes.size(); i++) {
                xml = xStream.toXML(clientes.get(i));
                File arquivo = new File("./exportacaoClientes/cliente_"+clientes.get(i).getId()+".XML"); 
                FileWriter fw = new FileWriter(arquivo);  
                BufferedWriter bw = new BufferedWriter(fw);  
                bw.write(xml);  
                bw.flush();  
                bw.close();  
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
            String sLinhaCompleta="";
            File diretorio = new File("./exportacaoClientes/");
            arquivos = diretorio.listFiles();
            for(int i = 0; i < arquivos.length; i++){
                System.out.println(arquivos[i]);
                sLinhaCompleta="";
                BufferedReader br = new BufferedReader(new FileReader(arquivos[i]));
                while(br.ready()){
                   String linha = br.readLine();
                   sLinhaCompleta = sLinhaCompleta + linha;
                   
                }
                System.out.println("xxx"+sLinhaCompleta);
                Cliente novoCliente = (Cliente) xStream.fromXML(sLinhaCompleta);
                cliDAO.salvar(novoCliente);
                br.close();
            }
            
            /*


            ClienteDAO cliDAO = new ClienteDAO();

            ArrayList<Cliente> clientes = cliDAO.listarTodos();

            String xml = xStream.toXML(clientes);
            System.out.println("x" + xml);

            //Cliente novoCliente = (Cliente)xStream.fromXML(xml);
            for (int i = 0; i < clientes.size(); i++) {
                xml = xStream.toXML(clientes.get(i));
            }

            Cliente novoCliente = (Cliente) xStream.fromXML(xml);
            
            */

        } catch (Exception e) {
            System.out.println("erro " + e.getMessage());
        }
    }
}
