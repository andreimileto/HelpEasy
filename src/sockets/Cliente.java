/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import javax.swing.JTextArea;

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
                
                pkgRecebido.append(dados + "\n");

            } catch (IOException ioe) {
                txaErro.append(ioe + "\n");
            }
        }
        // fecha socket
        socket.close();
    }
    
}
