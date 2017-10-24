/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import DAO.CidadeDAO;
import DAO.EnvioEmailDAO;

import entidade.EnvioEmail;
import java.util.ArrayList;

/**
 *
 * @author Mileto
 */
public class ControleEnvioEmail {

    EnvioEmail envioEmail;

    public String salvar(EnvioEmail envioEmail) {
        this.envioEmail = envioEmail;

        //verifica se o tamanho do nome é <3, caso seja, não conseguirá cadastrar.
        try {
            
        if (envioEmail.getEmail().length() < 5) {
            return "Erro ao salvar Cadastro\n É preciso que o e-mail tenha mais que 5 caracteres.";
        }
        if (envioEmail.getSenha().length()<5) {
            return "Erro ao salvar Cadastro\n É preciso que a senha tenha mais que 5 caracteres.";
        }
        if (envioEmail.getTitulo().length()<5) {
            return "Erro ao salvar Cadastro\n É preciso que o título tenha mais que 5 caracteres.";
        }
        if (envioEmail.getMensagem().length()<5) {
            return "Erro ao salvar Cadastro\n É preciso que a mensagem tenha mais que 5 caracteres.";
        }
        } catch (Exception e) {
            return "Erro ao salvar cadastro\n Verifique com o suporte.";
        }
        

        EnvioEmailDAO envioEmailDAO = new EnvioEmailDAO();
        ArrayList<EnvioEmail> envioEmails = new ArrayList<>();

        envioEmails = listar(envioEmail);

        //verifica se existe algum cadastro com o mesmo nome que seja um ID diferente do que está alterando.
//        for (int i = 0; i < envioEmails.size(); i++) {
//            if (this.envioEmails.getDescricao().equalsIgnoreCase(envioEmails.get(i).getDescricao()) && envioEmail.getId() != cidades.get(i).getId()) {
//                return "Erro ao salvar Cidade\nJá existe um cadastro com esse nome!";
//            }
//
//        }

        //caso as duas validações acima não interfira no cadastro, será efetuado o cadasro
        if (envioEmailDAO.salvar(envioEmail)) {
            return "ok";
        } else {
            return "Erro ao salvar e-mail\nEntre em contato com o suporte";
        }
    }

    public ArrayList<EnvioEmail> listar(EnvioEmail envioEmail) {
        this.envioEmail = envioEmail;
        EnvioEmailDAO envioEmailDAO = new EnvioEmailDAO();
        return envioEmailDAO.listar(this.envioEmail);
    }

}
