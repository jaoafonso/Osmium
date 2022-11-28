package dao;

import factory.ConnectionFactory;
import java.sql.Connection;
import modelo.Interesses;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class InteressesDAO {

    private Connection connection;

    public InteressesDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public String nomeCategoria(int id_categoria) {
        try {

            PreparedStatement ps = connection.prepareStatement("SELECT nome_categoria FROM categoria_de_jogo WHERE id_categoria=" + id_categoria);
            ResultSet rs = ps.executeQuery();

            rs.next();
            String nomeCategoria = rs.getString("nome_categoria");
            
            ps.close();
            rs.close();

            return nomeCategoria;
        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "nomeCategoria():" + e.getMessage());
            return null;
        }
    }
    
    public ArrayList listarInteresses(int id_usuario) {
        try {
            ArrayList dado = new ArrayList();

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM interesses_do_usuario WHERE id_usuario = " + id_usuario);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                dado.add(new Object[]{
                    nomeCategoria(rs.getInt("id_categoria"))
                });

            }
            ps.close();
            rs.close();
            connection.close();

            return dado;
        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "listarPlataformas():" + e.getMessage());
            return null;
        }
    }

    public void limparInteresses(int id_usuario) {
        try {
            String sql = "";
            sql = "DELETE FROM interesses_do_usuario WHERE id_usuario = " + id_usuario;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();
            connection.close();

        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }
    
    public void favoritarCategoria(Interesses objInteresses) {

        try {
            String sql = "";
            sql = "INSERT INTO interesses_do_usuario(id_usuario, id_categoria) VALUES(?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, objInteresses.getId_usuario());
            stmt.setInt(2, objInteresses.getId_categoria());

            stmt.execute();
            stmt.close();
            connection.close();

        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "favoritarCategoria():" + e.getMessage());
        }

    }

    public int pegarIdCategoria(String nome_categoria) {

        try {
            int id_categoria = 0;

            PreparedStatement ps = connection.prepareStatement("SELECT id_categoria FROM categoria_de_jogo WHERE nome_categoria ='" + nome_categoria + "'");
            ResultSet rs = ps.executeQuery();

            rs.next();
            id_categoria = rs.getInt("id_categoria");

            ps.close();
            rs.close();

            return id_categoria;
        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "pegarIdCategoria():" + e.getMessage());
            return 0;
        }
    }
}
