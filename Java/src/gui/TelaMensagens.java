/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.MensagensDAO;
import dao.SeguidoresDAO;
import factory.ConnectionFactory;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import modelo.Mensagens;
import modelo.Seguidores;
import modelo.ModelTable;
import modelo.Usuario;

/**
 *
 * @author Usuario
 */
public class TelaMensagens extends javax.swing.JFrame {

    /**
     * Creates new form TelaMensagens
     */
    Connection connection;

    private Mensagens objMensagens;
    private MensagensDAO msgDAO;
    private Seguidores objSeguidores;
    private SeguidoresDAO segDAO;

    public TelaMensagens() {
        initComponents();

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                carregarUsuarioPadrao();
                carregarSeguidoresMutuos(objSeguidores);
                carregarMensagens(objMensagens);

                jTable2.getColumnModel().getColumn(2).setMinWidth(0);
                jTable2.getColumnModel().getColumn(2).setMaxWidth(0);
                jTable2.getColumnModel().getColumn(2).setWidth(0);
                jTable2.getColumnModel().getColumn(0).setMinWidth(300);
                jTable2.getColumnModel().getColumn(0).setMaxWidth(300);
                jTable2.getColumnModel().getColumn(0).setWidth(300);
            }
        });

        // Configurações de aparência da tabela dos amigos
        jScrollPane1.getViewport().setBackground(new Color(60, 63, 64));
        JTableHeader header = jTable1.getTableHeader();
        DefaultTableCellRenderer head_render = new DefaultTableCellRenderer();
        header.setPreferredSize(new Dimension(100, 30));
        head_render.setBackground(new Color(122, 105, 190));
        jTable1.getTableHeader().setDefaultRenderer(head_render);
        jTable1.setGridColor(new Color(18, 18, 18));
        jTable1.setShowHorizontalLines(true);
        jTable1.setRowSelectionAllowed(false);

        // Configurações de aparência da tabela das mensagens
        jScrollPane2.getViewport().setBackground(new Color(60, 63, 64));
        JTableHeader header2 = jTable2.getTableHeader();
        header2.setPreferredSize(new Dimension(100, 30));
        jTable2.getTableHeader().setDefaultRenderer(head_render);
        ((DefaultTableCellRenderer) jTable2.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
        jTable2.setGridColor(new Color(18, 18, 18));
        jTable2.setShowHorizontalLines(true);
        jTable2.setShowVerticalLines(true);
        jTable2.setRowSelectionAllowed(false);

    }

    Usuario usr = new Usuario();

    public int pegarIdUsuario(String nome_usuario) {
        try {
            int id_usuario = 0;

            this.connection = new ConnectionFactory().getConnection();

            String sql = "SELECT * FROM usuario WHERE nome_usuario='" + nome_usuario + "'";
            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                id_usuario = rs.getInt("id_usuario");
            }
            connection.close();
            return id_usuario;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public void escreverMensagem() {
        TelaEnviarMensagem frame = new TelaEnviarMensagem();
        frame.msg.setId_destinatario(pegarIdUsuario(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString()));
        frame.msg.setId_remetente(usr.getId_usuario());
        frame.usr.setNome_usuario(usr.getNome_usuario());
        frame.retorno = "Tela Mensagens";
        frame.setVisible(true);
        this.dispose();
    }

    public void abrirMensagem() {
        TelaLerMensagem frame = new TelaLerMensagem();
        frame.msg.setId_mensagem(Integer.valueOf(jTable2.getValueAt(jTable2.getSelectedRow(), 2).toString()));
        frame.usr.setNome_usuario(usr.getNome_usuario());
        frame.setVisible(true);
        this.dispose();
    }

    public void carregarMensagens(Mensagens objMensagens) {

        msgDAO = new MensagensDAO();
        ArrayList dados = new ArrayList();

        objMensagens = new Mensagens();
        dados = msgDAO.listarMensagens(usr.getId_usuario());
        String[] colunas = objMensagens.getColunas();

        ModelTable modelo = new ModelTable(dados, colunas);

        jTable2.setModel(modelo);

    }

    public void carregarSeguidoresMutuos(Seguidores objSeguidores) {

        segDAO = new SeguidoresDAO();
        ArrayList dados = new ArrayList();

        objSeguidores = new Seguidores();
        dados = segDAO.listarSeguidoresMutuos(usr.getId_usuario());
        String[] colunas = objSeguidores.getColunas();

        ModelTable modelo = new ModelTable(dados, colunas);

        jTable1.setModel(modelo);

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
        btnVoltar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mensagens");
        setMinimumSize(new java.awt.Dimension(1280, 720));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(33, 37, 41));

        jPanel2.setBackground(new java.awt.Color(18, 18, 18));

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
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

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
                "Seguidores Mútuos"
            }
        ));
        jTable1.setFocusable(false);
        jTable1.setOpaque(false);
        jTable1.setRowHeight(26);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
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
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Remetente", "Mensagem", "ID"
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
            jTable2.getColumnModel().getColumn(0).setMinWidth(300);
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(300);
            jTable2.getColumnModel().getColumn(0).setMaxWidth(300);
            jTable2.getColumnModel().getColumn(2).setMinWidth(0);
            jTable2.getColumnModel().getColumn(2).setPreferredWidth(0);
            jTable2.getColumnModel().getColumn(2).setMaxWidth(0);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1005, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addGap(14, 14, 14))
        );

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

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnVoltarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVoltarMouseClicked
        // TODO add your handling code here:
        TelaPrincipal frame = new TelaPrincipal();
        frame.usr.setNome_usuario(usr.getNome_usuario());
        frame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnVoltarMouseClicked

    private void btnVoltarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVoltarMouseEntered
        // TODO add your handling code here:
        btnVoltar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnVoltarMouseEntered

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        escreverMensagem();
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        abrirMensagem();
    }//GEN-LAST:event_jTable2MouseClicked

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
            java.util.logging.Logger.getLogger(TelaMensagens.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaMensagens.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaMensagens.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaMensagens.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaMensagens().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVoltar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
