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
        //listarTarefas();
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
        //listarTarefas();

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
if (tarefa.getTitulo()==null) {
            tarefa.setTitulo("");
        }
        if (tarefa.getDescricao() == null) {
            tarefa.setDescricao("");
        }
        TarefaDAO tarDAO = new TarefaDAO();
        ArrayList<Tarefa> tarefas = tarDAO.listar(tarefa);

        dtm.addColumn("ID");
        dtm.addColumn("CLIENTE");
        dtm.addColumn("TÍTULO");
        dtm.addColumn("AUTOR");
        dtm.addColumn("RESPONSÁVEL");
        dtm.addColumn("FASE");
        

        for (int i = 0; i < tarefas.size(); i++) {

            dtm.addRow(new String[]{String.valueOf(tarefas.get(i).getId()),
                tarefas.get(i).getCliente().getRazaoSocial(),
                tarefas.get(i).getTitulo(),
                String.valueOf(tarefas.get(i).getUsuarioByIdUsuarioAutor().getNome()),
                String.valueOf(tarefas.get(i).getUsuarioByIdUsuarioResponsavel().getNome()),
                String.valueOf(tarefas.get(i).getFase().getDescricao()),
//                String.valueOf(tarefas.get(i).getDatahoraConclusao())
                
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

        btnConfirmar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        tfdFiltroId = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfdNomeCliente = new javax.swing.JTextField();
        btnLocalizarCliente = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        tfdAutor = new javax.swing.JTextField();
        btnLocalizarAutor = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        tfdNomeResponsavel = new javax.swing.JTextField();
        btnLocalizarResponsavel = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        tfdNomePrioridade = new javax.swing.JTextField();
        btnLocalizarPrioridade = new javax.swing.JButton();
        tfdNomeProjeto = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnLocalizarProjeto = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        tfdNomeMotivo = new javax.swing.JTextField();
        btnLocalizarMotivo = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        tfdNomeModulo = new javax.swing.JTextField();
        btnLocalizarModulo = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        tfdFase = new javax.swing.JTextField();
        btnLocalizarFase = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        tfdVersaoBug = new javax.swing.JTextField();
        btnLocalizarVersaoBug = new javax.swing.JButton();
        tfdVersaoCorrecao = new javax.swing.JTextField();
        btnLocalizarVersaoCorrecao = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        tfdFiltro = new javax.swing.JTextField();
        btnLocalizar = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListaTarefas = new javax.swing.JTable();
        btnTeste = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("EasyPDV - Lista de clientes");

        btnConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/confirmar.png"))); // NOI18N
        btnConfirmar.setText("Selecionar");
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

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista de tarefas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(0, 51, 255))); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(0, 0, 255))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(tfdFiltroId, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 79, -1));

        jLabel9.setText("ID:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jLabel2.setText("Cliente:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, -1, -1));

        tfdNomeCliente.setEditable(false);
        jPanel1.add(tfdNomeCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, 180, -1));

        btnLocalizarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Lupa3.png"))); // NOI18N
        btnLocalizarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocalizarClienteActionPerformed(evt);
            }
        });
        jPanel1.add(btnLocalizarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 10, 30, 25));

        jLabel12.setText("Autor:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        tfdAutor.setEditable(false);
        jPanel1.add(tfdAutor, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 180, -1));

        btnLocalizarAutor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Lupa3.png"))); // NOI18N
        btnLocalizarAutor.setPreferredSize(new java.awt.Dimension(51, 25));
        btnLocalizarAutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocalizarAutorActionPerformed(evt);
            }
        });
        jPanel1.add(btnLocalizarAutor, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, 30, -1));

        jLabel10.setText("Responsável:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        tfdNomeResponsavel.setEditable(false);
        jPanel1.add(tfdNomeResponsavel, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 180, -1));

        btnLocalizarResponsavel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Lupa3.png"))); // NOI18N
        btnLocalizarResponsavel.setPreferredSize(new java.awt.Dimension(51, 25));
        btnLocalizarResponsavel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocalizarResponsavelActionPerformed(evt);
            }
        });
        jPanel1.add(btnLocalizarResponsavel, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, 30, -1));

        jLabel6.setText("Prioridade:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 50, -1, -1));

        tfdNomePrioridade.setEditable(false);
        jPanel1.add(tfdNomePrioridade, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 40, 180, -1));

        btnLocalizarPrioridade.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Lupa3.png"))); // NOI18N
        btnLocalizarPrioridade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocalizarPrioridadeActionPerformed(evt);
            }
        });
        jPanel1.add(btnLocalizarPrioridade, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 40, 30, 25));

        tfdNomeProjeto.setEditable(false);
        jPanel1.add(tfdNomeProjeto, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 180, -1));

        jLabel3.setText("Projeto:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        btnLocalizarProjeto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Lupa3.png"))); // NOI18N
        btnLocalizarProjeto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocalizarProjetoActionPerformed(evt);
            }
        });
        jPanel1.add(btnLocalizarProjeto, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, 30, 24));

        jLabel4.setText("Motivo:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 80, -1, -1));

        tfdNomeMotivo.setEditable(false);
        jPanel1.add(tfdNomeMotivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 70, 180, -1));

        btnLocalizarMotivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Lupa3.png"))); // NOI18N
        btnLocalizarMotivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocalizarMotivoActionPerformed(evt);
            }
        });
        jPanel1.add(btnLocalizarMotivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 70, 30, 25));

        jLabel5.setText("Módulo:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 110, -1, -1));

        tfdNomeModulo.setEditable(false);
        jPanel1.add(tfdNomeModulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 100, 180, -1));

        btnLocalizarModulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Lupa3.png"))); // NOI18N
        btnLocalizarModulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocalizarModuloActionPerformed(evt);
            }
        });
        jPanel1.add(btnLocalizarModulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 100, 30, 25));

        jLabel11.setText("Fase:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 20, -1, -1));

        tfdFase.setEditable(false);
        jPanel1.add(tfdFase, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 10, 180, -1));

        btnLocalizarFase.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Lupa3.png"))); // NOI18N
        btnLocalizarFase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocalizarFaseActionPerformed(evt);
            }
        });
        jPanel1.add(btnLocalizarFase, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 10, 30, 25));

        jLabel7.setText("Versão BUG:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 50, -1, -1));

        tfdVersaoBug.setEditable(false);
        jPanel1.add(tfdVersaoBug, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 40, 180, -1));

        btnLocalizarVersaoBug.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Lupa3.png"))); // NOI18N
        btnLocalizarVersaoBug.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocalizarVersaoBugActionPerformed(evt);
            }
        });
        jPanel1.add(btnLocalizarVersaoBug, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 40, 30, 25));

        tfdVersaoCorrecao.setEditable(false);
        jPanel1.add(tfdVersaoCorrecao, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 70, 180, -1));

        btnLocalizarVersaoCorrecao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Lupa3.png"))); // NOI18N
        btnLocalizarVersaoCorrecao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocalizarVersaoCorrecaoActionPerformed(evt);
            }
        });
        jPanel1.add(btnLocalizarVersaoCorrecao, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 70, 30, 25));

        jLabel8.setText("Correção:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 80, -1, -1));

        tfdFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfdFiltroKeyReleased(evt);
            }
        });
        jPanel1.add(tfdFiltro, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 480, -1));

        btnLocalizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Lupa3.png"))); // NOI18N
        btnLocalizar.setText("Buscar");
        btnLocalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocalizarActionPerformed(evt);
            }
        });
        jPanel1.add(btnLocalizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 130, 110, -1));

        jLabel13.setText("Título/Desc.:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        tblListaTarefas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "CLIENTE", "TÍTULO", "AUTOR", "RESPONSÁVEL", "FASE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblListaTarefas.setFocusable(false);
        tblListaTarefas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListaTarefasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblListaTarefas);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 904, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 3, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnTeste.setText("jButton1");
        btnTeste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTesteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnTeste)
                        .addGap(224, 224, 224)
                        .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConfirmar)
                    .addComponent(btnSair)
                    .addComponent(btnTeste))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        int idResponsavel = responsavel.getId();
        String nomeResponsavel = responsavel.getNome();
        JdgListaUsuario listaUsuario = new JdgListaUsuario(null, true, responsavel);
        listaUsuario.setVisible(true);
        
        if (responsavel.getId() > 0) {
            tfdNomeResponsavel.setText(responsavel.getNome());

        }else{
            responsavel.setId(idResponsavel);
            responsavel.setNome(nomeResponsavel);
        }
    }//GEN-LAST:event_btnLocalizarResponsavelActionPerformed

    private void btnLocalizarProjetoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocalizarProjetoActionPerformed
