package dao;

import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.JogosFavoritos;

public class JogosFavoritosDAO {

    private Connection connection;

    public JogosFavoritosDAO() {
        this.connection = new ConnectionFactory().getConnection();
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

    public ArrayList listarJogosFavoritos(int id_usuario) {
        try {
            ArrayList dado = new ArrayList();

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM jogos_favoritos WHERE id_usuario = " + id_usuario);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                dado.add(new Object[]{
                    nomeJogo(rs.getInt("id_jogo"))
                });

            }
            ps.close();
            rs.close();
            connection.close();

            return dado;
        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "listarJogosFavoritos():" + e.getMessage());
            return null;
        }
    }

    public int pegarIdJogo(String nome_jogo) {
        try {
            int id_jogo = 0;

            PreparedStatement ps = connection.prepareStatement("SELECT id_jogo FROM jogos WHERE nome_jogo ='" + nome_jogo + "'");
            ResultSet rs = ps.executeQuery();

            rs.next();
            id_jogo = rs.getInt("id_jogo");

            ps.close();
            rs.close();

            return id_jogo;
        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "pegarIdJogo():" + e.getMessage());
            return 0;
        }
    }

    public void limparJogosFavoritos(int id_usuario) {
        try {
            String sql = "";
            sql = "DELETE FROM jogos_favoritos WHERE id_usuario = " + id_usuario;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();

        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public void favoritarJogo(JogosFavoritos objJF) {
        try {
            String sql = "";
            sql = "INSERT INTO jogos_favoritos(id_usuario, id_jogo) VALUES(?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, objJF.getId_usuario());
            stmt.setInt(2, objJF.getId_jogo());

            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "favoritarJogo():" + e.getMessage());
        }
    }
}
