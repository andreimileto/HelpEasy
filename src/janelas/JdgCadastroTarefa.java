package janelas;

import DAO.ClienteDAO;
import DAO.TarefaDAO;
import DAO.TarefaUsuarioDAO;
import apoio.Formatacao;
import apoio.Util;
import controle.ControleMovimentoTarefa;
import controle.ControleTarefa;
import entidade.Cidade;
import entidade.Cliente;
import entidade.Fase;
import entidade.Modulo;
import entidade.Motivo;
import entidade.MovimentoTarefa;
import entidade.Prioridade;
import entidade.Projeto;
import entidade.Tarefa;
import entidade.TarefaUsuario;
import entidade.Usuario;
import entidade.Versao;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mileto
 */
public class JdgCadastroTarefa extends javax.swing.JDialog {

    /**
     * Creates new form JdgCadastroTarefa
     */
    Cliente cliente;
    Cidade cidade;
    Projeto projeto;
    Motivo motivo;
    Prioridade prioridade;
    Fase fase;
    Tarefa tarefa;
    Versao versaoBug;
    Versao versaoCorrecao;
    Modulo modulo;
    Usuario autor;
    Usuario usuarioResponsavel;
    ArrayList<TarefaUsuario> colaboradores;
    TarefaUsuario tarefaUsuario;
    Usuario novoUsuario;

    public JdgCadastroTarefa(java.awt.Frame parent, boolean modal, Usuario usuarioAutor) {
        super(parent, modal);
        initComponents();
        tarefa = new Tarefa();
        cidade = new Cidade();
        cliente = new Cliente(cidade);
        projeto = new Projeto();
        motivo = new Motivo();
        prioridade = new Prioridade();
        fase = new Fase();
        modulo = new Modulo();
        versaoBug = new Versao();
        versaoCorrecao = new Versao();
        usuarioResponsavel = new Usuario();
        this.autor = usuarioAutor;
        colaboradores = new ArrayList<>();
        tarefaUsuario = new TarefaUsuario();
        listarDados();
    }

    public JdgCadastroTarefa(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cidade = new Cidade();
        cliente = new Cliente(cidade);
        projeto = new Projeto();
        motivo = new Motivo();
        prioridade = new Prioridade();
        fase = new Fase();
        modulo = new Modulo();
        versaoBug = new Versao();
        versaoCorrecao = new Versao();
        colaboradores = new ArrayList<>();
        tarefaUsuario = new TarefaUsuario();
        listarDados();
    }

    public JdgCadastroTarefa(java.awt.Frame parent, boolean modal, Tarefa tarefa, Motivo motivo, Usuario autor, Usuario responsavel, Modulo modulo, Projeto projeto, Prioridade prioridade, Fase fase, Versao versaoBug, Versao versaoCorrecao, Cliente cliente) {
        super(parent, modal);
        initComponents();

        this.cidade = new Cidade();

        this.tarefa = tarefa;
        this.motivo = motivo;
        tarefa.setMotivo(motivo);
        this.autor = autor;
        tarefa.setUsuarioByIdUsuarioAutor(autor);
        this.usuarioResponsavel = responsavel;
        tarefa.setUsuarioByIdUsuarioResponsavel(responsavel);
        this.projeto = projeto;
        tarefa.setProjeto(projeto);
        this.prioridade = prioridade;
        tarefa.setPrioridade(prioridade);
        this.modulo = modulo;
        tarefa.setModulo(modulo);
        this.versaoBug = versaoBug;
        tarefa.setVersaoByIdVersaoBug(versaoBug);
        this.versaoCorrecao = versaoCorrecao;
        tarefa.setVersaoByIdVersaoCorrecao(versaoCorrecao);
        this.fase = fase;
        tarefa.setFase(fase);
        this.cliente = cliente;
        this.cliente.setCidade(cidade);
        tarefa.setCliente(cliente);
        colaboradores = new ArrayList<>();
        tarefaUsuario = new TarefaUsuario();
        tarefaUsuario.setTarefa(tarefa);
        tarefaUsuario.setUsuario(novoUsuario);
        TarefaUsuarioDAO tarefaUsuarioDAO = new TarefaUsuarioDAO();
        colaboradores = tarefaUsuarioDAO.listar(tarefaUsuario);
        listarDados();
        listarColaboradores();
        habilitarDesabilitarBotoes();
    }

