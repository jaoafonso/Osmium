/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.CategoriasDAO;
import dao.ConvitesDAO;
import dao.InteressesDAO;
import dao.JogosDAO;
import dao.JogosFavoritosDAO;
import dao.PlataformasDAO;
import dao.PublicacoesDAO;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import modelo.Usuario;
import factory.ConnectionFactory;
import java.awt.Dimension;
import java.awt.Image;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import modelo.Categorias;
import modelo.Convites;
import modelo.Interesses;
import modelo.Jogos;
import modelo.JogosFavoritos;
import modelo.ModelTable;
import modelo.Plataformas;
import modelo.Publicacoes;

/**
 *
 * @author Usuario
 */
public class TelaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form TelaPrincipal
     */
    private Categorias objCategorias;
    private CategoriasDAO categDAO;

    private Jogos objJogos;
    private JogosDAO jogoDAO;

    private Convites objConvites;
    private ConvitesDAO convitesDAO;

    private Publicacoes objPublicacoes;
    private PublicacoesDAO pubDAO;

    public TelaPrincipal() {
        initComponents();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                carregarUsuarioPadrao();
                ImageIcon fotoUsr = new ImageIcon(getClass().getResource("/img/img" + usr.getFoto_usuario() + ".png"));
                jLabel35.setText(usr.getNome_usuario());
                fotoUsr.setImage(fotoUsr.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
                jLabel37.setIcon(fotoUsr);
                jLabel37.setText("");

                carregarConvites(objConvites);
                carregarPublicacoes(objPublicacoes, "deOutros");

                if (usr.isPerfil_concluido() == true) {
                    jPanel50.setVisible(false);
                }

                isPerfilConcluido();
            }
        });

        // Configurações de aparência da tabela dos convites recebidos
        jScrollPane1.getViewport().setBackground(new Color(60, 63, 64));
        JTableHeader header = jTable1.getTableHeader();
        DefaultTableCellRenderer head_render = new DefaultTableCellRenderer();
        header.setPreferredSize(new Dimension(100, 30));
        head_render.setBackground(new Color(122, 105, 190));
        jTable1.getTableHeader().setDefaultRenderer(head_render);
        ((DefaultTableCellRenderer) jTable1.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
        jTable1.setGridColor(new Color(18, 18, 18));
        jTable1.setShowHorizontalLines(true);
        jTable1.setRowSelectionAllowed(false);
        jScrollPane1.setVisible(false);
        jPanel49.setVisible(false);

        // Configurações de aparência da tabela das publicacoes
        jScrollPane2.getViewport().setBackground(new Color(60, 63, 64));
        JTableHeader header2 = jTable2.getTableHeader();
        header2.setPreferredSize(new Dimension(100, 50));
        jTable2.getTableHeader().setDefaultRenderer(head_render);
        ((DefaultTableCellRenderer) jTable2.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.LEFT);
        jTable2.setGridColor(new Color(18, 18, 18));
        jTable2.setShowHorizontalLines(true);
        jTable2.setShowVerticalLines(true);
        jTable2.setRowSelectionAllowed(false);
        
        // Configurações de aparência da tabela de categorias
        jScrollPane4.getViewport().setBackground(new Color(60, 63, 64));
        JTableHeader header4 = jTable4.getTableHeader();
        header4.setPreferredSize(new Dimension(100, 30));
        jTable4.getTableHeader().setDefaultRenderer(head_render);
        jTable4.setGridColor(new Color(18, 18, 18));
        jTable4.setShowHorizontalLines(true);
        jTable4.setRowSelectionAllowed(false);
        
        // Configurações de aparência da tabela de jogos
        jScrollPane5.getViewport().setBackground(new Color(60, 63, 64));
        JTableHeader header5 = jTable5.getTableHeader();
        header5.setPreferredSize(new Dimension(100, 30));
        jTable5.getTableHeader().setDefaultRenderer(head_render);
        jTable5.setGridColor(new Color(18, 18, 18));
        jTable5.setShowHorizontalLines(true);
        jTable5.setRowSelectionAllowed(false);

        carregarCategorias(objCategorias);
        carregarJogos(objJogos, null);

        jPanel8.setVisible(false);
        jPanel14.setVisible(false);
        jPanel15.setVisible(false);
    }
    Connection connection;
    Usuario usr = new Usuario();
    Usuario outroUsr = new Usuario();
    Convites cvt = new Convites();

    public void isPerfilConcluido() {
        ImageIcon xIcon = new ImageIcon(getClass().getResource("/img/marca-x.png"));
        boolean interesses = true;
        boolean jogos_favoritos = true;
        boolean plataformas = true;
        try {
            this.connection = new ConnectionFactory().getConnection();

            String sql = "SELECT * FROM interesses_do_usuario WHERE id_usuario=" + usr.getId_usuario();
            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next() == false) {
                interesses = false;
            }

            String sql2 = "SELECT * FROM jogos_favoritos WHERE id_usuario=" + usr.getId_usuario();
            Statement stmt2 = connection.createStatement();

            ResultSet rs2 = stmt2.executeQuery(sql2);
            if (rs2.next() == false) {
                jogos_favoritos = false;
            }

            String sql3 = "SELECT * FROM plataformas WHERE id_usuario=" + usr.getId_usuario();
            Statement stmt3 = connection.createStatement();

            ResultSet rs3 = stmt3.executeQuery(sql3);
            if (rs3.next() == false) {
                plataformas = false;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (interesses && jogos_favoritos && plataformas) {
            jPanel50.setVisible(false);
        } else {
            if (!interesses) {
                jLabel40.setIcon(xIcon);
            }
            if (!jogos_favoritos) {
                jLabel41.setIcon(xIcon);
            }
            if (!plataformas) {
                jLabel42.setIcon(xIcon);
            }
        }
    }

    public void abrirPublicacao() {
        TelaVerPublicacao frame = new TelaVerPublicacao();
        frame.pub.setId_publicacao(Integer.valueOf(jTable2.getValueAt(jTable2.getSelectedRow(), 3).toString()));
        frame.usr.setId_usuario(usr.getId_usuario());
        frame.usr.setNome_usuario(usr.getNome_usuario());
        frame.setVisible(true);
        this.dispose();
    }

    public void carregarJogos(Jogos objJogos, String nome_categoria) {

        jogoDAO = new JogosDAO();
        ArrayList dados = new ArrayList();
        objJogos = new Jogos();

        if (nome_categoria == null) {
            dados = jogoDAO.listarJogos();
        } else {
            dados = jogoDAO.listarJogosPorCategoria(nome_categoria);
        }
        String[] colunas = new String[]{"Nome do Jogo"};

        ModelTable modelo = new ModelTable(dados, colunas);
        jTable5.setModel(modelo);

    }

    public void carregarJogosPorPesquisa(Jogos objJogos, String pesquisa, String localizacao) {

        jogoDAO = new JogosDAO();
        ArrayList dados = new ArrayList();
        objJogos = new Jogos();

        dados = jogoDAO.listarJogosPorPesquisa(pesquisa);

        String[] colunas = new String[]{"Nome do Jogo"};

        ModelTable modelo = new ModelTable(dados, colunas);

        if (localizacao == "favoritos") {
            jTable5.setModel(modelo);
        }
    }

    public void carregarCategorias(Categorias objCategorias) {

        categDAO = new CategoriasDAO();
        ArrayList dados = new ArrayList();

        objCategorias = new Categorias();
        dados = categDAO.listarCategorias();
        String[] colunas = objCategorias.getColunas();

        ModelTable modelo = new ModelTable(dados, colunas);

        jTable4.setModel(modelo);

    }

    ArrayList<Integer> linhasSelecionadasCateg = new ArrayList<Integer>();
    ArrayList<Integer> linhasSelecionadasJogos = new ArrayList<Integer>();

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

        jLabel50.setText("<html>" + stringCategorias);

    }

    public void selecionarJogos(int linha) {
        if (linhasSelecionadasJogos.contains(linha)) {
            linhasSelecionadasJogos.remove(Integer.valueOf(linha));
        } else if (linhasSelecionadasJogos.size() >= 10) {
            JOptionPane.showMessageDialog(null, "Mínimo 10 jogos");
        } else {
            linhasSelecionadasJogos.add(linha);
        }

        String stringJogos = "";

        for (int i = 0; i < linhasSelecionadasJogos.size(); i++) {
            if (i < (linhasSelecionadasJogos.size()) - 1) {
                stringJogos += String.valueOf(jTable5.getValueAt(linhasSelecionadasJogos.get(i), 0)) + ", ";
            } else {
                stringJogos += String.valueOf(jTable5.getValueAt(linhasSelecionadasJogos.get(i), 0));
            }
        }

        jLabel55.setText("<html>" + stringJogos);
    }

    public void infoConvite(int id_convite) {
        try {
            this.connection = new ConnectionFactory().getConnection();

            String sql = "SELECT * FROM convites WHERE id_convite=" + id_convite;
            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                cvt.setRemetente(rs.getInt("remetente"));
                cvt.setDestinatario(rs.getInt("destinatario"));
                cvt.setId_jogo(rs.getInt("id_jogo"));
            }
            connection.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void escreverMensagem() {
        ConvitesDAO cvtDAO = new ConvitesDAO();
        infoConvite(Integer.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString()));
        TelaEnviarMensagem frame = new TelaEnviarMensagem();
        frame.msg.setId_destinatario(cvt.getRemetente());
        frame.msg.setId_remetente(usr.getId_usuario());
        frame.jogo_convite = cvtDAO.nomeJogo(cvt.getId_jogo());
        frame.usr.setId_usuario(usr.getId_usuario());
        frame.usr.setNome_usuario(usr.getNome_usuario());
        frame.retorno = "Tela Principal";
        frame.setVisible(true);
        this.dispose();
    }

    public void carregarPublicacoes(Publicacoes objPublicacoes, String objetivo) {

        pubDAO = new PublicacoesDAO();
        ArrayList dados = new ArrayList();

        objPublicacoes = new Publicacoes();
        dados = pubDAO.listarPublicacoes(usr.getId_usuario(), objetivo);
        String[] colunas = objPublicacoes.getColunas();

        ModelTable modelo = new ModelTable(dados, colunas);

        jTable2.setModel(modelo);

        jTable2.getColumnModel().getColumn(3).setMinWidth(0);
        jTable2.getColumnModel().getColumn(3).setMaxWidth(0);
        jTable2.getColumnModel().getColumn(3).setWidth(0);
        jTable2.getColumnModel().getColumn(0).setMinWidth(200);
        jTable2.getColumnModel().getColumn(0).setMaxWidth(200);
        jTable2.getColumnModel().getColumn(0).setWidth(200);
        jTable2.getColumnModel().getColumn(1).setMinWidth(200);
        jTable2.getColumnModel().getColumn(1).setMaxWidth(200);
        jTable2.getColumnModel().getColumn(1).setWidth(200);

    }

    public void carregarConvites(Convites objConvites) {

        convitesDAO = new ConvitesDAO();
        ArrayList dados = new ArrayList();

        objConvites = new Convites();
        dados = convitesDAO.listarConvites(usr.getId_usuario());
        String[] colunas = objConvites.getColunas();

        ModelTable modelo = new ModelTable(dados, colunas);

        jTable1.setModel(modelo);

        jTable1.getColumnModel().getColumn(1).setMinWidth(0);
        jTable1.getColumnModel().getColumn(1).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(1).setWidth(0);

    }

    public void carregarOutroUsuario(String nome_usuario) {
        try {
            this.connection = new ConnectionFactory().getConnection();

            String sql = "SELECT * FROM usuario WHERE nome_usuario='" + nome_usuario + "'";
            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                outroUsr.setId_usuario(rs.getInt("id_usuario"));
                outroUsr.setNome_usuario(rs.getString("nome_usuario"));
                outroUsr.setDesc_usuario(rs.getString("desc_usuario"));
                outroUsr.setEmail_usuario(rs.getString("email_usuario"));
                outroUsr.setDataNasc_usuario(rs.getString("dataNasc_usuario"));
                outroUsr.setFoto_usuario(rs.getInt("foto_usuario"));
                outroUsr.setAdministrador(rs.getBoolean("administrador"));
            }
            connection.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void carregarUsuarioPadrao() {
        try {
            this.connection = new ConnectionFactory().getConnection();

            String sql = "SELECT * FROM usuario WHERE nome_usuario='" + usr.getNome_usuario() + "'";
            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                usr.setId_usuario(rs.getInt("id_usuario"));
                usr.setNome_usuario(rs.getString("nome_usuario"));
                usr.setDesc_usuario(rs.getString("desc_usuario"));
                usr.setEmail_usuario(rs.getString("email_usuario"));
                usr.setDataNasc_usuario(rs.getString("dataNasc_usuario"));
                usr.setFoto_usuario(rs.getInt("foto_usuario"));
                usr.setAdministrador(rs.getBoolean("administrador"));
                usr.setPerfil_concluido(rs.getBoolean("perfil_concluido"));

            }
            connection.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
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
        jPanel6 = new javax.swing.JPanel();
        btnExit = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        panelBtnEntrar = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        panelBtnEntrar1 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        panelBtnEntrar2 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        btnExit1 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        btnExit2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        btnExit3 = new javax.swing.JButton();
        btnAtualizar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jPanel47 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jPanel8 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jPanel51 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jPanel52 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jLabel54 = new javax.swing.JLabel();
        btnExit4 = new javax.swing.JButton();
        jLabel55 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel49 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel33 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel31 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jPanel34 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jPanel35 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jPanel36 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jPanel37 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jPanel38 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jPanel39 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jPanel41 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jPanel43 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jPanel40 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jPanel42 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jPanel44 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jPanel48 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jPanel50 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jPanel45 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jPanel46 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Osmium");
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(33, 37, 41));

        jPanel6.setBackground(new java.awt.Color(13, 17, 23));

        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logout.png"))); // NOI18N
        btnExit.setBorderPainted(false);
        btnExit.setContentAreaFilled(false);
        btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExitMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnExitMouseEntered(evt);
            }
        });
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconPequeno.png"))); // NOI18N

        panelBtnEntrar.setOpaque(false);
        panelBtnEntrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBtnEntrarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelBtnEntrarMouseEntered(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Mensagens");

        javax.swing.GroupLayout panelBtnEntrarLayout = new javax.swing.GroupLayout(panelBtnEntrar);
        panelBtnEntrar.setLayout(panelBtnEntrarLayout);
        panelBtnEntrarLayout.setHorizontalGroup(
            panelBtnEntrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBtnEntrarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelBtnEntrarLayout.setVerticalGroup(
            panelBtnEntrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnEntrarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelBtnEntrar1.setOpaque(false);
        panelBtnEntrar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBtnEntrar1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelBtnEntrar1MouseEntered(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("Jogos");

        javax.swing.GroupLayout panelBtnEntrar1Layout = new javax.swing.GroupLayout(panelBtnEntrar1);
        panelBtnEntrar1.setLayout(panelBtnEntrar1Layout);
        panelBtnEntrar1Layout.setHorizontalGroup(
            panelBtnEntrar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBtnEntrar1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelBtnEntrar1Layout.setVerticalGroup(
            panelBtnEntrar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnEntrar1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelBtnEntrar2.setOpaque(false);
        panelBtnEntrar2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBtnEntrar2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelBtnEntrar2MouseEntered(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("Convites");

        javax.swing.GroupLayout panelBtnEntrar2Layout = new javax.swing.GroupLayout(panelBtnEntrar2);
        panelBtnEntrar2.setLayout(panelBtnEntrar2Layout);
        panelBtnEntrar2Layout.setHorizontalGroup(
            panelBtnEntrar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBtnEntrar2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelBtnEntrar2Layout.setVerticalGroup(
            panelBtnEntrar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnEntrar2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnExit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/settings.png"))); // NOI18N
        btnExit1.setBorderPainted(false);
        btnExit1.setContentAreaFilled(false);
        btnExit1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExit1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnExit1MouseEntered(evt);
            }
        });
        btnExit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExit1ActionPerformed(evt);
            }
        });

        jPanel12.setOpaque(false);

        btnExit2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/magnifying-glass.png"))); // NOI18N
        btnExit2.setBorderPainted(false);
        btnExit2.setContentAreaFilled(false);
        btnExit2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExit2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnExit2MouseEntered(evt);
            }
        });
        btnExit2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExit2ActionPerformed(evt);
            }
        });

        jTextField1.setBackground(new java.awt.Color(69, 73, 73));
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setText("  Buscar usuários");
        jTextField1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField1FocusLost(evt);
            }
        });
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnExit2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExit2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        btnExit3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/edit.png"))); // NOI18N
        btnExit3.setBorderPainted(false);
        btnExit3.setContentAreaFilled(false);
        btnExit3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExit3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnExit3MouseEntered(evt);
            }
        });
        btnExit3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExit3ActionPerformed(evt);
            }
        });

        btnAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/reload.png"))); // NOI18N
        btnAtualizar.setBorderPainted(false);
        btnAtualizar.setContentAreaFilled(false);
        btnAtualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAtualizarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAtualizarMouseEntered(evt);
            }
        });
        btnAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel5)
                .addGap(20, 20, 20)
                .addComponent(panelBtnEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(panelBtnEntrar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(panelBtnEntrar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(132, 132, 132)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExit3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExit1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnExit1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExit3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btnExit, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panelBtnEntrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panelBtnEntrar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panelBtnEntrar2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(33, 37, 41));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel14.setBackground(new java.awt.Color(60, 63, 64));
        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(122, 105, 190), 2));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setText("Selecione as plataformas que você joga");
        jPanel14.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 232, -1));

        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText("X");
        jLabel44.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel44MouseClicked(evt);
            }
        });
        jPanel14.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 20, 20));

        jPanel47.setBackground(new java.awt.Color(60, 63, 64));
        jPanel47.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel47.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel47MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel47MouseEntered(evt);
            }
        });

        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setText("Concluir");

        javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
        jPanel47.setLayout(jPanel47Layout);
        jPanel47Layout.setHorizontalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel47Layout.setVerticalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel14.add(jPanel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, -1, -1));

        jCheckBox1.setBackground(new java.awt.Color(60, 63, 64));
        jCheckBox1.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setText("PC");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        jPanel14.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, -1, -1));

        jCheckBox2.setBackground(new java.awt.Color(60, 63, 64));
        jCheckBox2.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox2.setText("Console");
        jPanel14.add(jCheckBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, -1, -1));

        jCheckBox3.setBackground(new java.awt.Color(60, 63, 64));
        jCheckBox3.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox3.setText("Mobile");
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });
        jPanel14.add(jCheckBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, -1, -1));

        jPanel2.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 180, -1, 250));

        jPanel8.setBackground(new java.awt.Color(60, 63, 64));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(122, 105, 190), 2));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setText("Selecione suas categorias de interesse");
        jPanel8.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 310, -1));

        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setText("X");
        jLabel47.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel47MouseClicked(evt);
            }
        });
        jPanel8.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 0, 20, 20));

        jPanel51.setBackground(new java.awt.Color(60, 63, 64));
        jPanel51.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel51.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel51MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel51MouseEntered(evt);
            }
        });

        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel48.setText("Salvar");

        javax.swing.GroupLayout jPanel51Layout = new javax.swing.GroupLayout(jPanel51);
        jPanel51.setLayout(jPanel51Layout);
        jPanel51Layout.setHorizontalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel51Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel51Layout.setVerticalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel51Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel8.add(jPanel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 470, -1, -1));

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
        });
        jScrollPane4.setViewportView(jTable4);

        jPanel8.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 280, 230));

        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setText("Categorias Selecionadas:");
        jPanel8.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, 280, -1));

        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setText("Nenhuma");
        jPanel8.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 380, 280, 80));

        jPanel2.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 30, -1, 530));

        jPanel15.setBackground(new java.awt.Color(60, 63, 64));
        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(122, 105, 190), 2));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel51.setForeground(new java.awt.Color(255, 255, 255));
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel51.setText("Selecione seus jogos favoritos");
        jPanel15.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 320, -1));

        jLabel52.setForeground(new java.awt.Color(255, 255, 255));
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel52.setText("X");
        jLabel52.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel52MouseClicked(evt);
            }
        });
        jPanel15.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 0, 20, 20));

        jPanel52.setBackground(new java.awt.Color(60, 63, 64));
        jPanel52.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel52.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel52MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel52MouseEntered(evt);
            }
        });

        jLabel53.setForeground(new java.awt.Color(255, 255, 255));
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel53.setText("Salvar");

        javax.swing.GroupLayout jPanel52Layout = new javax.swing.GroupLayout(jPanel52);
        jPanel52.setLayout(jPanel52Layout);
        jPanel52Layout.setHorizontalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel52Layout.setVerticalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel15.add(jPanel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 500, -1, -1));

        jScrollPane5.setBorder(null);
        jScrollPane5.setOpaque(false);

        jTable5.setBackground(new java.awt.Color(60, 63, 64));
        jTable5.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jTable5.setForeground(new java.awt.Color(255, 255, 255));
        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Nome do Jogo"
            }
        ));
        jTable5.setFocusable(false);
        jTable5.setOpaque(false);
        jTable5.setRowHeight(26);
        jTable5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable5MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTable5);

        jPanel15.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 280, 210));

        jLabel54.setForeground(new java.awt.Color(255, 255, 255));
        jLabel54.setText("Jogos Selecionados:");
        jPanel15.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, 280, -1));

        btnExit4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/magnifying-glass.png"))); // NOI18N
        btnExit4.setBorderPainted(false);
        btnExit4.setContentAreaFilled(false);
        btnExit4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExit4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnExit4MouseEntered(evt);
            }
        });
        btnExit4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExit4ActionPerformed(evt);
            }
        });
        jPanel15.add(btnExit4, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 70, 30, 30));

        jLabel55.setForeground(new java.awt.Color(255, 255, 255));
        jLabel55.setText("Nenhum");
        jPanel15.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 380, 280, 120));

        jTextField2.setBackground(new java.awt.Color(69, 73, 73));
        jTextField2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(255, 255, 255));
        jTextField2.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jPanel15.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 240, 30));

        jPanel2.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, -1, 570));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(122, 105, 190), 2));
        jScrollPane1.setOpaque(false);
        jScrollPane1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jScrollPane1FocusLost(evt);
            }
        });
        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseExited(evt);
            }
        });

        jTable1.setBackground(new java.awt.Color(60, 63, 64));
        jTable1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jTable1.setForeground(new java.awt.Color(255, 255, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Convites", "ID"
            }
        ));
        jTable1.setFocusable(false);
        jTable1.setOpaque(false);
        jTable1.setRowHeight(26);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTable1MouseExited(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(1).setMinWidth(0);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(0);
            jTable1.getColumnModel().getColumn(1).setMaxWidth(0);
        }

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 400, 200));

        jPanel49.setBackground((new Color(0,0,0,00)));
        jPanel49.setOpaque(false);
        jPanel49.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel49MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel49Layout = new javax.swing.GroupLayout(jPanel49);
        jPanel49.setLayout(jPanel49Layout);
        jPanel49Layout.setHorizontalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 420, Short.MAX_VALUE)
        );
        jPanel49Layout.setVerticalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, -10, 420, 220));

        jPanel3.setBackground(new java.awt.Color(18, 18, 18));

        jPanel5.setPreferredSize(new java.awt.Dimension(140, 100));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setBackground(new java.awt.Color(60, 63, 64));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel9MouseClicked(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(186, 186, 186));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Configurações");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 140, 30));

        jPanel10.setBackground(new java.awt.Color(60, 63, 64));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel10MouseClicked(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(186, 186, 186));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Sair");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 140, 30));

        jPanel11.setBackground(new java.awt.Color(60, 63, 64));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel11MouseClicked(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(186, 186, 186));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Perfil");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 30));

        jPanel17.setPreferredSize(new java.awt.Dimension(140, 160));
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel18.setBackground(new java.awt.Color(60, 63, 64));
        jPanel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel18MouseClicked(evt);
            }
        });

        jLabel9.setForeground(new java.awt.Color(186, 186, 186));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Stumble Guys");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel17.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 140, 30));

        jPanel19.setBackground(new java.awt.Color(122, 105, 190));
        jPanel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel10.setForeground(new java.awt.Color(186, 186, 186));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Jogos Recomendados");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel17.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 30));

        jPanel20.setBackground(new java.awt.Color(60, 63, 64));
        jPanel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel20MouseClicked(evt);
            }
        });

        jLabel8.setForeground(new java.awt.Color(186, 186, 186));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Minecraft");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel17.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 140, 30));

        jPanel21.setBackground(new java.awt.Color(60, 63, 64));
        jPanel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel21MouseClicked(evt);
            }
        });

        jLabel7.setForeground(new java.awt.Color(186, 186, 186));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Forza Horizon 5");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel17.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 140, 30));

        jPanel22.setBackground(new java.awt.Color(60, 63, 64));
        jPanel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel22MouseClicked(evt);
            }
        });

        jLabel6.setForeground(new java.awt.Color(186, 186, 186));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Terraria");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel17.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 140, 30));

        jPanel23.setBackground(new java.awt.Color(60, 63, 64));
        jPanel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel23MouseClicked(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(186, 186, 186));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Hollow Knight");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel17.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 140, 30));

        jPanel33.setBackground(new java.awt.Color(18, 18, 18));

        jLabel18.setForeground(new java.awt.Color(88, 166, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Ver Todos");

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel17.add(jPanel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 140, 30));

        jPanel25.setPreferredSize(new java.awt.Dimension(140, 160));
        jPanel25.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel26.setBackground(new java.awt.Color(60, 63, 64));
        jPanel26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel15.setForeground(new java.awt.Color(186, 186, 186));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("User");

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel25.add(jPanel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 140, 30));

        jPanel27.setBackground(new java.awt.Color(122, 105, 190));
        jPanel27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel16.setForeground(new java.awt.Color(186, 186, 186));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Seguidores Recentes");

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel25.add(jPanel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 30));

        jPanel28.setBackground(new java.awt.Color(60, 63, 64));
        jPanel28.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel14.setForeground(new java.awt.Color(186, 186, 186));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("User");

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel25.add(jPanel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 140, 30));

        jPanel29.setBackground(new java.awt.Color(60, 63, 64));
        jPanel29.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel13.setForeground(new java.awt.Color(186, 186, 186));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("User");

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel25.add(jPanel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 140, 30));

        jPanel30.setBackground(new java.awt.Color(60, 63, 64));
        jPanel30.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel12.setForeground(new java.awt.Color(186, 186, 186));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("User");

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel25.add(jPanel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 140, 30));

        jPanel31.setBackground(new java.awt.Color(60, 63, 64));
        jPanel31.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel11.setForeground(new java.awt.Color(186, 186, 186));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("User");

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel25.add(jPanel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 140, 30));

        jPanel32.setBackground(new java.awt.Color(18, 18, 18));

        jLabel17.setForeground(new java.awt.Color(88, 166, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Ver Todos");

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel25.add(jPanel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 140, 30));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 642));

        jPanel4.setBackground(new java.awt.Color(18, 18, 18));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel24.setPreferredSize(new java.awt.Dimension(140, 160));
        jPanel24.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel34.setBackground(new java.awt.Color(60, 63, 64));
        jPanel34.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel19.setForeground(new java.awt.Color(186, 186, 186));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("User");

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel24.add(jPanel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 140, 30));

        jPanel35.setBackground(new java.awt.Color(122, 105, 190));
        jPanel35.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel20.setForeground(new java.awt.Color(186, 186, 186));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Faça novas Amizades!");

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel24.add(jPanel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 30));

        jPanel36.setBackground(new java.awt.Color(60, 63, 64));
        jPanel36.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel21.setForeground(new java.awt.Color(186, 186, 186));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("User");

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel24.add(jPanel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 140, 30));

        jPanel37.setBackground(new java.awt.Color(60, 63, 64));
        jPanel37.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel22.setForeground(new java.awt.Color(186, 186, 186));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("User");

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel24.add(jPanel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 140, 30));

        jPanel38.setBackground(new java.awt.Color(60, 63, 64));
        jPanel38.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel23.setForeground(new java.awt.Color(186, 186, 186));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("User");

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel24.add(jPanel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 140, 30));

        jPanel39.setBackground(new java.awt.Color(60, 63, 64));
        jPanel39.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel24.setForeground(new java.awt.Color(186, 186, 186));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("User");

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel24.add(jPanel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 140, 30));

        jPanel41.setBackground(new java.awt.Color(60, 63, 64));
        jPanel41.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel26.setForeground(new java.awt.Color(186, 186, 186));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("User");

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel24.add(jPanel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 140, 30));

        jPanel43.setBackground(new java.awt.Color(60, 63, 64));
        jPanel43.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel28.setForeground(new java.awt.Color(186, 186, 186));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("User");

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel24.add(jPanel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 140, 30));

        jPanel40.setBackground(new java.awt.Color(60, 63, 64));
        jPanel40.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel25.setForeground(new java.awt.Color(186, 186, 186));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("User");

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel24.add(jPanel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 140, 30));

        jPanel42.setBackground(new java.awt.Color(60, 63, 64));
        jPanel42.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel27.setForeground(new java.awt.Color(186, 186, 186));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("User");

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel24.add(jPanel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 140, 30));

        jPanel44.setBackground(new java.awt.Color(18, 18, 18));

        jLabel29.setForeground(new java.awt.Color(88, 166, 255));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Ver Todos");

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel24.add(jPanel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 140, 30));

        jPanel4.add(jPanel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 30, -1, 330));

        jPanel48.setBackground(new java.awt.Color(60, 63, 64));
        jPanel48.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel48MouseClicked(evt);
            }
        });

        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Username");

        jLabel37.setText("Foto");

        javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
        jPanel48.setLayout(jPanel48Layout);
        jPanel48Layout.setHorizontalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel48Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel48Layout.setVerticalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel48Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel4.add(jPanel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 548, -1, -1));

        jPanel50.setBackground(new java.awt.Color(60, 63, 64));

        jLabel36.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("Conclua seu Perfil!");

        jPanel16.setBackground(new java.awt.Color(60, 63, 64));
        jPanel16.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel16MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel16MouseEntered(evt);
            }
        });

        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("Interesses");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel45.setBackground(new java.awt.Color(60, 63, 64));
        jPanel45.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel45.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel45MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel45MouseEntered(evt);
            }
        });

        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("Jogos Favoritos");

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel46.setBackground(new java.awt.Color(60, 63, 64));
        jPanel46.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel46.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel46MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel46MouseEntered(evt);
            }
        });

        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("Plataformas");

        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
        jPanel46.setLayout(jPanel46Layout);
        jPanel46Layout.setHorizontalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel46Layout.setVerticalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/direito.png"))); // NOI18N

        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/direito.png"))); // NOI18N

        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/direito.png"))); // NOI18N

        javax.swing.GroupLayout jPanel50Layout = new javax.swing.GroupLayout(jPanel50);
        jPanel50.setLayout(jPanel50Layout);
        jPanel50Layout.setHorizontalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel50Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel50Layout.createSequentialGroup()
                        .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel50Layout.createSequentialGroup()
                        .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel40)
                            .addComponent(jLabel41)
                            .addComponent(jLabel42))
                        .addGap(8, 8, 8))))
        );
        jPanel50Layout.setVerticalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel50Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 378, 140, -1));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1064, 0, 204, 642));

        jPanel7.setBackground(new java.awt.Color(18, 18, 18));

        jScrollPane2.setBorder(null);
        jScrollPane2.setOpaque(false);

        jTable2.setBackground(new java.awt.Color(60, 63, 64));
        jTable2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jTable2.setForeground(new java.awt.Color(255, 255, 255));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Assunto", "Usuário", "Título", "ID"
            }
        ));
        jTable2.setFocusable(false);
        jTable2.setOpaque(false);
        jTable2.setRowHeight(50);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(3).setMinWidth(0);
            jTable2.getColumnModel().getColumn(3).setPreferredWidth(0);
            jTable2.getColumnModel().getColumn(3).setMaxWidth(0);
        }

        jPanel13.setBackground(new java.awt.Color(60, 63, 64));
        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel13MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel13MouseEntered(evt);
            }
        });

        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("Suas Publicações");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 840, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(352, 352, 352)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(206, 0, -1, 642));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseClicked
        // TODO add your handling code here:
        Color temaDark = new Color(18, 18, 18);
        UIManager.put("control", temaDark);
        UIManager.put("OptionPane.background", temaDark);
        UIManager.put("OptionPane.messageForeground", Color.white);
        int resposta = JOptionPane.showOptionDialog(new JFrame(), "Deseja realmente sair do sistema?", "Sair",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                new Object[]{"Não", "Sim"}, JOptionPane.YES_OPTION);
        if (resposta == JOptionPane.NO_OPTION) { //Inverti a opção para facilitar, para o Netbeans focar no "Não", caso o usuario clique sem querer em sair
            System.exit(0);
        }
    }//GEN-LAST:event_btnExitMouseClicked

    private void btnExitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseEntered
        // TODO add your handling code here:
        btnExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnExitMouseEntered

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExitActionPerformed

    private void panelBtnEntrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnEntrarMouseClicked
        // TODO add your handling code here:
        TelaMensagens frame = new TelaMensagens();
        frame.usr.setNome_usuario(usr.getNome_usuario());
        frame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_panelBtnEntrarMouseClicked

    private void panelBtnEntrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnEntrarMouseEntered
        // TODO add your handling code here:
        panelBtnEntrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_panelBtnEntrarMouseEntered

    private void panelBtnEntrar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnEntrar1MouseClicked
        // TODO add your handling code here:
        TelaJogos frame = new TelaJogos();
        frame.usr.setNome_usuario(usr.getNome_usuario());
        frame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_panelBtnEntrar1MouseClicked

    private void panelBtnEntrar1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnEntrar1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_panelBtnEntrar1MouseEntered

    private void panelBtnEntrar2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnEntrar2MouseClicked
        // TODO add your handling code here:

        if (jPanel49.isVisible()) {
            jScrollPane1.setVisible(false);
            jPanel49.setVisible(false);
        } else {
            jScrollPane1.setVisible(true);
            jPanel49.setVisible(true);
        }
    }//GEN-LAST:event_panelBtnEntrar2MouseClicked

    private void panelBtnEntrar2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnEntrar2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_panelBtnEntrar2MouseEntered

    private void btnExit1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExit1MouseClicked
        // TODO add your handling code here:
        TelaConfig frame = new TelaConfig();
        frame.usr.setNome_usuario(usr.getNome_usuario());
        frame.usr.setId_usuario(usr.getId_usuario());
        frame.usr.setFoto_usuario(usr.getFoto_usuario());
        frame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnExit1MouseClicked

    private void btnExit1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExit1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExit1MouseEntered

    private void btnExit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExit1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExit1ActionPerformed

    private void btnExit2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExit2MouseClicked
        // TODO add your handling code here:
        if (jTextField1.getText().equals(usr.getNome_usuario())) {
            TelaPerfil frame = new TelaPerfil();
            frame.usr.setNome_usuario(usr.getNome_usuario());
            frame.setVisible(true);
            this.dispose();
        } else {
            TelaPerfil frame = new TelaPerfil();
            frame.usr.setNome_usuario(usr.getNome_usuario());
            frame.usr.setId_usuario(usr.getId_usuario());
            frame.outroUsr.setNome_usuario(jTextField1.getText());
            frame.setVisible(true);
            this.dispose();
        }

    }//GEN-LAST:event_btnExit2MouseClicked

    private void btnExit2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExit2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExit2MouseEntered

    private void btnExit2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExit2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExit2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jPanel48MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel48MouseClicked
        // TODO add your handling code here:
        TelaPerfil frame = new TelaPerfil();
        frame.usr.setNome_usuario(usr.getNome_usuario());
        frame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jPanel48MouseClicked

    private void jTextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusGained
        // TODO add your handling code here:
        jTextField1.setText("");
    }//GEN-LAST:event_jTextField1FocusGained

    private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1FocusLost

    private void jPanel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseClicked
        // TODO add your handling code here:
        TelaConfig frame = new TelaConfig();
        frame.usr.setNome_usuario(usr.getNome_usuario());
        frame.usr.setFoto_usuario(usr.getFoto_usuario());
        frame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jPanel9MouseClicked

    private void jPanel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseClicked
        // TODO add your handling code here:
        Color temaDark = new Color(18, 18, 18);
        UIManager.put("control", temaDark);
        UIManager.put("OptionPane.background", temaDark);
        UIManager.put("OptionPane.messageForeground", Color.white);
        int resposta = JOptionPane.showOptionDialog(new JFrame(), "Deseja realmente sair do sistema?", "Sair",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                new Object[]{"Não", "Sim"}, JOptionPane.YES_OPTION);
        if (resposta == JOptionPane.NO_OPTION) { //Inverti a opção para facilitar, para o Netbeans focar no "Não", caso o usuario clique sem querer em sair
            System.exit(0);
        }
    }//GEN-LAST:event_jPanel10MouseClicked

    private void jPanel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel11MouseClicked
        // TODO add your handling code here:
        TelaPerfil frame = new TelaPerfil();
        frame.usr.setNome_usuario(usr.getNome_usuario());
        frame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jPanel11MouseClicked

    private void jPanel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel18MouseClicked
        // TODO add your handling code here:\
        TelaInfoJogo frame = new TelaInfoJogo();
        frame.usr.setNome_usuario(usr.getNome_usuario());
        frame.jg.setNome_jogo("Stumble Guys");
        frame.retorno = "Tela Principal";
        frame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jPanel18MouseClicked

    private void jPanel22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel22MouseClicked
        // TODO add your handling code here:
        TelaInfoJogo frame = new TelaInfoJogo();
        frame.usr.setNome_usuario(usr.getNome_usuario());
        frame.jg.setNome_jogo("Terraria");
        frame.retorno = "Tela Principal";
        frame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jPanel22MouseClicked

    private void jPanel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel21MouseClicked
        // TODO add your handling code here:
        TelaInfoJogo frame = new TelaInfoJogo();
        frame.usr.setNome_usuario(usr.getNome_usuario());
        frame.jg.setNome_jogo("Forza Horizon 5");
        frame.retorno = "Tela Principal";
        frame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jPanel21MouseClicked

    private void jPanel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel20MouseClicked
        // TODO add your handling code here:
        TelaInfoJogo frame = new TelaInfoJogo();
        frame.usr.setNome_usuario(usr.getNome_usuario());
        frame.jg.setNome_jogo("Minecraft");
        frame.retorno = "Tela Principal";
        frame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jPanel20MouseClicked

    private void jPanel23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel23MouseClicked
        // TODO add your handling code here:
        TelaInfoJogo frame = new TelaInfoJogo();
        frame.usr.setNome_usuario(usr.getNome_usuario());
        frame.jg.setNome_jogo("Hollow Knight");
        frame.retorno = "Tela Principal";
        frame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jPanel23MouseClicked

    private void jTable1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseExited

    private void jPanel49MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel49MouseExited
        // TODO add your handling code here:
        jPanel49.setVisible(false);
        jScrollPane1.setVisible(false);
    }//GEN-LAST:event_jPanel49MouseExited

    private void jScrollPane1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane1MouseExited

    private void jScrollPane1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jScrollPane1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane1FocusLost

    private void btnExit3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExit3MouseClicked
        // TODO add your handling code here:
        TelaNovaPublicacao frame = new TelaNovaPublicacao();
        frame.usr.setNome_usuario(usr.getNome_usuario());
        frame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnExit3MouseClicked

    private void btnExit3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExit3MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExit3MouseEntered

    private void btnExit3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExit3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExit3ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        escreverMensagem();
    }//GEN-LAST:event_jTable1MouseClicked

    private void jPanel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel13MouseClicked
        // TODO add your handling code here:
        if (jLabel31.getText() == "Suas Publicações") {
            jLabel31.setText("Publicações de Outros");
            carregarPublicacoes(objPublicacoes, "doUsuario");

        } else if (jLabel31.getText() == "Publicações de Outros") {
            jLabel31.setText("Suas Publicações");
            carregarPublicacoes(objPublicacoes, "deOutros");
        }
    }//GEN-LAST:event_jPanel13MouseClicked

    private void jPanel13MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel13MouseEntered
        // TODO add your handling code here:
        jPanel11.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jPanel13MouseEntered

    private void btnAtualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAtualizarMouseClicked
        // TODO add your handling code here:
        TelaPrincipal frame = new TelaPrincipal();
        frame.usr.setNome_usuario(usr.getNome_usuario());
        frame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAtualizarMouseClicked

    private void btnAtualizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAtualizarMouseEntered
        // TODO add your handling code here:
        btnAtualizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnAtualizarMouseEntered

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAtualizarActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        abrirPublicacao();
    }//GEN-LAST:event_jTable2MouseClicked

    private void jPanel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel16MouseClicked
        // TODO add your handling code here:
        jPanel8.setVisible(true);
        jPanel14.setVisible(false);
        jPanel15.setVisible(false);
        jPanel7.setVisible(false);
    }//GEN-LAST:event_jPanel16MouseClicked

    private void jPanel16MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel16MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel16MouseEntered

    private void jPanel45MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel45MouseClicked
        // TODO add your handling code here:
        jPanel15.setVisible(true);
        jPanel8.setVisible(false);
        jPanel14.setVisible(false);
        jPanel7.setVisible(false);
    }//GEN-LAST:event_jPanel45MouseClicked

    private void jPanel45MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel45MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel45MouseEntered

    private void jPanel46MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel46MouseClicked
        // TODO add your handling code here:
        jPanel14.setVisible(true);
        jPanel8.setVisible(false);
        jPanel15.setVisible(false);
        jPanel7.setVisible(false);
    }//GEN-LAST:event_jPanel46MouseClicked

    private void jPanel46MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel46MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel46MouseEntered

    private void jLabel44MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel44MouseClicked
        // TODO add your handling code here:
        jPanel14.setVisible(false);
        jPanel7.setVisible(true);
    }//GEN-LAST:event_jLabel44MouseClicked

    private void jPanel47MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel47MouseClicked
        // TODO add your handling code here:
        Plataformas pla = new Plataformas();

        pla.setPc(jCheckBox1.isSelected());
        pla.setConsole(jCheckBox2.isSelected());
        pla.setMobile(jCheckBox3.isSelected());
        pla.setId_usuario(usr.getId_usuario());

        PlataformasDAO plaDAO = new PlataformasDAO();

        plaDAO.limparPlataformas(pla.getId_usuario());
        plaDAO.cadastrarPlataformas(pla);

        Color temaDark = new Color(18, 18, 18);
        UIManager.put("control", temaDark);
        UIManager.put("OptionPane.background", temaDark);
        UIManager.put("OptionPane.messageForeground", Color.white);
        JOptionPane.showMessageDialog(null, "Concluido!");
        TelaPrincipal frame = new TelaPrincipal();
        frame.usr.setNome_usuario(usr.getNome_usuario());
        frame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jPanel47MouseClicked

    private void jPanel47MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel47MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel47MouseEntered

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox3ActionPerformed

    private void jLabel47MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel47MouseClicked
        // TODO add your handling code here:
        jPanel8.setVisible(false);
        jPanel7.setVisible(true);
    }//GEN-LAST:event_jLabel47MouseClicked

    private void jPanel51MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel51MouseClicked
        // TODO add your handling code here:

        Interesses in = new Interesses();
        InteressesDAO inDAO = new InteressesDAO();

        in.setId_usuario(usr.getId_usuario());

        inDAO.limparInteresses(in.getId_usuario());

        for (int i = 0; i < linhasSelecionadasCateg.size(); i++) {

            in.setId_categoria(inDAO.pegarIdCategoria(String.valueOf(jTable4.getValueAt(linhasSelecionadasCateg.get(i), 0))));
            inDAO.favoritarCategoria(in);
        }

        Color temaDark = new Color(18, 18, 18);
        UIManager.put("control", temaDark);
        UIManager.put("OptionPane.background", temaDark);
        UIManager.put("OptionPane.messageForeground", Color.white);
        JOptionPane.showMessageDialog(null, "Concluido!");
        TelaPrincipal frame = new TelaPrincipal();
        frame.usr.setNome_usuario(usr.getNome_usuario());
        frame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jPanel51MouseClicked

    private void jPanel51MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel51MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel51MouseEntered

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
        // TODO add your handling code here

        selecionarCategoria(jTable4.getSelectedRow());
        /* esse metodo ira adicionar o index da linha à um array, sempre que um numero for
        repetido, ou seja, um usuario clicar naquela linha novamente, o metodo devera desamrcar
        aquela linha e remover aquele numero do array. no final ele converterá os indexs
        salvos no array para o nome da categoria e ira adiciona-las na tabela de interesses */
        // usar o jTable4.getValueAt([numero da linha], 0) para pegar o nome da categoria
    }//GEN-LAST:event_jTable4MouseClicked

    private void jLabel52MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel52MouseClicked
        // TODO add your handling code here:
        jPanel15.setVisible(false);
        jPanel7.setVisible(true);
    }//GEN-LAST:event_jLabel52MouseClicked

    private void jPanel52MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel52MouseClicked
        // TODO add your handling code here:
        JogosFavoritos jf = new JogosFavoritos();
        JogosFavoritosDAO jfDAO = new JogosFavoritosDAO();

        jf.setId_usuario(usr.getId_usuario());

        jfDAO.limparJogosFavoritos(jf.getId_usuario());

        for (int i = 0; i < linhasSelecionadasJogos.size(); i++) {

            jf.setId_jogo(jfDAO.pegarIdJogo(String.valueOf(jTable5.getValueAt(linhasSelecionadasJogos.get(i), 0))));
            jfDAO.favoritarJogo(jf);
        }

        Color temaDark = new Color(18, 18, 18);
        UIManager.put("control", temaDark);
        UIManager.put("OptionPane.background", temaDark);
        UIManager.put("OptionPane.messageForeground", Color.white);
        JOptionPane.showMessageDialog(null, "Concluido!");
        TelaPrincipal frame = new TelaPrincipal();
        frame.usr.setNome_usuario(usr.getNome_usuario());
        frame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jPanel52MouseClicked

    private void jPanel52MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel52MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel52MouseEntered

    private void jTable5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseClicked
        // TODO add your handling code here:

        selecionarJogos(jTable5.getSelectedRow());
    }//GEN-LAST:event_jTable5MouseClicked

    private void btnExit4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExit4MouseClicked
        // TODO add your handling code here:
        carregarJogosPorPesquisa(objJogos, jTextField1.getText(), "favoritos");
    }//GEN-LAST:event_btnExit4MouseClicked

    private void btnExit4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExit4MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExit4MouseEntered

    private void btnExit4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExit4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExit4ActionPerformed

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
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnExit1;
    private javax.swing.JButton btnExit2;
    private javax.swing.JButton btnExit3;
    private javax.swing.JButton btnExit4;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
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
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JPanel panelBtnEntrar;
    private javax.swing.JPanel panelBtnEntrar1;
    private javax.swing.JPanel panelBtnEntrar2;
    // End of variables declaration//GEN-END:variables
}
