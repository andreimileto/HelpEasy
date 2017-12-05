/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author fabricio
 */
public class Servidor extends Thread {

    String endereco;
    int porta;
    String mensagem;
    boolean enviar = false;

    public Servidor(String endereco, int porta, String mensagem) {
        this.mensagem = mensagem;
        this.endereco = endereco;
        this.porta = porta;
    }

    public void definirEnvio(boolean enviar) {
        this.enviar = enviar;
    }

    public void definirMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
                if (enviar) {
                    byte[] b = mensagem.getBytes();
                    InetAddress addr = InetAddress.getByName(endereco);
                    DatagramSocket ds = new DatagramSocket();
                    DatagramPacket pkg = new DatagramPacket(b, b.length, addr, porta);
                    ds.send(pkg);
                    enviar = false;
                }
            } catch (Exception e) {
                System.out.println("Nao foi possivel enviar a mensagem");
            }
        }
    }

}