    private void listarDados() {
        try {
            if (tarefa.getId() > 0) {
                tfdId.setText(tarefa.getId() + "");
                //   JOptionPane.showMessageDialog(rootPane, tarefa.getUsuarioByIdUsuarioAutor().getId() + "..id autor..");
                // JOptionPane.showMessageDialog(rootPane, tarefa.getUsuarioByIdUsuarioAutor().getNome() + "..nome autor..");
                tfdNomeAutor.setText(tarefa.getUsuarioByIdUsuarioAutor().getNome());
                tfdNomeResponsavel.setText(tarefa.getUsuarioByIdUsuarioResponsavel().getNome());
                tfdNomeCliente.setText(tarefa.getCliente().getRazaoSocial());
                tfdFase.setText(tarefa.getFase().getDescricao());
                tfdNomeModulo.setText(tarefa.getModulo().getDescricao());
                // JOptionPane.showMessageDialog(rootPane, "id do motivo é..."+tarefa.getMotivo().getId());
                //  JOptionPane.showMessageDialog(rootPane, "descrição do motivo é..."+tarefa.getMotivo().getDescricao());
                tfdNomeMotivo.setText(tarefa.getMotivo().getDescricao());
                tfdNomePrioridade.setText(tarefa.getPrioridade().getDescricao());
                tfdNomeProjeto.setText(tarefa.getProjeto().getDescricao());
                tfdTituloTarefa.setText(tarefa.getTitulo());
                tfaDescricaoTarefa.setText(tarefa.getDescricao());
                tfdVersaoBug.setText(tarefa.getVersaoByIdVersaoBug().getDescricao());
                tfdVersaoCorrecao.setText(tarefa.getVersaoByIdVersaoCorrecao().getDescricao());
                //     JOptionPane.showMessageDialog(rootPane, tarefa.getDatahoraPrevisao() + "previsao");
                JdcPrevisão.setDate(tarefa.getDatahoraPrevisao());
                JdcInclusao.setDate(tarefa.getDatahoraCriacao());
                JdcUltimaModificacao.setDate(tarefa.getDatahoraConclusao());
                btnAdicionar.setEnabled(true);
                btnLocalizarColaboradores.setEnabled(true);
                btnLocalizarModulo.setEnabled(true);
                btnLocalizarVersaoBug.setEnabled(true);
                btnLocalizarVersaoCorrecao.setEnabled(true);

            } else {
                tfaNovoMovimento.setEnabled(false);
                tfdNomeAutor.setText(autor.getNome());
            }
        } catch (Exception e) {
            tfaNovoMovimento.setEnabled(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfdId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfdNomeCliente = new javax.swing.JTextField();
        tfdNomeModulo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnLocalizarModulo = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        btnLocalizarCliente = new javax.swing.JButton();
        tfdNomeAutor = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfdNomeProjeto = new javax.swing.JTextField();
        tfdNomePrioridade = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnLocalizarPrioridade = new javax.swing.JButton();
        btnLocalizarProjeto = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        tfdNomeResponsavel = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tfdNomeMotivo = new javax.swing.JTextField();
        tfdVersaoBug = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnLocalizarVersaoBug = new javax.swing.JButton();
        btnLocalizarMotivo = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        tfdFase = new javax.swing.JTextField();
        btnLocalizarFase = new javax.swing.JButton();
        btnLocalizarVersaoCorrecao = new javax.swing.JButton();
        tfdVersaoCorrecao = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        JdcInclusao = new com.toedter.calendar.JDateChooser();
        jLabel15 = new javax.swing.JLabel();
        JdcPrevisão = new com.toedter.calendar.JDateChooser();
        jLabel16 = new javax.swing.JLabel();
        JdcUltimaModificacao = new com.toedter.calendar.JDateChooser();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        tfdNomeColaborador = new javax.swing.JTextField();
        btnLocalizarColaboradores = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblColaboradores = new javax.swing.JTable();
        btnAdicionar = new javax.swing.JButton();
        btnLocalizarResponsavel = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tfaDescricaoTarefa = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        tfdTituloTarefa = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tfaNovoMovimento = new javax.swing.JTextArea();
        jLabel14 = new javax.swing.JLabel();
        btnSalvar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        btnLocalizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("janelas.JdgCadastroTarefa"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cadastro de tarefa", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14), new java.awt.Color(0, 51, 255))); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel1.setText("ID:");

        tfdId.setEditable(false);

        jLabel2.setText("Cliente:");

        tfdNomeCliente.setEditable(false);

        tfdNomeModulo.setEditable(false);

        jLabel5.setText("Módulo:");

        btnLocalizarModulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Lupa3.png"))); // NOI18N
        btnLocalizarModulo.setEnabled(false);
        btnLocalizarModulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocalizarModuloActionPerformed(evt);
            }
        });

        jLabel9.setText("Autor:");

        btnLocalizarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Lupa3.png"))); // NOI18N
        btnLocalizarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocalizarClienteActionPerformed(evt);
            }
        });

        tfdNomeAutor.setEditable(false);

        jLabel3.setText("Projeto:");

        tfdNomeProjeto.setEditable(false);

        tfdNomePrioridade.setEditable(false);

        jLabel6.setText("Prioridade:");

        btnLocalizarPrioridade.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Lupa3.png"))); // NOI18N
        btnLocalizarPrioridade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocalizarPrioridadeActionPerformed(evt);
            }
        });

        btnLocalizarProjeto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Lupa3.png"))); // NOI18N
        btnLocalizarProjeto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocalizarProjetoActionPerformed(evt);
            }
        });

        jLabel10.setText("Responsável:");

        tfdNomeResponsavel.setEditable(false);

        jLabel4.setText("Motivo:");

        tfdNomeMotivo.setEditable(false);

        tfdVersaoBug.setEditable(false);

        jLabel7.setText("Versão BUG:");

        btnLocalizarVersaoBug.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Lupa3.png"))); // NOI18N
        btnLocalizarVersaoBug.setEnabled(false);
        btnLocalizarVersaoBug.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocalizarVersaoBugActionPerformed(evt);
            }
        });

        btnLocalizarMotivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Lupa3.png"))); // NOI18N
        btnLocalizarMotivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocalizarMotivoActionPerformed(evt);
            }
        });

        jLabel11.setText("Fase:");

        tfdFase.setEditable(false);

        btnLocalizarFase.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Lupa3.png"))); // NOI18N
        btnLocalizarFase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocalizarFaseActionPerformed(evt);
            }
        });

        btnLocalizarVersaoCorrecao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Lupa3.png"))); // NOI18N
        btnLocalizarVersaoCorrecao.setEnabled(false);
        btnLocalizarVersaoCorrecao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocalizarVersaoCorrecaoActionPerformed(evt);
            }
        });

        tfdVersaoCorrecao.setEditable(false);

        jLabel8.setText("Correção:");

        JdcInclusao.setEnabled(false);

        jLabel15.setText("Inclusão:");

        jLabel16.setText("Previsão:");

        JdcUltimaModificacao.setEnabled(false);

        jLabel17.setText("modificação:");

        jLabel18.setText("Colaboradores:");

        tfdNomeColaborador.setEditable(false);

        btnLocalizarColaboradores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Lupa3.png"))); // NOI18N
        btnLocalizarColaboradores.setEnabled(false);
        btnLocalizarColaboradores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocalizarColaboradoresActionPerformed(evt);
            }
        });

        tblColaboradores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "ID TAREFA", "NOME"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblColaboradores);

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/more.png"))); // NOI18N
        btnAdicionar.setEnabled(false);
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnLocalizarResponsavel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Lupa3.png"))); // NOI18N
        btnLocalizarResponsavel.setPreferredSize(new java.awt.Dimension(51, 25));
        btnLocalizarResponsavel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocalizarResponsavelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel3))
                                .addGap(4, 4, 4)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfdNomeResponsavel, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                    .addComponent(tfdNomeProjeto)
                                    .addComponent(tfdNomeMotivo)
                                    .addComponent(tfdNomeModulo, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(2, 2, 2)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnLocalizarMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnLocalizarProjeto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnLocalizarModulo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(27, 27, 27))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(btnLocalizarResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel8))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(tfdVersaoBug, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnLocalizarVersaoBug, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addComponent(tfdVersaoCorrecao, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(6, 6, 6)
                                                .addComponent(btnLocalizarVersaoCorrecao, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addGap(36, 36, 36)
                                            .addComponent(tfdNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnLocalizarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel11)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(JdcPrevisão, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                    .addComponent(tfdFase, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(6, 6, 6)
                                                    .addComponent(btnLocalizarFase, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                .addGap(39, 39, 39))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(tfdNomePrioridade, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLocalizarPrioridade, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(10, 10, 10)
                                .addComponent(tfdNomeColaborador, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLocalizarColaboradores, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(20, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(26, 26, 26)
                        .addComponent(tfdId, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addGap(10, 10, 10)
                        .addComponent(tfdNomeAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JdcInclusao, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JdcUltimaModificacao, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(JdcInclusao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JdcUltimaModificacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(tfdNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2))
                                    .addComponent(btnLocalizarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(tfdVersaoBug, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel7))
                                    .addComponent(btnLocalizarVersaoBug, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(jLabel18))
                                    .addComponent(tfdNomeColaborador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnLocalizarColaboradores, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfdId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tfdNomeAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel15))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel9))))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel10))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnLocalizarResponsavel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfdNomeResponsavel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tfdNomeProjeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3))
                            .addComponent(btnLocalizarProjeto, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnLocalizarMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tfdNomeMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4))
                            .addComponent(jLabel8)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tfdVersaoCorrecao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnLocalizarVersaoCorrecao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnLocalizarFase, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel11)
                                        .addComponent(tfdFase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(21, 21, 21)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(JdcPrevisão, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(6, 6, 6)
                                            .addComponent(jLabel16))))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnLocalizarModulo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(3, 3, 3)
                                            .addComponent(jLabel5))
                                        .addComponent(tfdNomeModulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(tfdNomePrioridade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6))
                                        .addComponent(btnLocalizarPrioridade, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        tfaDescricaoTarefa.setColumns(20);
        tfaDescricaoTarefa.setRows(5);
        jScrollPane1.setViewportView(tfaDescricaoTarefa);

        jLabel13.setText("DESCRIÇÃO");

        jLabel12.setText("TÍTULO");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(tfdTituloTarefa, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel12))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdTituloTarefa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        tfaNovoMovimento.setColumns(20);
        tfaNovoMovimento.setRows(5);
        jScrollPane2.setViewportView(tfaNovoMovimento);

        jLabel14.setText("NOVO MOVIMENTO:");

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/confirmar.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.setName("btnSalvar"); // NOI18N
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/lixeira16x16.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.setName("btnExcluir"); // NOI18N
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon_Schutdown16.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        btnLocalizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Lupa3.png"))); // NOI18N
        btnLocalizar.setText("Localizar");
        btnLocalizar.setName("btnLocalizar"); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane2))
                        .addContainerGap())
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnLocalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnExcluir)
                    .addComponent(btnSair)
                    .addComponent(btnLocalizar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnLocalizarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocalizarClienteActionPerformed
        //   limparCampos();
        JdgListaCliente clientes = new JdgListaCliente(null, true, cliente, cidade);
        clientes.setVisible(true);
        if (cliente.getId() > 0) {
            tfdNomeCliente.setText(cliente.getRazaoSocial());

        }
    }//GEN-LAST:event_btnLocalizarClienteActionPerformed

    private void btnLocalizarProjetoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocalizarProjetoActionPerformed

        JdgListaProjeto listaProjeto = new JdgListaProjeto(null, true, projeto);
        listaProjeto.setVisible(true);
        if (projeto.getId() > 0) {
            tfdNomeProjeto.setText(projeto.getDescricao());
            versaoBug.setProjeto(projeto);
            versaoCorrecao.setProjeto(projeto);
            modulo.setProjeto(projeto);
            btnLocalizarVersaoBug.setEnabled(true);
            btnLocalizarVersaoCorrecao.setEnabled(true);
            btnLocalizarModulo.setEnabled(true);
        }


    }//GEN-LAST:event_btnLocalizarProjetoActionPerformed

    private void btnLocalizarMotivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocalizarMotivoActionPerformed

        JdgListaMotivo listaMotivo = new JdgListaMotivo(null, true, motivo);
        listaMotivo.setVisible(true);

        if (motivo.getId() > 0) {
            tfdNomeMotivo.setText(motivo.getDescricao());

        }
    }//GEN-LAST:event_btnLocalizarMotivoActionPerformed

    private void btnLocalizarPrioridadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocalizarPrioridadeActionPerformed

        JdgListaPrioridade listaPrioridade = new JdgListaPrioridade(null, true, prioridade);
        listaPrioridade.setVisible(true);
        if (prioridade.getId() > 0) {
            tfdNomePrioridade.setText(prioridade.getDescricao());
        }


    }//GEN-LAST:event_btnLocalizarPrioridadeActionPerformed

    private void btnLocalizarFaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocalizarFaseActionPerformed

        JdgListaFase listaFase = new JdgListaFase(null, true, fase);
        listaFase.setVisible(true);
        if (fase.getId() > 0) {
            tfdFase.setText(fase.getDescricao());

        }

    }//GEN-LAST:event_btnLocalizarFaseActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if (tfdId.getText().length() > 0) {
            tarefa.setId(Integer.parseInt(tfdId.getText()));
        }

        tarefa.setTitulo(tfdTituloTarefa.getText());
        tarefa.setDescricao(tfaDescricaoTarefa.getText());
        tarefa.setCliente(cliente);
        tarefa.setFase(fase);
        tarefa.setModulo(modulo);
        tarefa.setMotivo(motivo);
        tarefa.setPrioridade(prioridade);
        tarefa.setProjeto(projeto);
        tarefa.setSituacao('A');
        tarefa.setVersaoByIdVersaoBug(versaoBug);
        tarefa.setVersaoByIdVersaoCorrecao(versaoCorrecao);
        tarefa.setUsuarioByIdUsuarioAutor(autor);
        tarefa.setUsuarioByIdUsuarioResponsavel(usuarioResponsavel);
        Date dataAtual;
        try {
            dataAtual = Formatacao.getDataAtualEmDate();
            tarefa.setDatahoraCriacao(dataAtual);
            tarefa.setDatahoraPrevisao(JdcPrevisão.getDate());
            tarefa.setDatahoraConclusao(dataAtual);
        } catch (ParseException ex) {
            Logger.getLogger(JdgCadastroTarefa.class.getName()).log(Level.SEVERE, null, ex);
        }

        ControleTarefa controleTarefa = new ControleTarefa();
        String mensagem = controleTarefa.salvar(tarefa);
        if (mensagem.equals("ok")) {
            if (colaboradores.size() > 0) {
                for (int i = 0; i < colaboradores.size(); i++) {
                    TarefaUsuarioDAO tarefaUsuarioDAO = new TarefaUsuarioDAO();
                    tarefaUsuarioDAO.salvar(colaboradores.get(i));
                }
            }

            if (tarefa.getId() > 0 && tfaNovoMovimento.getText().length()>0) {
                MovimentoTarefa movTarefa = new MovimentoTarefa();
                movTarefa.setTarefa(tarefa);
                movTarefa.setDescricao(tfaNovoMovimento.getText());
                movTarefa.setSituacao('A');
                movTarefa.setAnexo("xxx");
                movTarefa.setUsuario(TelaPrincipal.userH);
                try {
                    Date data;
                    data = Formatacao.getDataAtualEmDate();
                    movTarefa.setDatahoraMovimento(data);
                } catch (ParseException ex) {
                    TelaPrincipal.logH.gravaErro(JdgCadastroTarefa.class.getName(),"erro"); 
                }
                ControleMovimentoTarefa movimentotarefa = new ControleMovimentoTarefa();
                movimentotarefa.salvar(movTarefa);
            }

            Util.enviodeEmail(tarefa);
            if (tarefa.getId() == 0) {
                tfaNovoMovimento.setEnabled(false);
                tfaNovoMovimento.grabFocus();
            } else {
                tfaNovoMovimento.setText("");
                tfaNovoMovimento.grabFocus();
            }
            tfaNovoMovimento.setEnabled(true);
            tfaNovoMovimento.setText("");
            tfaNovoMovimento.grabFocus();
            JOptionPane.showMessageDialog(rootPane, "Tarefa registrada com sucesso");

        } else {
            JOptionPane.showMessageDialog(rootPane, mensagem);
        }


    }//GEN-LAST:event_btnSalvarActionPerformed

    private void limparCampos() {
        cliente = new Cliente();
        motivo = new Motivo();
        usuarioResponsavel = new Usuario();
        versaoBug = new Versao();
        versaoCorrecao = new Versao();
        modulo = new Modulo();
        projeto = new Projeto();
        prioridade = new Prioridade();
        fase = new Fase();
        tarefa = new Tarefa();
        tfdTituloTarefa.setText("");
        tfaDescricaoTarefa.setText("");
        tfdNomeResponsavel.setText("");
        tfdFase.setText("");
        tfdNomeCliente.setText("");
        tfdNomeModulo.setText("");
        tfdNomeMotivo.setText("");
        tfdNomePrioridade.setText("");
        tfdNomeProjeto.setText("");
        tfdVersaoBug.setText("");
        tfdVersaoCorrecao.setText("");
        tfdNomeAutor.setText("");
        JdcInclusao.setDate(null);
        JdcPrevisão.setDate(null);
        JdcUltimaModificacao.setDate(null);
        tfdId.setText("");
        colaboradores = new ArrayList<>();
        listarColaboradores();
    }

    private void btnLocalizarResponsavelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocalizarResponsavelActionPerformed
        JdgListaUsuario listaUsuario = new JdgListaUsuario(null, true, usuarioResponsavel);
        listaUsuario.setVisible(true);
        if (usuarioResponsavel.getId() > 0) {
            tfdNomeResponsavel.setText(usuarioResponsavel.getNome());

        }
    }//GEN-LAST:event_btnLocalizarResponsavelActionPerformed

    private void btnLocalizarModuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocalizarModuloActionPerformed
        JdgListaModulo listaModulo = new JdgListaModulo(null, true, modulo, projeto);
        listaModulo.setVisible(true);
        if (modulo.getId() > 0) {
            tfdNomeModulo.setText(modulo.getDescricao());
        }
    }//GEN-LAST:event_btnLocalizarModuloActionPerformed

    private void habilitarDesabilitarBotoes() {
        if (tarefa.getId() > 0) {
            btnLocalizarModulo.setEnabled(true);
            btnLocalizarColaboradores.setEnabled(true);
            btnLocalizarVersaoBug.setEnabled(true);
            btnLocalizarVersaoCorrecao.setEnabled(true);
            tfaNovoMovimento.setEnabled(true);
        } else {
            btnLocalizarModulo.setEnabled(false);
            btnLocalizarColaboradores.setEnabled(false);
            btnLocalizarVersaoBug.setEnabled(false);
            btnLocalizarVersaoCorrecao.setEnabled(false);
            tfaNovoMovimento.setEnabled(false);

        }
    }
    private void btnLocalizarVersaoBugActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocalizarVersaoBugActionPerformed
        JdgListaVersao listaVersao = new JdgListaVersao(null, true, versaoBug, projeto);
        listaVersao.setVisible(true);
        if (versaoBug.getId() > 0) {

            tfdVersaoBug.setText(versaoBug.getDescricao());

        }
    }//GEN-LAST:event_btnLocalizarVersaoBugActionPerformed

    private void btnLocalizarVersaoCorrecaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocalizarVersaoCorrecaoActionPerformed
        JdgListaVersao listaVersao = new JdgListaVersao(null, true, versaoCorrecao, projeto);
        listaVersao.setVisible(true);
        if (versaoCorrecao.getId() > 0) {

            tfdVersaoCorrecao.setText(versaoCorrecao.getDescricao());

        }
    }//GEN-LAST:event_btnLocalizarVersaoCorrecaoActionPerformed

    private void btnLocalizarColaboradoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocalizarColaboradoresActionPerformed

        novoUsuario = new Usuario();
        JdgListaUsuario listaUsuario = new JdgListaUsuario(null, true, novoUsuario);
        listaUsuario.setVisible(true);

        tfdNomeColaborador.setText(novoUsuario.getNome());


    }//GEN-LAST:event_btnLocalizarColaboradoresActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        if (novoUsuario.getId() > 0) {
            boolean ok = true;

            for (int i = 0; i < colaboradores.size(); i++) {
                if (novoUsuario.getId() == colaboradores.get(i).getUsuario().getId()) {
                    ok = false;
                }
            }
            if (ok) {
                tarefaUsuario = new TarefaUsuario();
                tarefaUsuario.setUsuario(novoUsuario);
                tarefaUsuario.setTarefa(tarefa);
                colaboradores.add(tarefaUsuario);
                listarColaboradores();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Erro ao adicionar colaborador:\nColaborador já adicionado na lista.");
            }

            tfdNomeColaborador.setText("");
        }
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        int retornoMensagem = JOptionPane.showConfirmDialog(rootPane, "Tem certeza que deseja excluir a tarefa " + tarefa.getId() + " ?");
        if (retornoMensagem == 0) {
            tarefa.setSituacao('I');
            ControleTarefa controleTarefa = new ControleTarefa();
            String retorno = controleTarefa.salvar(tarefa);
            if (retorno.equalsIgnoreCase("ok")) {
                JOptionPane.showMessageDialog(rootPane, "tarefa " + tarefa.getId() + " excluída com sucesso!");
                limparCampos();
            }
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void listarColaboradores() {
        try {
            //setar para tabela modelo de dados
            tblColaboradores.setModel(this.obterDadosParaTabelaCompleto());
            tblColaboradores.getColumnModel().getColumn(0).setPreferredWidth(1);
            tblColaboradores.getColumnModel().getColumn(1).setPreferredWidth(1);
            tblColaboradores.getColumnModel().getColumn(2).setPreferredWidth(100);

        } catch (Exception ex) {
            System.out.println("erro listarColaboradores " + ex);
            janelas.TelaPrincipal.logH.gravaErro(this.getClass().getName(), ex.getMessage());
        }
    }

    private DefaultTableModel obterDadosParaTabelaCompleto() throws Exception {
        DefaultTableModel dtm = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

//        TarefaUsuarioDAO tarefaUsuarioDAO = new TarefaUsuarioDAO();
//         colaboradores = tarefaUsuarioDAO.listar(tarefaUsuario);
        dtm.addColumn("ID");
        dtm.addColumn("ID TAREFA");
        dtm.addColumn("NOME");

        for (int i = 0; i < colaboradores.size(); i++) {

            dtm.addRow(new String[]{String.valueOf(colaboradores.get(i).getUsuario().getId()),
                String.valueOf(colaboradores.get(i).getTarefa().getId()),
                colaboradores.get(i).getUsuario().getNome()
            });
        }
        return dtm;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JdgCadastroTarefa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JdgCadastroTarefa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JdgCadastroTarefa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JdgCadastroTarefa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JdgCadastroTarefa dialog = new JdgCadastroTarefa(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser JdcInclusao;
    private com.toedter.calendar.JDateChooser JdcPrevisão;
    private com.toedter.calendar.JDateChooser JdcUltimaModificacao;
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnLocalizar;
    private javax.swing.JButton btnLocalizarCliente;
    private javax.swing.JButton btnLocalizarColaboradores;
    private javax.swing.JButton btnLocalizarFase;
    private javax.swing.JButton btnLocalizarModulo;
    private javax.swing.JButton btnLocalizarMotivo;
    private javax.swing.JButton btnLocalizarPrioridade;
    private javax.swing.JButton btnLocalizarProjeto;
    private javax.swing.JButton btnLocalizarResponsavel;
    private javax.swing.JButton btnLocalizarVersaoBug;
    private javax.swing.JButton btnLocalizarVersaoCorrecao;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblColaboradores;
    private javax.swing.JTextArea tfaDescricaoTarefa;
    private javax.swing.JTextArea tfaNovoMovimento;
    private javax.swing.JTextField tfdFase;
    private javax.swing.JTextField tfdId;
    private javax.swing.JTextField tfdNomeAutor;
    private javax.swing.JTextField tfdNomeCliente;
    private javax.swing.JTextField tfdNomeColaborador;
    private javax.swing.JTextField tfdNomeModulo;
    private javax.swing.JTextField tfdNomeMotivo;
    private javax.swing.JTextField tfdNomePrioridade;
    private javax.swing.JTextField tfdNomeProjeto;
    private javax.swing.JTextField tfdNomeResponsavel;
    private javax.swing.JTextField tfdTituloTarefa;
    private javax.swing.JTextField tfdVersaoBug;
    private javax.swing.JTextField tfdVersaoCorrecao;
    // End of variables declaration//GEN-END:variables
}
