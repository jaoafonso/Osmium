package dao;

import factory.ConnectionFactory;
import modelo.Mensagens;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class MensagensDAO {
    
    Connection connection;

    public MensagensDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public void excluirMensagem(int id_mensagem) {
        try {
            String sql = "";
            sql = "DELETE FROM mensagens WHERE id_mensagem = " + id_mensagem;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();

        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }
    
    public void enviarMensagem(Mensagens objMsg) {
        try {
            String sql = "";
            sql = "INSERT INTO mensagens(id_remetente, id_destinatario, mensagem) VALUES(?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, objMsg.getId_remetente());
            stmt.setInt(2, objMsg.getId_destinatario());
            stmt.setString(3, objMsg.getMensagem());

            stmt.execute();
            stmt.close();

        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }
    
    public String pegarNomeUsuario(int id_usuario) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT nome_usuario FROM usuario WHERE id_usuario=" + id_usuario);
            ResultSet rs = ps.executeQuery();

            rs.next();
            String nomeRemetente = rs.getString("nome_usuario");

            ps.close();
            rs.close();

            return nomeRemetente;
        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "pegarNomeUsuario():" + e.getMessage());
            return null;
        }
    }
    
    public ArrayList listarMensagensRecebidas(int id_usuario) {
        try {
            ArrayList dado = new ArrayList();

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM mensagens WHERE id_destinatario = " + id_usuario + " ORDER BY id_mensagem DESC");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                dado.add(new Object[]{
                    pegarNomeUsuario(rs.getInt("id_remetente")),
                    rs.getString("mensagem"),
                    rs.getInt("id_mensagem")
                });
            }
            ps.close();
            rs.close();
            connection.close();

            return dado;
        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "listarMensagensRecebidas():" + e.getMessage());
            return null;
        }
    }
    
    public ArrayList listarMensagensEnviadas(int id_usuario) {
        try {
            ArrayList dado = new ArrayList();

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM mensagens WHERE id_remetente = " + id_usuario + " ORDER BY id_mensagem DESC");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                dado.add(new Object[]{
                    pegarNomeUsuario(id_usuario),
                    rs.getString("mensagem"),
                    rs.getInt("id_mensagem")
                });
            }
            ps.close();
            rs.close();
            connection.close();

            return dado;
        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "listarMensagensEnviadas():" + e.getMessage());
            return null;
        }
    }
}
