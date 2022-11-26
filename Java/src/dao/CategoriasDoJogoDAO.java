package dao;

import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.CategoriasDoJogo;

public class CategoriasDoJogoDAO {

    private Connection connection;

    public CategoriasDoJogoDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public void limparCategorias(int id_jogo) {
        try {
            String sql = "";
            sql = "DELETE FROM categorias_do_jogo WHERE id_jogo = " + id_jogo;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();

        } catch (SQLException u) {
            throw new RuntimeException(u);
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
    
    public void adicionarCategoria(CategoriasDoJogo objCategoriasDoJogo) {

        try {
            String sql = "";
            sql = "INSERT INTO categorias_do_jogo(id_jogo, id_categoria) VALUES(?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, objCategoriasDoJogo.getId_jogo());
            stmt.setInt(2, objCategoriasDoJogo.getId_categoria());

            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "adicionarCategoria():" + e.getMessage());
        }

    }
    
}