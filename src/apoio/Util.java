/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apoio;

import controle.ControleEnvioEmail;
import entidade.EnvioEmail;
import entidade.Cliente;
import java.net.MalformedURLException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.Multipart;

/**
 *
 * @author Mileto
 */
public class Util {
//Cliente cliente;

    public static void enviodeEmail(Cliente cli) {
        Cliente cliente = new Cliente();
        cliente = cli;
        EnvioEmail Email = new EnvioEmail();
        ControleEnvioEmail controleEnvioEmail = new ControleEnvioEmail();
        ArrayList<EnvioEmail> emails = new ArrayList<>();
        emails = controleEnvioEmail.listar(Email);

        if (emails.get(0).getEnvioAtivo() == 'T') {

            boolean envio = false;

            Properties props = new Properties();

            /**
             * Parâmetros de conexão com servidor Hotmail
             */
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.socketFactory.port", "587");
            props.put("mail.smtp.socketFactory.fallback", "false");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            if (emails.get(0).getDominio() == 0) {
                props.put("mail.smtp.host", "smtp.gmail.com");
            } else if (emails.get(0).getDominio() == 1) {
                props.put("mail.smtp.host", "smtp.live.com");
            } else if (emails.get(0).getDominio() == 2) {
                props.put("mail.smtp.host", "smtps.bol.com.br");
            }

            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    ControleEnvioEmail controleEnvioEmail = new ControleEnvioEmail();
                    ArrayList<EnvioEmail> emails = new ArrayList<>();
                    emails = controleEnvioEmail.listar(Email);
                    return new PasswordAuthentication(emails.get(0).getEmail(), emails.get(0).getSenha());
                }
            });
            session.setDebug(true);
            try {
                // cria a mensagem
                MimeMessage msg = new MimeMessage(session);
                msg.setFrom(new InternetAddress(emails.get(0).getEmail()));
                InternetAddress[] address = {new InternetAddress(cliente.getEmail())};
                msg.setRecipients(Message.RecipientType.TO, address);
                msg.setSubject(emails.get(0).getTitulo());

                // cria a primeira parte da mensagem
                MimeBodyPart mbp1 = new MimeBodyPart();
                mbp1.setText(emails.get(0).getMensagem());

                // cria a segunda parte da mensage
                MimeBodyPart mbp2 = new MimeBodyPart();

                // anexa o arquivo na mensagem
                FileDataSource fds = new FileDataSource("C:\\Users\\Mileto\\Documents\\NetBeansProjects\\HelpEasy\\libs\\modelo.png");
                mbp2.setDataHandler(new DataHandler(fds));
                mbp2.setFileName(fds.getName());

                // cria a Multipart
                Multipart mp = new MimeMultipart();
                mp.addBodyPart(mbp1);
                mp.addBodyPart(mbp2);

                // adiciona a Multipart na mensagem
                msg.setContent(mp);

                // configura a data: cabecalho
                msg.setSentDate(new Date());

                // envia a mensagem
                Transport.send(msg);

            } catch (MessagingException mex) {
                mex.printStackTrace();
                Exception ex = null;
                if ((ex = mex.getNextException()) != null) {
                    ex.printStackTrace();

                }
            }
        }
    }
}
