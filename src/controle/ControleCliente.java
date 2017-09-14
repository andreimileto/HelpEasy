/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import DAO.CidadeDAO;
import DAO.ClienteDAO;
import apoio.Formatacao;
import apoio.Validacao;
import entidade.Cliente;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Mileto
 */
public class ControleCliente {

    Cliente cliente;

    public String salvar(Cliente cliente) {
        this.cliente = cliente;
        String mensagem = "Erro ao salvar cliente:\n";

        if (cliente.getSituacao() == 'A') {

            if (cliente.getEndereco().length() > 150) {
                mensagem = mensagem + "Endereço não pode ultrapassar 150 caracteres\n";
            }
            if (cliente.getCidade().getId() < 1) {
                mensagem = mensagem + "Cidade não selecionada\n";
            }

            if (cliente.getTipoCadastro() == 'F') {
                //System.out.println("antes do if..."+tffCpfCnpj.getText());
                try {

                    if (!Validacao.validarCPF(Formatacao.removerFormatacao(cliente.getCpfCnpj()))) {
                        // System.out.println("entrou no if do cpf como certo");
                        mensagem = mensagem + "CPF incorreto\n";

                    }
                } catch (Exception e) {
                    mensagem = mensagem + "Erro ao validar CPF\n";
                }

            } else {
                try {
                    if (!Validacao.validarCNPJ(Formatacao.removerFormatacao(cliente.getCpfCnpj()))) {

                        mensagem = mensagem + "CNPJ incorreto\n";
                    }
                } catch (Exception e) {
                    mensagem = mensagem + "erro ao validar CNPJ\n";

                }
                // System.out.println("ok..."+ok);
            }

            //verifica se o tamanho do nome é <3, caso seja, não conseguirá cadastrar.
            if (cliente.getRazaoSocial().length() < 3 || cliente.getRazaoSocial().length() > 150) {
                mensagem = mensagem + "É preciso que o nome tenha mais que dois caracteres na descrição ou menos que 150\n";
//            return "Erro ao salvar Cliente\nÉ preciso que o nome tenha mais que dois caracteres na descrição";
            }

            ClienteDAO clienteDAO = new ClienteDAO();
            ArrayList<Cliente> clientes = new ArrayList<>();

            clientes = listar(cliente);

            //verifica se existe algum cadastro com o mesmo nome que seja um ID diferente do que está alterando.
            for (int i = 0; i < clientes.size(); i++) {
                if (this.cliente.getCpfCnpj().equalsIgnoreCase(clientes.get(i).getCpfCnpj()) && cliente.getId() != clientes.get(i).getId()) {
                    mensagem = mensagem + "Já existe um cadastro com esse CPF/CNPJ!\n";
//                return "Erro ao salvar Cliente\nJá existe um cadastro com esse nome!";
                }

            }

            //caso as duas validações acima não interfira no cadastro, será efetuado o cadasro
            if (mensagem.length() < 26) {
                if (clienteDAO.salvar(cliente)) {
                    return "ok";
                } else {
                    return mensagem = mensagem + "\nEntre em contato com o suporte";

                }
            } else {
                return mensagem;
            }
        } else {
            ClienteDAO clienteDAO = new ClienteDAO();
            if (clienteDAO.salvar(cliente)) {
                return "ok";
            } else {
                return mensagem = mensagem + "\nEntre em contato com o suporte";

            }
        }

    }

    public ArrayList<Cliente> listar(Cliente cliente) {
        this.cliente = cliente;
        ClienteDAO clienteDAO = new ClienteDAO();
        return clienteDAO.listar(this.cliente);
    }

}
