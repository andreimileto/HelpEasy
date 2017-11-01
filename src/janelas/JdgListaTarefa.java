/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package janelas;

import DAO.ClienteDAO;
import DAO.TarefaDAO;
import entidade.Cidade;
import entidade.Cliente;
import entidade.Fase;
import entidade.Modulo;
import entidade.Motivo;
import entidade.Prioridade;
import entidade.Projeto;
import entidade.Tarefa;
import entidade.Usuario;
import entidade.Versao;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pc05
 */
public class JdgListaTarefa extends javax.swing.JDialog {

    /**
     * Creates new form JdgListaCliente
     */
    Cliente cliente;
    Cidade cid;
    Versao versaoBug;
    Versao versaoCorrecao;
    Modulo modulo;
    Motivo motivo;
    Projeto projeto;
    Prioridade prioridade;
    Fase fase;
    Usuario autor;
    Usuario responsavel;
    Tarefa tarefa;

    public JdgListaTarefa(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

    }
    
    public JdgListaTarefa(java.awt.Frame parent, boolean modal, Tarefa tarefa) {
        super(parent, modal);
        initComponents();
        this.tarefa = tarefa;
        listarTarefas();
    }

    public JdgListaTarefa(java.awt.Frame parent, boolean modal, Tarefa tarefa, Motivo motivo,Usuario autor, Usuario responsavel, Projeto projeto, Prioridade prioridade, Modulo modulo, Versao versaoBug, Versao versaoCorrecao,Fase fase,Cliente cliente) {
        super(parent, modal);

        initComponents();
        this.tarefa = tarefa;
        this.motivo = motivo;
        tarefa.setMotivo(motivo);
        this.autor = autor;
        tarefa.setUsuarioByIdUsuarioAutor(autor);
        this.responsavel= responsavel;
        tarefa.setUsuarioByIdUsuarioResponsavel(responsavel);
        this.projeto=projeto;
        tarefa.setProjeto(projeto);
        this.prioridade = prioridade;
        tarefa.setPrioridade(prioridade);
        this.modulo =modulo;
        tarefa.setModulo(modulo);
        this.versaoBug = versaoBug;
        tarefa.setVersaoByIdVersaoBug(versaoBug);
        this.versaoCorrecao = versaoCorrecao;
        tarefa.setVersaoByIdVersaoCorrecao(versaoCorrecao);
        this.fase = fase;
        tarefa.setFase(fase);
     
        this.cliente = cliente;
        tarefa.setCliente(cliente);
                
        // this.cid = cid;
        listarTarefas();

    }

    private void listarTarefas() {
        try {
            //setar para tabela modelo de dados
            tblListaTarefas.setModel(this.obterDadosParaTabelaCompleto());
            tblListaTarefas.getColumnModel().getColumn(0).setPreferredWidth(1);
            tblListaTarefas.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblListaTarefas.getColumnModel().getColumn(2).setPreferredWidth(150);
            tblListaTarefas.getColumnModel().getColumn(3).setPreferredWidth(80);
            tblListaTarefas.getColumnModel().getColumn(4).setPreferredWidth(80);
            tblListaTarefas.getColumnModel().getColumn(5).setPreferredWidth(20);
            tblListaTarefas.getColumnModel().getColumn(5).setPreferredWidth(20);

        } catch (Exception ex) {
            System.out.println("Erro " + ex);
            janelas.TelaPrincipal.logH.gravaErro(this.getClass().getName(), ex.getMessage());
        }
    }

    private DefaultTableModel obterDadosParaTabelaCompleto() throws Exception {
        DefaultTableModel dtm = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

//        cid.setDescricao("");
//        cliente.setRazaoSocial(tfdFiltro.getText());
//        cliente.setCpfCnpj(tfdFiltro.getText());
        TarefaDAO tarDAO = new TarefaDAO();
        ArrayList<Tarefa> tarefas = tarDAO.listar(tarefa);

        dtm.addColumn("ID");
        dtm.addColumn("CLIENTE");
        dtm.addColumn("TÍTULO");
        dtm.addColumn("AUTOR");
        dtm.addColumn("RESPONSÁVEL");
        dtm.addColumn("FASE");
        dtm.addColumn("ÚLTIMA MODIFICAÇÃO");

        for (int i = 0; i < tarefas.size(); i++) {

            dtm.addRow(new String[]{String.valueOf(tarefas.get(i).getId()),
                tarefas.get(i).getCliente().getRazaoSocial(),
                tarefas.get(i).getTitulo(),
                String.valueOf(tarefas.get(i).getUsuarioByIdUsuarioAutor().getNome()),
                String.valueOf(tarefas.get(i).getUsuarioByIdUsuarioResponsavel().getNome()),
                String.valueOf(tarefas.get(i).getFase().getDescricao()),
                String.valueOf(tarefas.get(i).getDatahoraConclusao())
            });
        }
        return dtm;
    }

