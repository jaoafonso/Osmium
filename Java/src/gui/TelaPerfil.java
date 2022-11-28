package gui;

import dao.SeguidoresDAO;
import dao.UsuarioDAO;
import dao.CategoriasDAO;
import dao.ConvitesDAO;
import dao.InteressesDAO;
import dao.JogosDAO;
import dao.JogosFavoritosDAO;
import dao.PlataformasDAO;
import factory.ConnectionFactory;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import modelo.Seguidores;
import modelo.Categorias;
import modelo.Convites;
import modelo.Interesses;
import modelo.Jogos;
import modelo.JogosFavoritos;
import modelo.ModelTable;
import modelo.Plataformas;
import modelo.Usuario;

public class TelaPerfil extends javax.swing.JFrame {

    private Categorias objCategorias;
    private CategoriasDAO categDAO;

    private Jogos objJogos;
    private JogosDAO jgDAO;

    private Plataformas objPlataformas;
    private PlataformasDAO plaDAO;

    private Interesses objInteresses;
    private InteressesDAO inDAO;

    private JogosFavoritos objJF;
    private JogosFavoritosDAO jfDAO;

    public TelaPerfil() {
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
                if (usr.isAdministrador() == true && !(outroUsr.getNome_usuario() == null || outroUsr.getNome_usuario().equals(usr.getNome_usuario()))) {
                    carregarUsuarioPadrao();
                    carregarOutroUsuario(outroUsr.getNome_usuario());
                    carregarPlataformas(objPlataformas, outroUsr.getId_usuario());
                    carregarInteresses(objInteresses, outroUsr.getId_usuario());
                    carregarJogosFavoritos(objJF, outroUsr.getId_usuario());
                    jLabel1.setText("Perfil de " + outroUsr.getNome_usuario());
                    ImageIcon fotoUsr = new ImageIcon(getClass().getResource("/img/img" + outroUsr.getFoto_usuario() + ".png"));
                    jLabel3.setText(outroUsr.getNome_usuario());
                    jTextArea1.setText(outroUsr.getDesc_usuario());
                    fotoUsr.setImage(fotoUsr.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
                    jLabel2.setIcon(fotoUsr);
                    jLabel2.setText("");
                    jPanel12.setVisible(false);
                    btnEdit1.setVisible(true);
                    btnEdit2.setVisible(true);
                    btnEdit3.setVisible(true);
                    btnEdit4.setVisible(true);
                    excluirUsuario.setVisible(true);
                    if (estaSeguindo() == true) {
                        jLabel12.setText("Seguindo");
                    }
                } else if (outroUsr.getNome_usuario() == null || outroUsr.getNome_usuario().equals(usr.getNome_usuario())) {
                    jLabel1.setText("Seu Perfil");
                    carregarUsuarioPadrao();
                    carregarPlataformas(objPlataformas, usr.getId_usuario());
                    carregarInteresses(objInteresses, usr.getId_usuario());
                    carregarJogosFavoritos(objJF, usr.getId_usuario());
                    ImageIcon fotoUsr = new ImageIcon(getClass().getResource("/img/img" + usr.getFoto_usuario() + ".png"));
                    jLabel3.setText(usr.getNome_usuario());
                    jTextArea1.setText(usr.getDesc_usuario());
                    fotoUsr.setImage(fotoUsr.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
                    jLabel2.setIcon(fotoUsr);
                    jLabel2.setText("");
                    jPanel11.setVisible(false);
                    jPanel14.setVisible(false);
                    jPanel15.setVisible(false);
                } else {
                    carregarUsuarioPadrao();
                    carregarOutroUsuario(outroUsr.getNome_usuario());
                    carregarPlataformas(objPlataformas, outroUsr.getId_usuario());
                    carregarInteresses(objInteresses, outroUsr.getId_usuario());
                    carregarJogosFavoritos(objJF, outroUsr.getId_usuario());
                    jLabel1.setText("Perfil de " + outroUsr.getNome_usuario());
                    ImageIcon fotoUsr = new ImageIcon(getClass().getResource("/img/img" + outroUsr.getFoto_usuario() + ".png"));
                    jLabel3.setText(outroUsr.getNome_usuario());
                    jTextArea1.setText(outroUsr.getDesc_usuario());
                    fotoUsr.setImage(fotoUsr.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
                    jLabel2.setIcon(fotoUsr);
                    jLabel2.setText("");
                    jPanel12.setVisible(false);
                    jPanel13.setVisible(false);
                    if (estaSeguindo() == true) {
                        jLabel12.setText("Seguindo");
                    }
                }
            }
        });

        btnEdit1.setVisible(false);
        btnEdit2.setVisible(false);
        btnEdit3.setVisible(false);
        btnEdit4.setVisible(false);
        excluirUsuario.setVisible(false);
        jPanel13.setVisible(false);
        jPanel7.setVisible(false);
        jPanel5.setVisible(false);
        jPanel8.setVisible(false);
        jPanel9.setVisible(false);
        jPanel10.setVisible(false);

        carregarCategorias(objCategorias);
        carregarJogos(objJogos, null);

        // Configurações de aparência da tabela dos interesses
        jScrollPane1.getViewport().setBackground(new Color(60, 63, 64));
        JTableHeader header = jTable1.getTableHeader();
        DefaultTableCellRenderer head_render = new DefaultTableCellRenderer();
        header.setPreferredSize(new Dimension(100, 30));
        head_render.setBackground(new Color(122, 105, 190));
        jTable1.getTableHeader().setDefaultRenderer(head_render);
        jTable1.setGridColor(new Color(18, 18, 18));
        jTable1.setShowHorizontalLines(true);
        jTable1.setRowSelectionAllowed(false);

        // Configurações de aparência da tabela dos jogos favoritos
        jScrollPane2.getViewport().setBackground(new Color(60, 63, 64));
        JTableHeader header2 = jTable2.getTableHeader();
        header2.setPreferredSize(new Dimension(100, 30));
        jTable2.getTableHeader().setDefaultRenderer(head_render);
        jTable2.setGridColor(new Color(18, 18, 18));
        jTable2.setShowHorizontalLines(true);
        jTable2.setRowSelectionAllowed(false);

        // Configurações de aparência da tabela das plataformas jogadas
        jScrollPane3.getViewport().setBackground(new Color(60, 63, 64));
        JTableHeader header3 = jTable3.getTableHeader();
        header3.setPreferredSize(new Dimension(100, 30));
        jTable3.getTableHeader().setDefaultRenderer(head_render);
        jTable3.setGridColor(new Color(18, 18, 18));
        jTable3.setShowHorizontalLines(true);
        jTable3.setRowSelectionAllowed(false);

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

        // Configurações de aparência da tabela de jogos nos convites
        jScrollPane6.getViewport().setBackground(new Color(60, 63, 64));
        JTableHeader header6 = jTable6.getTableHeader();
        header6.setPreferredSize(new Dimension(100, 30));
        jTable6.getTableHeader().setDefaultRenderer(head_render);
        jTable6.setGridColor(new Color(18, 18, 18));
        jTable6.setShowHorizontalLines(true);
        jTable6.setRowSelectionAllowed(false);

        jPanel5.setVisible(false);
    }

