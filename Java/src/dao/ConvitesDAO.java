package dao;

import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Convites;

public class ConvitesDAO {

    private Connection connection;

    public ConvitesDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public String nomeRemetente(int id_usuario) {
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
            JOptionPane.showMessageDialog(null, "nomeRemetente():" + e.getMessage());
            return null;
        }
    }
    
    public String nomeJogo(int id_jogo) {
        try {

            PreparedStatement ps = connection.prepareStatement("SELECT nome_jogo FROM jogos WHERE id_jogo=" + id_jogo);
            ResultSet rs = ps.executeQuery();

            rs.next();
            String nomeJogo = rs.getString("nome_jogo");
            
            ps.close();
            rs.close();

            return nomeJogo;
        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "nomeJogo():" + e.getMessage());
            return null;
        }
    }
    
    public int idUsuario(String nome_usuario) {
        try {

            PreparedStatement ps = connection.prepareStatement("SELECT id_usuario FROM usuario WHERE nome_usuario='" + nome_usuario +"'");
            ResultSet rs = ps.executeQuery();

            rs.next();
            int idUsuario = rs.getInt("id_usuario");
            
            ps.close();
            rs.close();

            return idUsuario;
        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "idUsuario():" + e.getMessage());
            return 0;
        }
    }
    
    public ArrayList listarConvites(String nome_usuario) {
        try {
            ArrayList dado = new ArrayList();

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM convites WHERE destinatario=" + idUsuario(nome_usuario));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                dado.add(new Object[]{
                    nomeRemetente(rs.getInt("remetente")),
                    nomeJogo(rs.getInt("id_jogo"))
                });

            }
            ps.close();
            rs.close();
            connection.close();

            return dado;
        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "listarConvites():" + e.getMessage());
            return null;
        }
    }
    
    public void enviarConvite(Convites objConvite) {
        try {
            String sql = "";
            sql = "INSERT INTO convites(remetente,destinatario,id_jogo,mensagem) VALUES(?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, objConvite.getRemetente());
            stmt.setInt(2, objConvite.getDestinatario());
            stmt.setInt(3, objConvite.getId_jogo());
            stmt.setString(4, objConvite.getMensagem());

            stmt.execute();
            stmt.close();

        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }
}