    private void selecionado() throws Exception {

        int row = tblListaTarefas.getSelectedRow();
        this.tarefa.setId(Integer.parseInt(tblListaTarefas.getValueAt(row, 0).toString()));

        TarefaDAO tarDAO = new TarefaDAO();
        ArrayList<Tarefa> tarefas = tarDAO.listarUmId(tarefa);
        
       // JOptionPane.showMessageDialog(null, "data previsao "+ tarefas.get(0).getDatahoraPrevisao());

        this.tarefa = tarefas.get(0);
        tarefa.setDatahoraPrevisao(tarefas.get(0).getDatahoraPrevisao());
       // JOptionPane.showMessageDialog(rootPane, tarefa.getDatahoraPrevisao());
        motivo.setId(tarefas.get(0).getMotivo().getId());
        motivo.setDescricao(tarefas.get(0).getMotivo().getDescricao());
        tarefa.setMotivo(motivo);
        
        autor.setId(tarefas.get(0).getUsuarioByIdUsuarioAutor().getId());
       autor.setNome(tarefas.get(0).getUsuarioByIdUsuarioAutor().getNome());
       tarefa.setUsuarioByIdUsuarioAutor(autor);
       
       responsavel.setId(tarefas.get(0).getUsuarioByIdUsuarioResponsavel().getId());
       responsavel.setNome(tarefas.get(0).getUsuarioByIdUsuarioResponsavel().getNome());
       tarefa.setUsuarioByIdUsuarioResponsavel(responsavel);
       
       projeto.setId(tarefas.get(0).getProjeto().getId());
       projeto.setDescricao(tarefas.get(0).getProjeto().getDescricao());
       tarefa.setProjeto(projeto);
       
         prioridade.setId(tarefas.get(0).getPrioridade().getId());
       prioridade.setDescricao(tarefas.get(0).getPrioridade().getDescricao());
       tarefa.setPrioridade(prioridade);
       
       modulo.setId(tarefas.get(0).getModulo().getId());
        modulo.setDescricao(tarefas.get(0).getModulo().getDescricao());
       tarefa.setModulo(modulo);
       
           
       versaoBug.setId(tarefas.get(0).getVersaoByIdVersaoBug().getId());
       versaoBug.setDescricao(tarefas.get(0).getVersaoByIdVersaoBug().getDescricao());
       tarefa.setVersaoByIdVersaoBug(versaoBug);
       
       versaoCorrecao.setId(tarefas.get(0).getVersaoByIdVersaoCorrecao().getId());
       versaoCorrecao.setDescricao(tarefas.get(0).getVersaoByIdVersaoCorrecao().getDescricao());
       tarefa.setVersaoByIdVersaoCorrecao(versaoCorrecao);
       
       fase.setId(tarefas.get(0).getFase().getId());
       fase.setDescricao(tarefas.get(0).getFase().getDescricao());
       tarefa.setFase(fase);
       
       
       cliente.setId(tarefas.get(0).getCliente().getId());
       cliente.setRazaoSocial(tarefas.get(0).getCliente().getRazaoSocial());
       cliente.setEmail(tarefas.get(0).getCliente().getEmail());
       tarefa.setCliente(cliente);
        
   
        this.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tfdFiltro = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListaTarefas = new javax.swing.JTable();
        btnConfirmar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        tfdNomeResponsavel = new javax.swing.JTextField();
        btnLocalizarResponsavel = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        tfdNomeProjeto = new javax.swing.JTextField();
        btnLocalizarProjeto = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        tfdNomeMotivo = new javax.swing.JTextField();
        btnLocalizarMotivo = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        tfdNomeCliente = new javax.swing.JTextField();
        btnLocalizarCliente = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        tfdVersaoBug = new javax.swing.JTextField();
        btnLocalizarVersaoBug = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        tfdVersaoCorrecao = new javax.swing.JTextField();
        btnLocalizarVersaoCorrecao = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        tfdFase = new javax.swing.JTextField();
        btnLocalizarFase = new javax.swing.JButton();
        tfdNomePrioridade = new javax.swing.JTextField();
        btnLocalizarPrioridade = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        tfdAutor = new javax.swing.JTextField();
        btnLocalizarAutor = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        tfdId = new javax.swing.JTextField();
        btnLocalizarId = new javax.swing.JButton();
        btnLocalizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("EasyPDV - Lista de clientes");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setText("Lista Cadastro de Cliente");

        tfdFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfdFiltroKeyReleased(evt);
            }
        });

        tblListaTarefas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblListaTarefas.setFocusable(false);
        tblListaTarefas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListaTarefasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblListaTarefas);

        btnConfirmar.setText("Editar");
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon_Schutdown16.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        jLabel10.setText("Responsável:");

        tfdNomeResponsavel.setEditable(false);

        btnLocalizarResponsavel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Lupa3.png"))); // NOI18N
        btnLocalizarResponsavel.setPreferredSize(new java.awt.Dimension(51, 25));
        btnLocalizarResponsavel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocalizarResponsavelActionPerformed(evt);
            }
        });

        jLabel3.setText("Projeto:");

        tfdNomeProjeto.setEditable(false);

        btnLocalizarProjeto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Lupa3.png"))); // NOI18N
        btnLocalizarProjeto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocalizarProjetoActionPerformed(evt);
            }
        });

        jLabel4.setText("Motivo:");

        tfdNomeMotivo.setEditable(false);

        btnLocalizarMotivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Lupa3.png"))); // NOI18N
        btnLocalizarMotivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocalizarMotivoActionPerformed(evt);
            }
        });

        jLabel2.setText("Cliente:");

        tfdNomeCliente.setEditable(false);

        btnLocalizarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Lupa3.png"))); // NOI18N
        btnLocalizarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocalizarClienteActionPerformed(evt);
            }
        });

        jLabel7.setText("Versão BUG:");

        tfdVersaoBug.setEditable(false);

        btnLocalizarVersaoBug.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Lupa3.png"))); // NOI18N
        btnLocalizarVersaoBug.setEnabled(false);
        btnLocalizarVersaoBug.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocalizarVersaoBugActionPerformed(evt);
            }
        });

        jLabel8.setText("Correção:");

        tfdVersaoCorrecao.setEditable(false);

        btnLocalizarVersaoCorrecao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Lupa3.png"))); // NOI18N
        btnLocalizarVersaoCorrecao.setEnabled(false);
        btnLocalizarVersaoCorrecao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocalizarVersaoCorrecaoActionPerformed(evt);
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

        tfdNomePrioridade.setEditable(false);

        btnLocalizarPrioridade.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Lupa3.png"))); // NOI18N
        btnLocalizarPrioridade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocalizarPrioridadeActionPerformed(evt);
            }
        });

        jLabel6.setText("Prioridade:");

        jLabel12.setText("Autor:");

        tfdAutor.setEditable(false);

        btnLocalizarAutor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Lupa3.png"))); // NOI18N
        btnLocalizarAutor.setPreferredSize(new java.awt.Dimension(51, 25));
        btnLocalizarAutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocalizarAutorActionPerformed(evt);
            }
        });

        jLabel13.setText("Fase:");

        tfdId.setEditable(false);

        btnLocalizarId.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Lupa3.png"))); // NOI18N
        btnLocalizarId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocalizarIdActionPerformed(evt);
            }
        });

        btnLocalizar.setText("Localizar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(234, 234, 234)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 796, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(tfdNomePrioridade, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLocalizarPrioridade, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel13))
                                .addGap(4, 4, 4)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(tfdId, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnLocalizarId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(tfdNomeResponsavel)
                                            .addComponent(tfdNomeProjeto)
                                            .addComponent(tfdNomeMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(2, 2, 2)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(btnLocalizarMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(btnLocalizarProjeto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(btnLocalizarResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(tfdVersaoBug, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnLocalizarVersaoBug, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(tfdVersaoCorrecao, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(btnLocalizarVersaoCorrecao, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tfdFase, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(btnLocalizarFase, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(36, 36, 36)
                                        .addComponent(tfdNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnLocalizarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addGap(33, 33, 33)
                                        .addComponent(tfdAutor)
                                        .addGap(2, 2, 2)
                                        .addComponent(btnLocalizarAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(tfdFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnLocalizar)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12)
                            .addComponent(btnLocalizarAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfdAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(8, 8, 8))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnLocalizarId, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfdId, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel10))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnLocalizarResponsavel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfdNomeResponsavel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tfdNomeProjeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3))
                            .addComponent(btnLocalizarProjeto, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnLocalizarMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tfdNomeMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4))))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(tfdNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2))
                                    .addComponent(btnLocalizarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(tfdVersaoBug, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel7))
                                    .addComponent(btnLocalizarVersaoBug, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(tfdFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnLocalizar))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel8)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(tfdVersaoCorrecao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(btnLocalizarVersaoCorrecao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(btnLocalizarFase, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tfdFase, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(tfdNomePrioridade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel6))
                                                .addComponent(btnLocalizarPrioridade, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))))))))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConfirmar)
                    .addComponent(btnSair))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        try {
            selecionado();
        } catch (Exception ex) {
            System.out.println("Erro " + ex);
            janelas.TelaPrincipal.logH.gravaErro(this.getClass().getName(), ex.getMessage());
        }
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void tfdFiltroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdFiltroKeyReleased

    }//GEN-LAST:event_tfdFiltroKeyReleased

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void tblListaTarefasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListaTarefasMouseClicked
        if (evt.getClickCount() > 1) {
            int linhaSelecionada = tblListaTarefas.getSelectedRow();
            try {
                selecionado();
            } catch (Exception ex) {
                janelas.TelaPrincipal.logH.gravaErro(this.getClass().getName(), ex.getMessage());
            }
            this.setVisible(false);
        }
    }//GEN-LAST:event_tblListaTarefasMouseClicked

    private void btnLocalizarResponsavelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocalizarResponsavelActionPerformed
        JdgListaUsuario listaUsuario = new JdgListaUsuario(null, true, usuarioResponsavel);
        listaUsuario.setVisible(true);
        if (usuarioResponsavel.getId() > 0) {
            tfdNomeResponsavel.setText(usuarioResponsavel.getNome());

        }
    }//GEN-LAST:event_btnLocalizarResponsavelActionPerformed

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

    private void btnLocalizarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocalizarClienteActionPerformed
        //   limparCampos();
        JdgListaCliente clientes = new JdgListaCliente(null, true, cliente, cidade);
        clientes.setVisible(true);
        if (cliente.getId() > 0) {
            tfdNomeCliente.setText(cliente.getRazaoSocial());

        }
    }//GEN-LAST:event_btnLocalizarClienteActionPerformed

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

    private void btnLocalizarFaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocalizarFaseActionPerformed

        JdgListaFase listaFase = new JdgListaFase(null, true, fase);
        listaFase.setVisible(true);
        if (fase.getId() > 0) {
            tfdFase.setText(fase.getDescricao());

        }
    }//GEN-LAST:event_btnLocalizarFaseActionPerformed

    private void btnLocalizarPrioridadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocalizarPrioridadeActionPerformed

        JdgListaPrioridade listaPrioridade = new JdgListaPrioridade(null, true, prioridade);
        listaPrioridade.setVisible(true);
        if (prioridade.getId() > 0) {
            tfdNomePrioridade.setText(prioridade.getDescricao());
        }

    }//GEN-LAST:event_btnLocalizarPrioridadeActionPerformed

    private void btnLocalizarAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocalizarAutorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLocalizarAutorActionPerformed

    private void btnLocalizarIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocalizarIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLocalizarIdActionPerformed

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
            java.util.logging.Logger.getLogger(JdgListaTarefa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JdgListaTarefa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JdgListaTarefa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JdgListaTarefa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JdgListaTarefa dialog = new JdgListaTarefa(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JButton btnLocalizar;
    private javax.swing.JButton btnLocalizarAutor;
    private javax.swing.JButton btnLocalizarCliente;
    private javax.swing.JButton btnLocalizarFase;
    private javax.swing.JButton btnLocalizarId;
    private javax.swing.JButton btnLocalizarMotivo;
    private javax.swing.JButton btnLocalizarPrioridade;
    private javax.swing.JButton btnLocalizarProjeto;
    private javax.swing.JButton btnLocalizarResponsavel;
    private javax.swing.JButton btnLocalizarVersaoBug;
    private javax.swing.JButton btnLocalizarVersaoCorrecao;
    private javax.swing.JButton btnSair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblListaTarefas;
    private javax.swing.JTextField tfdAutor;
    private javax.swing.JTextField tfdFase;
    private javax.swing.JTextField tfdFiltro;
    private javax.swing.JTextField tfdId;
    private javax.swing.JTextField tfdNomeCliente;
    private javax.swing.JTextField tfdNomeMotivo;
    private javax.swing.JTextField tfdNomePrioridade;
    private javax.swing.JTextField tfdNomeProjeto;
    private javax.swing.JTextField tfdNomeResponsavel;
    private javax.swing.JTextField tfdVersaoBug;
    private javax.swing.JTextField tfdVersaoCorrecao;
    // End of variables declaration//GEN-END:variables
}