    Connection connection;
    Usuario usr = new Usuario();
    Usuario outroUsr = new Usuario();
    private UsuarioDAO usrDAO;
    Convites cvt = new Convites();
    private ConvitesDAO cvtDAO;
    Seguidores seg = new Seguidores();
    private SeguidoresDAO segDAO;
    String retorno;
    String outroRetorno;
    String nome_jogo_retorno;

    public boolean estaSeguindo() {
        try {
            boolean retorno = false;
            this.connection = new ConnectionFactory().getConnection();

            String sql = "SELECT * FROM seguidores WHERE id_usuario='" + outroUsr.getId_usuario() + "' AND id_seguidor='" + usr.getId_usuario() + "'";
            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                retorno = true;
            }
            connection.close();
            return retorno;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void carregarJogosFavoritos(JogosFavoritos objJF, int id_usuario) {
        jfDAO = new JogosFavoritosDAO();
        ArrayList dados = new ArrayList();
        objJF = new JogosFavoritos();
        dados = jfDAO.listarJogosFavoritos(id_usuario);
        String[] colunas = new String[]{"Jogos Favoritos"};
        ModelTable modelo = new ModelTable(dados, colunas);
        jTable2.setModel(modelo);
    }

    public void carregarInteresses(Interesses objInteresses, int id_usuario) {
        inDAO = new InteressesDAO();
        ArrayList dados = new ArrayList();
        objInteresses = new Interesses();
        dados = inDAO.listarInteresses(id_usuario);
        String[] colunas = new String[]{"Interesses"};
        ModelTable modelo = new ModelTable(dados, colunas);
        jTable1.setModel(modelo);
    }

    public void carregarPlataformas(Plataformas objPlataformas, int id_usuario) {
        plaDAO = new PlataformasDAO();
        ArrayList dados = new ArrayList();
        objPlataformas = new Plataformas();
        dados = plaDAO.listarPlataformas(id_usuario);
        String[] colunas = new String[]{"Plataformas Jogadas"};
        ModelTable modelo = new ModelTable(dados, colunas);
        jTable3.setModel(modelo);
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

        jLabel25.setText("<html>" + stringCategorias);
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

        jLabel23.setText("<html>" + stringJogos);
    }

    public void carregarJogos(Jogos objJogos, String nome_categoria) {
        jgDAO = new JogosDAO();
        ArrayList dados = new ArrayList();
        objJogos = new Jogos();
        if (nome_categoria == null) {
            dados = jgDAO.listarJogos();
        } else {
            dados = jgDAO.listarJogosPorCategoria(nome_categoria);
        }

        String[] colunas = new String[]{"Nome do Jogo"};
        ModelTable modelo = new ModelTable(dados, colunas);
        jTable5.setModel(modelo);
        jTable6.setModel(modelo);

    }

    public void carregarJogosPorPesquisa(Jogos objJogos, String pesquisa, String localizacao) {
        jgDAO = new JogosDAO();
        ArrayList dados = new ArrayList();
        objJogos = new Jogos();
        dados = jgDAO.listarJogosPorPesquisa(pesquisa);
        String[] colunas = new String[]{"Nome do Jogo"};
        ModelTable modelo = new ModelTable(dados, colunas);

        if (localizacao == "convite") {
            jTable6.setModel(modelo);
        } else if (localizacao == "favoritos") {
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
        jPanel8 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jLabel22 = new javax.swing.JLabel();
        btnExit3 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        btnExit2 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jPanel10 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel30 = new javax.swing.JLabel();
        excluirUsuario = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel14 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnEdit3 = new javax.swing.JButton();
        btnEdit1 = new javax.swing.JButton();
        btnEdit2 = new javax.swing.JButton();
        btnEdit4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Perfil");
        setMinimumSize(new java.awt.Dimension(720, 720));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(33, 37, 41));
        jPanel1.setMaximumSize(new java.awt.Dimension(720, 720));
        jPanel1.setMinimumSize(new java.awt.Dimension(720, 720));
        jPanel1.setPreferredSize(new java.awt.Dimension(720, 720));

        jPanel2.setBackground(new java.awt.Color(18, 18, 18));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setBackground(new java.awt.Color(60, 63, 64));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(122, 105, 190), 2));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Selecione seus jogos favoritos");
        jPanel8.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 320, -1));

        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("X");
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel16MouseEntered(evt);
            }
        });
        jPanel8.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 0, 20, 20));

        jPanel18.setBackground(new java.awt.Color(60, 63, 64));
        jPanel18.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel18MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel18MouseEntered(evt);
            }
        });

        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Salvar");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel8.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 500, -1, -1));

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
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTable5MouseEntered(evt);
            }
        });
        jScrollPane5.setViewportView(jTable5);

        jPanel8.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 280, 210));

        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Jogos Selecionados:");
        jPanel8.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, 280, -1));

        btnExit3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/magnifying-glass.png"))); // NOI18N
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
        jPanel8.add(btnExit3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 70, 30, 30));

        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Nenhum");
        jPanel8.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 380, 280, 120));

        jTextField1.setBackground(new java.awt.Color(69, 73, 73));
        jTextField1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });
        jPanel8.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 240, 30));

        jPanel2.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, -1, 560));

        jPanel5.setBackground(new java.awt.Color(60, 63, 64));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(122, 105, 190), 2));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Selecione um jogo para o convite");
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 320, -1));

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

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Convidar");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 430, -1, -1));

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("X");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 0, 20, 20));

        jScrollPane6.setBorder(null);
        jScrollPane6.setOpaque(false);

        jTable6.setBackground(new java.awt.Color(60, 63, 64));
        jTable6.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jTable6.setForeground(new java.awt.Color(255, 255, 255));
        jTable6.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable6.setFocusable(false);
        jTable6.setOpaque(false);
        jTable6.setRowHeight(26);
        jTable6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTable6MouseEntered(evt);
            }
        });
        jScrollPane6.setViewportView(jTable6);

        jPanel5.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 280, 210));

        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Jogo Selecionado:");
        jPanel5.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, 280, -1));

        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Nenhum");
        jPanel5.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 380, 280, 30));

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
        jPanel5.add(btnExit2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 70, 30, 30));

        jTextField2.setBackground(new java.awt.Color(69, 73, 73));
        jTextField2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(255, 255, 255));
        jTextField2.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
        });
        jPanel5.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 240, 30));

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, -1, 490));

        jPanel7.setBackground(new java.awt.Color(60, 63, 64));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(122, 105, 190), 2));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Selecione suas categorias de interesse");
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

        jPanel9.setBackground(new java.awt.Color(60, 63, 64));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(122, 105, 190), 2));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Selecione as plataformas que você joga");
        jPanel9.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 232, -1));

        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("X");
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel20MouseEntered(evt);
            }
        });
        jPanel9.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 20, 20));

        jPanel19.setBackground(new java.awt.Color(60, 63, 64));
        jPanel19.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel19MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel19MouseEntered(evt);
            }
        });

        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Concluir");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel9.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, -1, -1));

        jCheckBox1.setBackground(new java.awt.Color(60, 63, 64));
        jCheckBox1.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setText("PC");
        jCheckBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jCheckBox1MouseEntered(evt);
            }
        });
        jPanel9.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, -1, -1));

        jCheckBox2.setBackground(new java.awt.Color(60, 63, 64));
        jCheckBox2.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox2.setText("Console");
        jCheckBox2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jCheckBox2MouseEntered(evt);
            }
        });
        jPanel9.add(jCheckBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, -1, -1));

        jCheckBox3.setBackground(new java.awt.Color(60, 63, 64));
        jCheckBox3.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox3.setText("Mobile");
        jCheckBox3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jCheckBox3MouseEntered(evt);
            }
        });
        jPanel9.add(jCheckBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, -1, -1));

        jPanel2.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 240, -1, 240));

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

        excluirUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bin2.png"))); // NOI18N
        excluirUsuario.setBorderPainted(false);
        excluirUsuario.setContentAreaFilled(false);
        excluirUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                excluirUsuarioMouseClicked(evt);
            }
        });
        jPanel2.add(excluirUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 630, 50, 50));

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

        jPanel3.setBackground(new java.awt.Color(18, 18, 18));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Seu Perfil || Perfil do UsuárioEX");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(147, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 6, 640, 50));

        jPanel12.setBackground(new java.awt.Color(60, 63, 64));
        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel12MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel12MouseEntered(evt);
            }
        });

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Editar Perfil");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 590, 110, -1));

        jLabel2.setText("foto de perfil");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(122, 101, -1, -1));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("username");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(122, 207, 100, -1));

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(18, 18, 18));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(255, 255, 255));
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("descrição do usuário");
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setBorder(null);
        jTextArea1.setFocusable(false);
        jTextArea1.setHighlighter(null);
        jPanel2.add(jTextArea1, new org.netbeans.lib.awtextra.AbsoluteConstraints(284, 101, 280, 150));
        jTextArea1.getAccessibleContext().setAccessibleName("");

        jPanel14.setBackground(new java.awt.Color(60, 63, 64));
        jPanel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel14MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel14MouseEntered(evt);
            }
        });

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Convidar");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 590, -1, -1));

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
        jLabel8.setText("Mensagem");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 640, 110, -1));

        jPanel13.setBackground(new java.awt.Color(60, 63, 64));
        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel13MouseClicked(evt);
            }
        });

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Cancelar");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 640, 110, -1));

        jPanel15.setBackground(new java.awt.Color(60, 63, 64));
        jPanel15.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel15MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel15MouseEntered(evt);
            }
        });

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Seguir");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 590, 110, -1));

        jPanel6.setOpaque(false);

        jScrollPane3.setBorder(null);
        jScrollPane3.setOpaque(false);

        jTable3.setBackground(new java.awt.Color(60, 63, 64));
        jTable3.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jTable3.setForeground(new java.awt.Color(255, 255, 255));
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Plataformas Jogadas"
            }
        ));
        jTable3.setFocusable(false);
        jTable3.setOpaque(false);
        jTable3.setRowHeight(26);
        jScrollPane3.setViewportView(jTable3);

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
                "Jogos Favoritos"
            }
        ));
        jTable2.setFocusable(false);
        jTable2.setOpaque(false);
        jTable2.setRowHeight(26);
        jScrollPane2.setViewportView(jTable2);

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
                "Interesses"
            }
        ));
        jTable1.setFocusable(false);
        jTable1.setOpaque(false);
        jTable1.setRowHeight(26);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(108, 300, -1, 210));

        btnEdit3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editar.png"))); // NOI18N
        btnEdit3.setBorderPainted(false);
        btnEdit3.setContentAreaFilled(false);
        btnEdit3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEdit3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEdit3MouseEntered(evt);
            }
        });
        jPanel2.add(btnEdit3, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 510, 40, 30));

        btnEdit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editar.png"))); // NOI18N
        btnEdit1.setBorderPainted(false);
        btnEdit1.setContentAreaFilled(false);
        btnEdit1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEdit1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEdit1MouseEntered(evt);
            }
        });
        jPanel2.add(btnEdit1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 510, 40, 30));

        btnEdit2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editar.png"))); // NOI18N
        btnEdit2.setBorderPainted(false);
        btnEdit2.setContentAreaFilled(false);
        btnEdit2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEdit2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEdit2MouseEntered(evt);
            }
        });
        jPanel2.add(btnEdit2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 510, 40, 30));

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
        jPanel2.add(btnEdit4, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 160, 40, 30));

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
        if (retorno == null) {
            TelaPrincipal frame = new TelaPrincipal();
            frame.usr = usr;
            frame.setVisible(true);
            this.dispose();
        } else if (retorno == "TelaInfoJogo") {
            TelaInfoJogo frame = new TelaInfoJogo();
            frame.usr = usr;
            frame.jg.setNome_jogo(nome_jogo_retorno);
            frame.retorno = outroRetorno;
            frame.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnVoltarMouseClicked

    private void btnVoltarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVoltarMouseEntered
        btnVoltar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnVoltarMouseEntered

    private void jPanel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel11MouseClicked
        TelaEnviarMensagem frame = new TelaEnviarMensagem();
        frame.msg.setId_destinatario(outroUsr.getId_usuario());
        frame.msg.setId_remetente(usr.getId_usuario());
        frame.usr = usr;
        frame.retorno = "Tela Perfil";
        frame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jPanel11MouseClicked

    private void jPanel11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel11MouseEntered
        jPanel11.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jPanel11MouseEntered

    private void jPanel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel12MouseClicked
        jPanel13.setVisible(true);
        btnEdit1.setVisible(true);
        btnEdit2.setVisible(true);
        btnEdit3.setVisible(true);
        btnEdit4.setVisible(true);
    }//GEN-LAST:event_jPanel12MouseClicked

    private void jPanel12MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel12MouseEntered
        jPanel12.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jPanel12MouseEntered

    private void jPanel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel13MouseClicked
        jPanel13.setVisible(false);
        btnEdit1.setVisible(false);
        btnEdit2.setVisible(false);
        btnEdit3.setVisible(false);
    }//GEN-LAST:event_jPanel13MouseClicked

    private void jPanel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel14MouseClicked
        jPanel5.setVisible(true);
        jPanel6.setVisible(false);
    }//GEN-LAST:event_jPanel14MouseClicked

    private void jPanel14MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel14MouseEntered
        jPanel14.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jPanel14MouseEntered

    private void jPanel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel15MouseClicked
        seg.setId_seguidor(usr.getId_usuario());
        seg.setId_usuario(outroUsr.getId_usuario());

        if (estaSeguindo() == false) {
            segDAO = new SeguidoresDAO();
            segDAO.seguir(seg);
            TelaPerfil frame = new TelaPerfil();
            frame.usr = usr;
            frame.outroUsr.setNome_usuario(outroUsr.getNome_usuario());
            frame.setVisible(true);
            this.dispose();
        } else if (estaSeguindo() == true) {
            int resposta = JOptionPane.showOptionDialog(new JFrame(), "Deseja deixar de seguir " + outroUsr.getNome_usuario() + "?", "Deixar de Seguir",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                    new Object[]{"Não", "Sim"}, JOptionPane.YES_OPTION);
            if (resposta == JOptionPane.NO_OPTION) { //Inverti a opção para facilitar, para o Netbeans focar no "Não", caso o usuario clique sem querer em "Seguindo"
                segDAO = new SeguidoresDAO();
                segDAO.deixarDeSeguir(seg);
                TelaPerfil frame = new TelaPerfil();
                frame.usr = usr;
                frame.outroUsr.setNome_usuario(outroUsr.getNome_usuario());
                frame.setVisible(true);
                this.dispose();
            }
        }
    }//GEN-LAST:event_jPanel15MouseClicked

    private void jPanel15MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel15MouseEntered
        jPanel15.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jPanel15MouseEntered

    private void jPanel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel16MouseClicked
        cvt.setRemetente(usr.getId_usuario());
        cvt.setDestinatario(outroUsr.getId_usuario());

        cvtDAO = new ConvitesDAO();
        cvtDAO.enviarConvite(cvt);
        JOptionPane.showMessageDialog(null, "Concluido!");

        jPanel5.setVisible(false);
        jPanel6.setVisible(true);
    }//GEN-LAST:event_jPanel16MouseClicked

    private void jPanel16MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel16MouseEntered
        jPanel16.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jPanel16MouseEntered

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        jPanel5.setVisible(false);
        jPanel6.setVisible(true);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        jPanel7.setVisible(false);
        jPanel6.setVisible(true);
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
        selecionarCategoria(jTable4.getSelectedRow());
    }//GEN-LAST:event_jTable4MouseClicked

    private void jPanel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel17MouseClicked
        Interesses in = new Interesses();

        if (usr.isAdministrador() == true && !(outroUsr.getNome_usuario() == null || outroUsr.getNome_usuario().equals(usr.getNome_usuario()))) {
            in.setId_usuario(outroUsr.getId_usuario());
            inDAO = new InteressesDAO();
            inDAO.limparInteresses(in.getId_usuario());
            for (int i = 0; i < linhasSelecionadasCateg.size(); i++) {
                inDAO = new InteressesDAO();
                in.setId_categoria(inDAO.pegarIdCategoria(String.valueOf(jTable4.getValueAt(linhasSelecionadasCateg.get(i), 0))));
                inDAO.favoritarCategoria(in);
            }
            JOptionPane.showMessageDialog(null, "Concluido!");
            TelaPerfil frame = new TelaPerfil();
            frame.usr = usr;
            frame.outroUsr.setNome_usuario(outroUsr.getNome_usuario());
            frame.setVisible(true);
            this.dispose();

        } else {
            in.setId_usuario(usr.getId_usuario());
            inDAO = new InteressesDAO();
            inDAO.limparInteresses(in.getId_usuario());
            for (int i = 0; i < linhasSelecionadasCateg.size(); i++) {
                inDAO = new InteressesDAO();
                in.setId_categoria(inDAO.pegarIdCategoria(String.valueOf(jTable4.getValueAt(linhasSelecionadasCateg.get(i), 0))));
                inDAO.favoritarCategoria(in);
            }
            JOptionPane.showMessageDialog(null, "Concluido!");
            TelaPerfil frame = new TelaPerfil();
            frame.usr = usr;
            frame.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_jPanel17MouseClicked

    private void jPanel17MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel17MouseEntered
        jPanel17.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jPanel17MouseEntered

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        jPanel8.setVisible(false);
        jPanel6.setVisible(true);
    }//GEN-LAST:event_jLabel16MouseClicked

    private void jPanel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel18MouseClicked
        JogosFavoritos jf = new JogosFavoritos();
        if (usr.isAdministrador() == true && !(outroUsr.getNome_usuario() == null || outroUsr.getNome_usuario().equals(usr.getNome_usuario()))) {
            jf.setId_usuario(outroUsr.getId_usuario());
            jfDAO = new JogosFavoritosDAO();
            jfDAO.limparJogosFavoritos(jf.getId_usuario());
            for (int i = 0; i < linhasSelecionadasJogos.size(); i++) {
                jfDAO = new JogosFavoritosDAO();
                jf.setId_jogo(jfDAO.pegarIdJogo(String.valueOf(jTable5.getValueAt(linhasSelecionadasJogos.get(i), 0))));
                jfDAO.favoritarJogo(jf);
            }
            JOptionPane.showMessageDialog(null, "Concluido!");
            TelaPerfil frame = new TelaPerfil();
            frame.usr = usr;
            frame.outroUsr.setNome_usuario(outroUsr.getNome_usuario());
            frame.setVisible(true);
            this.dispose();
        } else {
            jf.setId_usuario(usr.getId_usuario());
            jfDAO = new JogosFavoritosDAO();
            jfDAO.limparJogosFavoritos(jf.getId_usuario());
            for (int i = 0; i < linhasSelecionadasJogos.size(); i++) {
                jfDAO = new JogosFavoritosDAO();
                jf.setId_jogo(jfDAO.pegarIdJogo(String.valueOf(jTable5.getValueAt(linhasSelecionadasJogos.get(i), 0))));
                jfDAO.favoritarJogo(jf);
            }
            JOptionPane.showMessageDialog(null, "Concluido!");
            TelaPerfil frame = new TelaPerfil();
            frame.usr = usr;
            frame.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_jPanel18MouseClicked

    private void jPanel18MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel18MouseEntered
        jPanel18.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jPanel18MouseEntered

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
        jPanel9.setVisible(false);
        jPanel6.setVisible(true);
    }//GEN-LAST:event_jLabel20MouseClicked

    private void jPanel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel19MouseClicked
        Plataformas pla = new Plataformas();

        pla.setPc(jCheckBox1.isSelected());
        pla.setConsole(jCheckBox2.isSelected());
        pla.setMobile(jCheckBox3.isSelected());

        if (usr.isAdministrador() == true && !(outroUsr.getNome_usuario() == null || outroUsr.getNome_usuario().equals(usr.getNome_usuario()))) {
            pla.setId_usuario(outroUsr.getId_usuario());
            plaDAO = new PlataformasDAO();
            plaDAO.limparPlataformas(pla.getId_usuario());
            plaDAO.cadastrarPlataformas(pla);
            JOptionPane.showMessageDialog(null, "Concluido!");
            TelaPerfil frame = new TelaPerfil();
            frame.usr = usr;
            frame.outroUsr.setNome_usuario(outroUsr.getNome_usuario());
            frame.setVisible(true);
            this.dispose();
        } else {
            pla.setId_usuario(usr.getId_usuario());
            plaDAO = new PlataformasDAO();
            plaDAO.limparPlataformas(pla.getId_usuario());
            plaDAO.cadastrarPlataformas(pla);
            JOptionPane.showMessageDialog(null, "Concluido!");
            TelaPerfil frame = new TelaPerfil();
            frame.usr = usr;
            frame.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_jPanel19MouseClicked

    private void jPanel19MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel19MouseEntered
        jPanel19.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jPanel19MouseEntered

    private void jTable5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseClicked
        selecionarJogos(jTable5.getSelectedRow());
    }//GEN-LAST:event_jTable5MouseClicked

    private void jTable6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable6MouseClicked
        jgDAO = new JogosDAO();
        cvt.setId_jogo(jgDAO.pegarIdJogoCC(jTable6.getValueAt(jTable6.getSelectedRow(), 0).toString()));
        jLabel28.setText(jTable6.getValueAt(jTable6.getSelectedRow(), 0).toString());
    }//GEN-LAST:event_jTable6MouseClicked

    private void btnExit2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExit2MouseClicked
        carregarJogosPorPesquisa(objJogos, jTextField2.getText(), "convite");
    }//GEN-LAST:event_btnExit2MouseClicked

    private void btnExit2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExit2MouseEntered
        btnExit2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnExit2MouseEntered

    private void btnExit3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExit3MouseClicked
        carregarJogosPorPesquisa(objJogos, jTextField1.getText(), "favoritos");
    }//GEN-LAST:event_btnExit3MouseClicked

    private void btnExit3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExit3MouseEntered
        btnExit3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnExit3MouseEntered

    private void btnEdit3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEdit3MouseClicked
        jPanel9.setVisible(true);
        jPanel6.setVisible(false);
    }//GEN-LAST:event_btnEdit3MouseClicked

    private void btnEdit3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEdit3MouseEntered
        btnEdit3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnEdit3MouseEntered

    private void btnEdit1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEdit1MouseClicked
        jPanel7.setVisible(true);
        jPanel6.setVisible(false);
    }//GEN-LAST:event_btnEdit1MouseClicked

    private void btnEdit1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEdit1MouseEntered
        btnEdit1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnEdit1MouseEntered

    private void btnEdit2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEdit2MouseClicked
        jPanel8.setVisible(true);
        jPanel6.setVisible(false);
    }//GEN-LAST:event_btnEdit2MouseClicked

    private void btnEdit2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEdit2MouseEntered
        btnEdit2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnEdit2MouseEntered

    private void jLabel26MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel26MouseClicked
        jPanel10.setVisible(false);
        jPanel6.setVisible(true);
    }//GEN-LAST:event_jLabel26MouseClicked

    private void jPanel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel20MouseClicked
        if (usr.isAdministrador() == true && !(outroUsr.getNome_usuario() == null || outroUsr.getNome_usuario().equals(usr.getNome_usuario()))) {
            usrDAO = new UsuarioDAO();
            usrDAO.alterarDescricao(outroUsr.getId_usuario(), jTextArea2.getText());
            JOptionPane.showMessageDialog(null, "Concluido!");
            TelaPerfil frame = new TelaPerfil();
            frame.usr = usr;
            frame.outroUsr.setNome_usuario(outroUsr.getNome_usuario());
            frame.setVisible(true);
            this.dispose();
        } else {
            usrDAO = new UsuarioDAO();
            usrDAO.alterarDescricao(usr.getId_usuario(), jTextArea2.getText());
            JOptionPane.showMessageDialog(null, "Concluido!");
            TelaPerfil frame = new TelaPerfil();
            frame.usr = usr;
            frame.setVisible(true);
            this.dispose();
        }
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

    private void btnEdit4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEdit4MouseClicked
        jPanel10.setVisible(true);
        jPanel6.setVisible(false);
    }//GEN-LAST:event_btnEdit4MouseClicked

    private void btnEdit4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEdit4MouseEntered
        btnEdit4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnEdit4MouseEntered

    private void excluirUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_excluirUsuarioMouseClicked
        int resposta = JOptionPane.showOptionDialog(new JFrame(), "ATENÇÃO! A exclusão dessa conta será permanente, deseja continuar?", "Sair",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                new Object[]{"Não", "Sim"}, JOptionPane.YES_OPTION);
        if (resposta == JOptionPane.NO_OPTION) {
            int confirmacao = JOptionPane.showOptionDialog(new JFrame(), "Tem certeza? Isso não poderá ser desfeito!", "Sair",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                    new Object[]{"Não", "Sim"}, JOptionPane.YES_OPTION);
            if (confirmacao == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(null, "Usuário " + outroUsr.getNome_usuario() + " excluído.");
                usrDAO = new UsuarioDAO();
                usrDAO.excluirUsuario(outroUsr.getId_usuario());
                if (retorno == null) {
                    TelaPrincipal frame = new TelaPrincipal();
                    frame.usr = usr;
                    frame.setVisible(true);
                    this.dispose();
                } else if (retorno == "TelaInfoJogo") {
                    TelaInfoJogo frame = new TelaInfoJogo();
                    frame.usr = usr;
                    frame.jg.setNome_jogo(nome_jogo_retorno);
                    frame.retorno = outroRetorno;
                    frame.setVisible(true);
                    this.dispose();
                }
            }
        }
    }//GEN-LAST:event_excluirUsuarioMouseClicked

    private void jTextArea2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (usr.isAdministrador() == true && !(outroUsr.getNome_usuario() == null || outroUsr.getNome_usuario().equals(usr.getNome_usuario()))) {
                usrDAO = new UsuarioDAO();
                usrDAO.alterarDescricao(outroUsr.getId_usuario(), jTextArea2.getText());
                JOptionPane.showMessageDialog(null, "Concluido!");
                TelaPerfil frame = new TelaPerfil();
                frame.usr = usr;
                frame.outroUsr.setNome_usuario(outroUsr.getNome_usuario());
                frame.setVisible(true);
                this.dispose();
            } else {
                usrDAO = new UsuarioDAO();
                usrDAO.alterarDescricao(usr.getId_usuario(), jTextArea2.getText());
                JOptionPane.showMessageDialog(null, "Concluido!");
                TelaPerfil frame = new TelaPerfil();
                frame.usr = usr;
                frame.setVisible(true);
                this.dispose();
            }
        }
    }//GEN-LAST:event_jTextArea2KeyPressed

    private void jLabel26MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel26MouseEntered
        jLabel26.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jLabel26MouseEntered

    private void jLabel20MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseEntered
        jLabel20.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jLabel20MouseEntered

    private void jCheckBox1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox1MouseEntered
        jCheckBox1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jCheckBox1MouseEntered

    private void jCheckBox2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox2MouseEntered
        jCheckBox2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jCheckBox2MouseEntered

    private void jCheckBox3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox3MouseEntered
        jCheckBox3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jCheckBox3MouseEntered

    private void jTable4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseEntered
        jTable4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jTable4MouseEntered

    private void jLabel7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseEntered
        jLabel7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jLabel7MouseEntered

    private void jLabel16MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseEntered
        jLabel16.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jLabel16MouseEntered

    private void jTable5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseEntered
        jTable5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jTable5MouseEntered

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            carregarJogosPorPesquisa(objJogos, jTextField1.getText(), "favoritos");
        }
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jTable6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable6MouseEntered
        jTable6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jTable6MouseEntered

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            carregarJogosPorPesquisa(objJogos, jTextField2.getText(), "convite");
        }
    }//GEN-LAST:event_jTextField2KeyPressed

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
            java.util.logging.Logger.getLogger(TelaPerfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPerfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPerfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPerfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPerfil().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEdit1;
    private javax.swing.JButton btnEdit2;
    private javax.swing.JButton btnEdit3;
    private javax.swing.JButton btnEdit4;
    private javax.swing.JButton btnExit2;
    private javax.swing.JButton btnExit3;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JButton excluirUsuario;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}