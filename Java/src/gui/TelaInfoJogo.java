package gui;

import dao.CategoriasDAO;
import dao.CategoriasDoJogoDAO;
import dao.JogosDAO;
import dao.UsuarioDAO;
import factory.ConnectionFactory;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import modelo.Categorias;
import modelo.CategoriasDoJogo;
import modelo.Jogos;
import modelo.ModelTable;
import modelo.Usuario;

public class TelaInfoJogo extends javax.swing.JFrame {

    private Categorias objCategorias;
    private Usuario objUsuario;

    public TelaInfoJogo() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/icon.png")));

        // Configurações de aparência para os JOptionPane
        UIManager.put("control", new Color(18, 18, 18));
        UIManager.put("OptionPane.background", new Color(18, 18, 18));
        UIManager.put("OptionPane.messageForeground", Color.white);
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 14));
        UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.PLAIN, 12));

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                carregarJogo();
                carregarCategoriasJogo(objCategorias);
                carregarUsuariosQueJogam(objUsuario);
                jLabel2.setText(jg.getNome_jogo());
                jTextArea1.setText(jg.getDesc_jogo());

                if (usr.isAdministrador() == true) {
                    excluirJogo.setVisible(true);
                    btnEdit4.setVisible(true);
                    btnEdit5.setVisible(true);
                }
            }
        });

        // Configurações de aparência da tabela dos amigos que jogam
        jScrollPane1.getViewport().setBackground(new Color(60, 63, 64));
        JTableHeader header = jTable1.getTableHeader();
        DefaultTableCellRenderer head_render = new DefaultTableCellRenderer();
        header.setPreferredSize(new Dimension(100, 30));
        head_render.setBackground(new Color(122, 105, 190));
        jTable1.getTableHeader().setDefaultRenderer(head_render);
        jTable1.setGridColor(new Color(18, 18, 18));
        jTable1.setShowHorizontalLines(true);
        jTable1.setRowSelectionAllowed(false);

        // Configurações de aparência da tabela das categorias
        jScrollPane2.getViewport().setBackground(new Color(60, 63, 64));
        JTableHeader header2 = jTable2.getTableHeader();
        header2.setPreferredSize(new Dimension(100, 30));
        jTable2.getTableHeader().setDefaultRenderer(head_render);
        jTable2.setGridColor(new Color(18, 18, 18));
        jTable2.setShowHorizontalLines(true);
        jTable2.setRowSelectionAllowed(false);

        // Configurações de aparência da tabela de categorias do jogo
        jScrollPane4.getViewport().setBackground(new Color(60, 63, 64));
        JTableHeader header4 = jTable4.getTableHeader();
        header4.setPreferredSize(new Dimension(100, 30));
        jTable4.getTableHeader().setDefaultRenderer(head_render);
        jTable4.setGridColor(new Color(18, 18, 18));
        jTable4.setShowHorizontalLines(true);
        jTable4.setRowSelectionAllowed(false);

        excluirJogo.setVisible(false);
        btnEdit4.setVisible(false);
        btnEdit5.setVisible(false);
        jPanel7.setVisible(false);
        jPanel10.setVisible(false);
        carregarCategorias(objCategorias);
    }
    Connection connection;
    private JogosDAO jgDAO;
    private CategoriasDoJogoDAO cjDAO;
    private CategoriasDAO categDAO;
    private UsuarioDAO usrDAO;
    Usuario usr = new Usuario();
    Jogos jg = new Jogos();
    String retorno;

    public void carregarCategorias(Categorias objCategorias) {
        categDAO = new CategoriasDAO();
        ArrayList dados = new ArrayList();
        objCategorias = new Categorias();
        dados = categDAO.listarCategorias();
        String[] colunas = objCategorias.getColunas();
        ModelTable modelo = new ModelTable(dados, colunas);
        jTable4.setModel(modelo);
    }

    public void selecionarCategoria(int linha) {
        if (linhasSelecionadasCateg.contains(linha)) {
            linhasSelecionadasCateg.remove(Integer.valueOf(linha));
        } else if (linhasSelecionadasCateg.size() >= 10) {
            JOptionPane.showMessageDialog(null, "Mínimo 10 Categorias");
        } else {
            linhasSelecionadasCateg.add(linha);
        }

        String stringCategorias = "";

        for (int i = 0; i < linhasSelecionadasCateg.size(); i++) {
            if (i < (linhasSelecionadasCateg.size()) - 1) {
                stringCategorias += String.valueOf(jTable4.getValueAt(linhasSelecionadasCateg.get(i), 0)) + ", ";
            } else {
                stringCategorias += String.valueOf(jTable4.getValueAt(linhasSelecionadasCateg.get(i), 0));
            }
        }

        jLabel25.setText("<html>" + stringCategorias);
    }

    public void abrirPerfil() {
        TelaPerfil frame = new TelaPerfil();
        frame.outroUsr.setNome_usuario(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
        frame.usr = usr;
        frame.retorno = "TelaInfoJogo";
        frame.outroRetorno = retorno;
        frame.nome_jogo_retorno = jg.getNome_jogo();
        frame.setVisible(true);
        this.dispose();
    }

    public void carregarUsuariosQueJogam(Usuario objUsuario) {
        usrDAO = new UsuarioDAO();
        ArrayList dados = new ArrayList();
        objUsuario = new Usuario();
        dados = usrDAO.listarUsuariosQueJogam(jg.getId_jogo());
        String[] colunas = new String[]{"Usuários que Jogam"};
        ModelTable modelo = new ModelTable(dados, colunas);
        jTable1.setModel(modelo);
    }

    public void carregarCategoriasJogo(Categorias objCategorias) {
        categDAO = new CategoriasDAO();
        ArrayList dados = new ArrayList();
        objCategorias = new Categorias();
        dados = categDAO.listarCategoriasJogo(jg.getId_jogo());
        String[] colunas = new String[]{"Categorias do Jogo"};
        ModelTable modelo = new ModelTable(dados, colunas);
        jTable2.setModel(modelo);
    }

    public void carregarJogo() {
        try {
            this.connection = new ConnectionFactory().getConnection();

            String sql = "SELECT * FROM jogos WHERE nome_jogo='" + jg.getNome_jogo() + "'";
            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                jg.setId_jogo(rs.getInt("id_jogo"));
                jg.setNome_jogo(rs.getString("nome_jogo"));
                jg.setDesc_jogo(rs.getString("desc_jogo"));
            }
            connection.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel30 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        btnVoltar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel11 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        btnEdit4 = new javax.swing.JButton();
        btnEdit5 = new javax.swing.JButton();
        excluirJogo = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(720, 720));
        setMinimumSize(new java.awt.Dimension(720, 720));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(33, 37, 41));
        jPanel1.setMaximumSize(new java.awt.Dimension(720, 720));
        jPanel1.setMinimumSize(new java.awt.Dimension(720, 720));

        jPanel2.setBackground(new java.awt.Color(18, 18, 18));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel10.setBackground(new java.awt.Color(60, 63, 64));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(122, 105, 190), 2));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Digite sua nova descrição:");
        jPanel10.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 232, -1));

        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("X");
        jLabel26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel26MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel26MouseEntered(evt);
            }
        });
        jPanel10.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 20, 20));

        jPanel20.setBackground(new java.awt.Color(60, 63, 64));
        jPanel20.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel20MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel20MouseEntered(evt);
            }
        });

        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Concluir");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel10.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, -1, -1));

        jScrollPane7.setBackground(new java.awt.Color(0, 0, 0));
        jScrollPane7.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane7.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jTextArea2.setBackground(new java.awt.Color(69, 73, 73));
        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTextArea2.setForeground(new java.awt.Color(255, 255, 255));
        jTextArea2.setLineWrap(true);
        jTextArea2.setRows(5);
        jTextArea2.setWrapStyleWord(true);
        jTextArea2.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jTextArea2.setHighlighter(null);
        jTextArea2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextArea2KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextArea2KeyTyped(evt);
            }
        });
        jScrollPane7.setViewportView(jTextArea2);

        jPanel10.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 60, 230, -1));

        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel10.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, 150, 30));

        jPanel2.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 240, -1, 240));

        jPanel7.setBackground(new java.awt.Color(60, 63, 64));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(122, 105, 190), 2));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Selecione as Categorias do Jogo");
        jPanel7.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 310, -1));

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("X");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel7MouseEntered(evt);
            }
        });
        jPanel7.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 0, 20, 20));

        jPanel17.setBackground(new java.awt.Color(60, 63, 64));
        jPanel17.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel17MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel17MouseEntered(evt);
            }
        });

        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Salvar");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel7.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 470, -1, -1));

        jScrollPane4.setBorder(null);
        jScrollPane4.setOpaque(false);

        jTable4.setBackground(new java.awt.Color(60, 63, 64));
        jTable4.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jTable4.setForeground(new java.awt.Color(255, 255, 255));
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Categorias de Jogos"
            }
        ));
        jTable4.setFocusable(false);
        jTable4.setOpaque(false);
        jTable4.setRowHeight(27);
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTable4MouseEntered(evt);
            }
        });
        jScrollPane4.setViewportView(jTable4);

        jPanel7.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 280, 230));

        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Categorias Selecionadas:");
        jPanel7.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, 280, -1));

        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Nenhuma");
        jPanel7.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 380, 280, 80));

        jPanel2.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, -1, 520));

        btnVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/left-arrow-white.png"))); // NOI18N
        btnVoltar.setBorderPainted(false);
        btnVoltar.setContentAreaFilled(false);
        btnVoltar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVoltarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVoltarMouseEntered(evt);
            }
        });
        jPanel2.add(btnVoltar, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 50, 50));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Informações do Jogo");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(74, 6, 564, 50));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Nome do Jogo");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 88, 590, 50));

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(18, 18, 18));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(255, 255, 255));
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("descrição do jogo");
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setBorder(null);
        jTextArea1.setFocusable(false);
        jTextArea1.setHighlighter(null);
        jPanel2.add(jTextArea1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, 350, 150));

        jPanel11.setBackground(new java.awt.Color(60, 63, 64));
        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel11MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel11MouseEntered(evt);
            }
        });

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Encontrar Parceiro");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 440, -1, -1));

        btnEdit4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editar.png"))); // NOI18N
        btnEdit4.setBorderPainted(false);
        btnEdit4.setContentAreaFilled(false);
        btnEdit4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEdit4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEdit4MouseEntered(evt);
            }
        });
        jPanel2.add(btnEdit4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 570, 50, 30));

        btnEdit5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editar.png"))); // NOI18N
        btnEdit5.setBorderPainted(false);
        btnEdit5.setContentAreaFilled(false);
        btnEdit5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEdit5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEdit5MouseEntered(evt);
            }
        });
        jPanel2.add(btnEdit5, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 220, 50, 30));

        excluirJogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bin2.png"))); // NOI18N
        excluirJogo.setBorderPainted(false);
        excluirJogo.setContentAreaFilled(false);
        excluirJogo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                excluirJogoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                excluirJogoMouseEntered(evt);
            }
        });
        jPanel2.add(excluirJogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 630, 50, 50));

        jPanel3.setOpaque(false);

        jScrollPane1.setBorder(null);
        jScrollPane1.setOpaque(false);

        jTable1.setBackground(new java.awt.Color(60, 63, 64));
        jTable1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jTable1.setForeground(new java.awt.Color(255, 255, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Usuários que Jogam"
            }
        ));
        jTable1.setFocusable(false);
        jTable1.setOpaque(false);
        jTable1.setRowHeight(26);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTable1MouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jScrollPane2.setBorder(null);
        jScrollPane2.setOpaque(false);

        jTable2.setBackground(new java.awt.Color(60, 63, 64));
        jTable2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jTable2.setForeground(new java.awt.Color(255, 255, 255));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Categorias"
            }
        ));
        jTable2.setFocusable(false);
        jTable2.setOpaque(false);
        jTable2.setRowHeight(26);
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 360, 350, 210));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 720));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnVoltarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVoltarMouseClicked
        if (retorno == "Tela Principal") {
            TelaPrincipal frame = new TelaPrincipal();
            frame.usr = usr;
            frame.setVisible(true);
            this.dispose();
        } else if (retorno == "Tela Jogos") {
            TelaJogos frame = new TelaJogos();
            frame.usr = usr;
            frame.setVisible(true);
            this.dispose();
        } else {
            TelaPrincipal frame = new TelaPrincipal();
            frame.usr = usr;
            frame.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnVoltarMouseClicked

    private void btnVoltarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVoltarMouseEntered
        btnVoltar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnVoltarMouseEntered

    private void jPanel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel11MouseClicked
        jgDAO = new JogosDAO();
        String parceiro = jgDAO.encontrarParceiro(jg.getId_jogo());
        if (parceiro == "" || parceiro.equals(usr.getNome_usuario())) {
            JOptionPane.showMessageDialog(null, "Não encontramos nenhum companheiro compatível. :(");
        } else {
            TelaPerfil frame = new TelaPerfil();
            frame.usr = usr;
            frame.outroUsr.setNome_usuario(parceiro);
            frame.retorno = "TelaInfoJogo";
            frame.outroRetorno = retorno;
            frame.nome_jogo_retorno = jg.getNome_jogo();
            frame.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_jPanel11MouseClicked

    private void jPanel11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel11MouseEntered
        jPanel11.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jPanel11MouseEntered

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        abrirPerfil();
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnEdit4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEdit4MouseClicked
        jPanel7.setVisible(true);
        jPanel3.setVisible(false);
    }//GEN-LAST:event_btnEdit4MouseClicked

    private void btnEdit4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEdit4MouseEntered
        btnEdit4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnEdit4MouseEntered

    private void btnEdit5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEdit5MouseClicked
        jPanel10.setVisible(true);
        jPanel3.setVisible(false);
    }//GEN-LAST:event_btnEdit5MouseClicked

    private void btnEdit5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEdit5MouseEntered
        btnEdit5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnEdit5MouseEntered

    private void excluirJogoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_excluirJogoMouseClicked
        int resposta = JOptionPane.showOptionDialog(new JFrame(), "ATENÇÃO! A exclusão desse jogo será permanente, deseja continuar?", "Sair",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                new Object[]{"Não", "Sim"}, JOptionPane.YES_OPTION);
        if (resposta == JOptionPane.NO_OPTION) {
            int confirmacao = JOptionPane.showOptionDialog(new JFrame(), "Tem certeza? Isso não poderá ser desfeito!", "Sair",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                    new Object[]{"Não", "Sim"}, JOptionPane.YES_OPTION);
            if (confirmacao == JOptionPane.NO_OPTION) {
                jgDAO = new JogosDAO();
                JOptionPane.showMessageDialog(null, "Jogo " + jg.getNome_jogo() + " excluído.");
                jgDAO.excluirJogo(jg.getId_jogo());
                if (retorno == "Tela Principal") {
                    TelaPrincipal frame = new TelaPrincipal();
                    frame.usr = usr;
                    frame.setVisible(true);
                    this.dispose();
                } else if (retorno == "Tela Jogos") {
                    TelaJogos frame = new TelaJogos();
                    frame.usr = usr;
                    frame.setVisible(true);
                    this.dispose();
                } else {
                    TelaPrincipal frame = new TelaPrincipal();
                    frame.usr = usr;
                    frame.setVisible(true);
                    this.dispose();
                }
            }
        }
    }//GEN-LAST:event_excluirJogoMouseClicked

    private void excluirJogoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_excluirJogoMouseEntered
        excluirJogo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_excluirJogoMouseEntered

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        jPanel7.setVisible(false);
        jPanel3.setVisible(true);
    }//GEN-LAST:event_jLabel7MouseClicked

    ArrayList<Integer> linhasSelecionadasCateg = new ArrayList<Integer>();
    private void jPanel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel17MouseClicked
        CategoriasDoJogo cj = new CategoriasDoJogo();
        cjDAO = new CategoriasDoJogoDAO();

        cj.setId_jogo(jg.getId_jogo());
        cjDAO.limparCategorias(cj.getId_jogo());

        for (int i = 0; i < linhasSelecionadasCateg.size(); i++) {
            cjDAO = new CategoriasDoJogoDAO();
            cj.setId_categoria(cjDAO.pegarIdCategoria(String.valueOf(jTable4.getValueAt(linhasSelecionadasCateg.get(i), 0))));
            cjDAO.adicionarCategoria(cj);
        }
        
        JOptionPane.showMessageDialog(null, "Concluido!");
        TelaInfoJogo frame = new TelaInfoJogo();
        frame.jg.setNome_jogo(jg.getNome_jogo());
        frame.usr = usr;
        frame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jPanel17MouseClicked

    private void jPanel17MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel17MouseEntered
        jPanel17.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jPanel17MouseEntered

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
        selecionarCategoria(jTable4.getSelectedRow());
    }//GEN-LAST:event_jTable4MouseClicked

    private void jLabel26MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel26MouseClicked
        jPanel10.setVisible(false);
        jPanel3.setVisible(true);
    }//GEN-LAST:event_jLabel26MouseClicked

    private void jPanel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel20MouseClicked
        jgDAO = new JogosDAO();
        jgDAO.alterarDescricao(jg.getId_jogo(), jTextArea2.getText());
        JOptionPane.showMessageDialog(null, "Concluido!");
        TelaInfoJogo frame = new TelaInfoJogo();
        frame.jg.setNome_jogo(jg.getNome_jogo());
        frame.usr = usr;
        frame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jPanel20MouseClicked

    private void jPanel20MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel20MouseEntered
        jPanel20.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jPanel20MouseEntered

    private void jTextArea2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea2KeyTyped
        String s = jTextArea2.getText();
        int l = s.length();
        try {
            if (l >= 300) {
                evt.consume();
                jLabel30.setText("Máximo 300 Caracteres!");
            } else {
                jLabel30.setText("");
            }
        } catch (Exception w) {
        }
    }//GEN-LAST:event_jTextArea2KeyTyped

    private void jTextArea2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jgDAO = new JogosDAO();
            jgDAO.alterarDescricao(jg.getId_jogo(), jTextArea2.getText());
            JOptionPane.showMessageDialog(null, "Concluido!");
            TelaInfoJogo frame = new TelaInfoJogo();
            frame.jg.setNome_jogo(jg.getNome_jogo());
            frame.usr = usr;
            frame.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_jTextArea2KeyPressed

    private void jLabel7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseEntered
        jLabel7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jLabel7MouseEntered

    private void jLabel26MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel26MouseEntered
        jLabel26.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jLabel26MouseEntered

    private void jTable4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseEntered
        jTable4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jTable4MouseEntered

    private void jTable1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseEntered
        jTable1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jTable1MouseEntered

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
            java.util.logging.Logger.getLogger(TelaInfoJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaInfoJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaInfoJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaInfoJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaInfoJogo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEdit4;
    private javax.swing.JButton btnEdit5;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JButton excluirJogo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable4;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables
}