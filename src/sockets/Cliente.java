/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import apoio.HibernateUtil;
import apoio.UsuariosAlteracoes;
import janelas.TelaPrincipal;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextArea;
import org.hibernate.Session;

/**
 *
 * @author fabricio
 */
public class Cliente extends Thread {

    String host = null;
    int porta = 0;
    JTextArea txaErro;
    JTextArea pkgRecebido;

    public Cliente(String host, int porta, JTextArea txaErro, JTextArea pkgRecebido) {
        this.host = host;
        this.porta = porta;
        this.txaErro = txaErro;
        this.pkgRecebido = pkgRecebido;
    }

    @Override
    public void run() {
        MulticastSocket socket = null;
        try {
            // determina endereco e porta
            InetAddress grupo = InetAddress.getByName(host);

            // cria multicast socket e se une ao grupo
            socket = new MulticastSocket(porta);

            socket.joinGroup(grupo);
        } catch (IOException ioe) {
            txaErro.append(ioe + "\n");
        }

        boolean ativo = true; // habilita laco
        ArrayList<UsuariosAlteracoes> lista = new ArrayList<>();

        while (ativo) {
            try {
                // prepara buffer (vazio)
                byte[] buf = new byte[256];

                // prepara pacote para resposta
                DatagramPacket pacote = new DatagramPacket(buf, buf.length);

                // recebe pacote
                socket.receive(pacote);

                // exibe dados na area de texto
                String dados = new String(pacote.getData()).trim();

                if (dados.equals(TelaPrincipal.userH.getLogin())) {
                    dados = "é voce leandro";
                }

                try {
                    Session sessao = HibernateUtil.getSessionFactory().openSession();
                    sessao.beginTransaction();

                    dados = "";
                    List<Object[]> query = sessao.createSQLQuery("select * from viewUsuariosAlteracoes where id_usuario = " + TelaPrincipal.userH.getId()).list();
                    for (Object[] qry : query) {
                        dados = dados + "\n" + qry[2];
                    }
                    sessao.getTransaction().commit();

                } catch (Exception e) {
                    System.out.println("erro ao chamar view: " + e);
                }

                pkgRecebido.append(dados + "\n");

            } catch (IOException ioe) {
                txaErro.append(ioe + "\n");
            }
        }
        // fecha socket
        socket.close();
    }

}
