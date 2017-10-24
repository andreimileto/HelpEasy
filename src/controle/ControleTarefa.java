/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import DAO.ClienteDAO;
import DAO.TarefaDAO;
import apoio.Formatacao;
import entidade.Cliente;
import entidade.Tarefa;
import entidade.Versao;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Mileto
 */
public class ControleTarefa {

    Tarefa tarefa;

    public String salvar(Tarefa tarefa) {
        this.tarefa = tarefa;
        String mensagem = "Erro ao salvar tarefa:\n";
        TarefaDAO tarefaDAO = new TarefaDAO();
        if (tarefa.getSituacao() == 'A') {

            if (tarefa.getTitulo().length() < 1) {
                mensagem = mensagem + "- Título não está preenchido\n";
            }

            if (tarefa.getDescricao().length() < 1) {
                mensagem = mensagem + "- Descrição não está preenchida\n";
            }
            if (tarefa.getCliente().getId() == 0) {

            }
            if (tarefa.getFase().getId() < 1) {
                mensagem = mensagem + "- Fase não está preenchida\n";
            }
            if (tarefa.getModulo().getId() < 1) {
                mensagem = mensagem + "- Módulo não está preenchido\n";
            }

            if (tarefa.getMotivo().getId() < 1) {
                mensagem = mensagem + "- Motivo não está preenchido\n";
            }
            if (tarefa.getPrioridade().getId() < 1) {
                mensagem = mensagem + "- Prioridade não está preenchido\n";
            }

            if (tarefa.getProjeto().getId() < 1) {
                mensagem = mensagem + "- Projeto não está preenchido\n";
            }

            if (tarefa.getVersaoByIdVersaoBug().getId() < 1) {
                Versao versaoBug = new Versao();
                versaoBug.setId(1);
                tarefa.setVersaoByIdVersaoBug(versaoBug);
            }

            if (tarefa.getVersaoByIdVersaoCorrecao().getId() < 1) {
                Versao versaoCorrecao = new Versao();
                versaoCorrecao.setId(1);
                tarefa.setVersaoByIdVersaoBug(versaoCorrecao);
            }

//            if (cliente.getEndereco().length() > 150) {
//                mensagem = mensagem + "Endereço não pode ultrapassar 150 caracteres\n";
//            }
//            if (cliente.getCidade().getId() < 1) {
//                mensagem = mensagem + "Cidade não selecionada\n";
//            }
//
//            if (cliente.getTipoCadastro() == 'F') {
//                //System.out.println("antes do if..."+tffCpfCnpj.getText());
//                try {
//
//                    if (!Validacao.validarCPF(Formatacao.removerFormatacao(cliente.getCpfCnpj()))) {
//                        // System.out.println("entrou no if do cpf como certo");
//                        mensagem = mensagem + "CPF incorreto\n";
//
//                    }
//                } catch (Exception e) {
//                    mensagem = mensagem + "Erro ao validar CPF\n";
//                }
//
//            } else {
//                try {
//                    if (!Validacao.validarCNPJ(Formatacao.removerFormatacao(cliente.getCpfCnpj()))) {
//
//                        mensagem = mensagem + "CNPJ incorreto\n";
//                    }
//                } catch (Exception e) {
//                    mensagem = mensagem + "erro ao validar CNPJ\n";
//
//                }
//                // System.out.println("ok..."+ok);
//            }
//
//            //verifica se o tamanho do nome é <3, caso seja, não conseguirá cadastrar.
//            if (cliente.getRazaoSocial().length() < 3 || cliente.getRazaoSocial().length() > 150) {
//                mensagem = mensagem + "É preciso que o nome tenha mais que dois caracteres na descrição ou menos que 150\n";
////            return "Erro ao salvar Cliente\nÉ preciso que o nome tenha mais que dois caracteres na descrição";
//            }
//
//            ClienteDAO clienteDAO = new ClienteDAO();
//            ArrayList<Cliente> clientes = new ArrayList<>();
//
//            clientes = listar(cliente);
//
//            //verifica se existe algum cadastro com o mesmo nome que seja um ID diferente do que está alterando.
//            for (int i = 0; i < clientes.size(); i++) {
//                if (this.cliente.getCpfCnpj().equalsIgnoreCase(clientes.get(i).getCpfCnpj()) && cliente.getId() != clientes.get(i).getId()) {
//                    mensagem = mensagem + "Já existe um cadastro com esse CPF/CNPJ!\n";
////                return "Erro ao salvar Cliente\nJá existe um cadastro com esse nome!";
//                }
//
//            }
//
//            //caso as duas validações acima não interfira no cadastro, será efetuado o cadasro
            if (mensagem.length() < 26) {
                if (tarefaDAO.salvar(tarefa)) {
                    //int maiorId = tarefaDAO.ultimoId(tarefa);
                    //Formatacao.criarDiretorioTarefa(maiorId + "");
                    
                    JOptionPane.showMessageDialog(null, "Id tarefa "+tarefa.getId());
                    return "ok";
                } else {
                    return mensagem = mensagem + "\nEntre em contato com o suporte";

                }
            } else {
                return mensagem;
            }
        } else {

            if (tarefaDAO.salvar(tarefa)) {
                return "ok";
            } else {
                return mensagem = mensagem + "\nEntre em contato com o suporte";

            }
        }

    }

//    public ArrayList<Cliente> listar(Cliente cliente) {
//        this.cliente = cliente;
//        ClienteDAO clienteDAO = new ClienteDAO();
//        return clienteDAO.listar(this.cliente);
//    }
}
