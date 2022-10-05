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

    /*public String stringCategoriasJogo(ArrayList idCategorias) {

        ArrayList stringCategorias = new ArrayList();

        String categorias = String.join(", ", stringCategorias);
        return categorias;
    }*/
    public String stringCategoriasJogo(int id_jogo) {

        // criar uma funcao que carrega as categorias da
        //tabela categorias_de_jogo com base no nome do jogo, e 
        //adiciona todas as categorias em uma só string (exemplo: ação, terror, 2d)
        try {
            ArrayList<Integer> idCategorias = new ArrayList<Integer>();
            ArrayList stringCategorias = new ArrayList();

            String sql = "";
            String sqlS = "";
            String stringTest = "";
            sql = "SELECT id_categoria FROM categorias_do_jogo WHERE id_jogo = " + id_jogo;
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                idCategorias.add(rs.getInt("id_categoria")
                );
                /*sqlS = "SELECT * FROM categorias WHERE id_categoria = " + rs.getInt("id_categoria");
                PreparedStatement ps1 = connection.prepareStatement(sqlS);
                ResultSet rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    stringCategorias.add(new Object[]{
                        rs1.getString("nome_categoria")
                    });
                }*/

            }
            int[] str = new int[idCategorias.size()];
            for (int i = 0; i < idCategorias.size(); i++) {
                str[i] = idCategorias.get(i);
            }

            //String listString = String.join("", idCategorias);

            ps.close();
            rs.close();
            connection.close();
            //String stringCategoriasJogo = String.join(", ", stringCategorias);
            
            for (int i = 0; i < idCategorias.size(); i++) {
                stringTest = stringTest + str[i];
            }
            System.out.println(stringTest);
            return stringTest;

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
                int test = rs.getInt("id_jogo");
                //System.out.println(stringCategoriasJogo(1));
                dado.add(new Object[]{
                    rs.getString("nome_jogo"),
                    test

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