projeto.setId(0);
projeto.setDescricao("");
        JdgListaProjeto listaProjeto = new JdgListaProjeto(null, true, projeto);
        listaProjeto.setVisible(true);
        if (projeto.getId() > 0 && projeto.getDescricao().length()>0) {
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
        
        int idCliente = cliente.getId();
        String nomeCliente = cliente.getRazaoSocial();
        //int idCidade = cliente.getCidade().getId();
        cid = new Cidade();
       // cliente = new Cliente();
        cliente.setCidade(cid);
        
        JdgListaCliente clientes = new JdgListaCliente(null, true, cliente, cid);
        clientes.setVisible(true);
        if (cliente.getId() > 0 && cliente.getRazaoSocial().length()>0) {
            tfdNomeCliente.setText(cliente.getRazaoSocial());

        }else{
            cliente.setId(idCliente);
           // cid.setId(idCidade);
            cliente.setRazaoSocial(nomeCliente);
            cliente.setCidade(cid);
            tfdNomeCliente.setText(cliente.getRazaoSocial());
        }
    }//GEN-LAST:event_btnLocalizarClienteActionPerformed

    private void btnLocalizarVersaoBugActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocalizarVersaoBugActionPerformed
        projeto = new Projeto();
        versaoBug.setProjeto(projeto);
        versaoBug.setId(0);
        versaoBug.setDescricao("");
        JdgListaVersao listaVersao = new JdgListaVersao(null, true, versaoBug, projeto);
        listaVersao.setVisible(true);
        if (versaoBug.getId() > 0 && versaoBug.getDescricao().length() > 0) {

            tfdVersaoBug.setText(versaoBug.getDescricao());

        }
    }//GEN-LAST:event_btnLocalizarVersaoBugActionPerformed

    private void btnLocalizarVersaoCorrecaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocalizarVersaoCorrecaoActionPerformed
        versaoCorrecao.setId(0);
        versaoCorrecao.setDescricao("");

        JdgListaVersao listaVersao = new JdgListaVersao(null, true, versaoCorrecao, projeto);
        listaVersao.setVisible(true);
        if (versaoCorrecao.getId() > 0 && versaoCorrecao.getDescricao().length()>0) {

            tfdVersaoCorrecao.setText(versaoCorrecao.getDescricao());

        }
    }//GEN-LAST:event_btnLocalizarVersaoCorrecaoActionPerformed

    private void btnLocalizarFaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocalizarFaseActionPerformed
        int idFase = fase.getId();
        String nomeFase = fase.getDescricao();
       // fase  = new Fase();
        JdgListaFase listaFase = new JdgListaFase(null, true, fase);
        listaFase.setVisible(true);
        if (fase.getId() > 0 && fase.getDescricao().length()>0) {
            tfdFase.setText(fase.getDescricao());

        }else{
            fase.setId(idFase);
            fase.setDescricao(nomeFase);
            tfdFase.setText(fase.getDescricao());
        }
      
        
     
        
        
        
        
        
    }//GEN-LAST:event_btnLocalizarFaseActionPerformed

    private void btnLocalizarPrioridadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocalizarPrioridadeActionPerformed
        
        JdgListaPrioridade listaPrioridade = new JdgListaPrioridade(null, true, prioridade);
        listaPrioridade.setVisible(true);
        if (prioridade.getId() > 0 && prioridade.getDescricao().length()>0) {
            tfdNomePrioridade.setText(prioridade.getDescricao());
        }

    }//GEN-LAST:event_btnLocalizarPrioridadeActionPerformed

    private void btnLocalizarAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocalizarAutorActionPerformed

   autor.setId(0);
   autor.setNome("");
        JdgListaUsuario listaUsuario = new JdgListaUsuario(null, true, autor);
        listaUsuario.setVisible(true);
        
        if (autor.getId() > 0 && autor.getNome().length()>0) {
            tfdAutor.setText(autor.getNome());

        }
    }//GEN-LAST:event_btnLocalizarAutorActionPerformed

    private void btnLocalizarModuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocalizarModuloActionPerformed
        modulo.setDescricao("");
        modulo.setId(0);
        modulo.setProjeto(projeto);
        JdgListaModulo listaModulo = new JdgListaModulo(null, true, modulo, projeto);
        listaModulo.setVisible(true);
        if (modulo.getId() > 0 && modulo.getDescricao().length()>0) {
            tfdNomeModulo.setText(modulo.getDescricao());
        }
    }//GEN-LAST:event_btnLocalizarModuloActionPerformed

    private void btnLocalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocalizarActionPerformed
        tarefa.setTitulo(tfdFiltro.getText());
        tarefa.setDescricao(tfdFiltro.getText());
        try {
            int id = Integer.parseInt(tfdFiltroId.getText());
            if (id > 0) {
                //JOptionPane.showMessageDialog(rootPane, "id tarefa"+id);
            tarefa.setId(id);    
       //     JOptionPane.showMessageDialog(rootPane, "id tarefa"+tarefa.getId());
            }
            
        } catch (Exception e) {
            tarefa.setId(0);
        }
       // tarefa.setFase(fase);
        listarTarefas();
    }//GEN-LAST:event_btnLocalizarActionPerformed

    private void btnTesteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTesteActionPerformed
        try {
            String query = "Select * from tarefa";
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnTesteActionPerformed

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
    private javax.swing.JButton btnLocalizarModulo;
    private javax.swing.JButton btnLocalizarMotivo;
    private javax.swing.JButton btnLocalizarPrioridade;
    private javax.swing.JButton btnLocalizarProjeto;
    private javax.swing.JButton btnLocalizarResponsavel;
    private javax.swing.JButton btnLocalizarVersaoBug;
    private javax.swing.JButton btnLocalizarVersaoCorrecao;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnTeste;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblListaTarefas;
    private javax.swing.JTextField tfdAutor;
    private javax.swing.JTextField tfdFase;
    private javax.swing.JTextField tfdFiltro;
    private javax.swing.JTextField tfdFiltroId;
    private javax.swing.JTextField tfdNomeCliente;
    private javax.swing.JTextField tfdNomeModulo;
    private javax.swing.JTextField tfdNomeMotivo;
    private javax.swing.JTextField tfdNomePrioridade;
    private javax.swing.JTextField tfdNomeProjeto;
    private javax.swing.JTextField tfdNomeResponsavel;
    private javax.swing.JTextField tfdVersaoBug;
    private javax.swing.JTextField tfdVersaoCorrecao;
    // End of variables declaration//GEN-END:variables
}
