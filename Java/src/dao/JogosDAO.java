package dao;

import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class JogosDAO {

    private Connection connection;

    public JogosDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public String stringCategoriasJogo(ArrayList idCategorias) {

        ArrayList stringCategorias = new ArrayList();
        
        

        String categorias = String.join(", ", stringCategorias);
        return categorias;
    }

    public ArrayList categoriasJogo(int id_jogo) {

        // criar uma funcao que carrega as categorias da
        //tabela categorias_de_jogo com base no nome do jogo, e 
        //adiciona todas as categorias em uma só string (exemplo: ação, terror, 2d)
        try {
            ArrayList idCategorias = new ArrayList();

            String sql = "";
            sql = "SELECT id_categoria FROM categorias_do_jogo WHERE id_jogo = " + id_jogo;
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                idCategorias.add(new Object[]{
                    rs.getInt("id_categoria")
                });

            }
            ps.close();
            rs.close();
            connection.close();

            return idCategorias;
        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "Erro preencher o ArrayList");
            return null;
        }
    }

    public ArrayList listarJogos() {
        try {

            ArrayList dado = new ArrayList();

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM jogos");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                dado.add(new Object[]{
                    rs.getString("nome_jogo"),
                    stringCategoriasJogo(categoriasJogo(rs.getInt("id_jogo")))

                });

            }
            ps.close();
            rs.close();
            connection.close();

            return dado;
        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "Erro preencher o ArrayList");
            return null;
        }
    }
}
