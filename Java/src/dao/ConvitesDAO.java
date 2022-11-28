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
    
    public int idJogo(String nome_jogo) {
        try {
            
            PreparedStatement ps = connection.prepareStatement("SELECT id_jogo FROM jogos WHERE nome_jogo='" + nome_jogo +"'");
            ResultSet rs = ps.executeQuery();

            rs.next();
            int id_jogo = rs.getInt("id_jogo");
            
            ps.close();
            rs.close();

            return id_jogo;
        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "idUsuario():" + e.getMessage() + nome_jogo);
            return 0;
        }
    }
    
    public String infoConvite(String nome_remetente, String nome_jogo) {
        String info_convite = nome_remetente + " te convidou para jogar " + nome_jogo;
        return info_convite;
    }
    
    public ArrayList listarConvites(int id_usuario) {
        try {
            ArrayList dado = new ArrayList();

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM convites WHERE destinatario=" + id_usuario + " ORDER BY id_convite DESC");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                dado.add(new Object[]{
                    infoConvite(nomeRemetente(rs.getInt("remetente")), nomeJogo(rs.getInt("id_jogo"))),
                    rs.getInt("id_convite")
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
            sql = "INSERT INTO convites(remetente,destinatario,id_jogo) VALUES(?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, objConvite.getRemetente());
            stmt.setInt(2, objConvite.getDestinatario());
            stmt.setInt(3, objConvite.getId_jogo());

            stmt.execute();
            stmt.close();
            connection.close();

        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }
}
