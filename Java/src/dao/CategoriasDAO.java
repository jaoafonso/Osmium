package dao;

import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class CategoriasDAO {
    
    private Connection connection;
    
    public CategoriasDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public ArrayList listarCategorias() {
        try {

            ArrayList dado = new ArrayList();

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM categoria_de_jogo");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                dado.add(new Object[]{
                    rs.getString("nome_categoria")
                });

            }
            ps.close();
            rs.close();
            connection.close();

            return dado;
        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "listarCategorias():" + e.getMessage());
            return null;
        }
    }
    
    public String pegarNomeCategoria(int id_categoria) {
        try {

            String nome_categoria = "";

            PreparedStatement ps = connection.prepareStatement("SELECT nome_categoria FROM categoria_de_jogo WHERE id_categoria=" + id_categoria);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                nome_categoria = rs.getString("nome_categoria");

            }
            ps.close();
            rs.close();

            return nome_categoria;
        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "pegarNomeCategoria():" + e.getMessage());
            return null;
        }
    }
    
    public ArrayList listarCategoriasJogo(int id_jogo) {
        try {

            ArrayList dado = new ArrayList();

            PreparedStatement ps = connection.prepareStatement("SELECT id_categoria FROM categorias_do_jogo WHERE id_jogo=" + id_jogo);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                dado.add(new Object[]{
                    pegarNomeCategoria(rs.getInt("id_categoria"))
                });

            }
            ps.close();
            rs.close();
            connection.close();

            return dado;
        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "listarCategoriasJogo():" + e.getMessage());
            return null;
        }
    }
}