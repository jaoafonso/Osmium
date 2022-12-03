package dao;

import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Jogos;

public class JogosDAO {

    private Connection connection;

    public JogosDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void excluirJogo(int id_jogo) {
        try {
            String sql1 = "DELETE FROM categorias_do_jogo WHERE id_jogo  = " + id_jogo + "";
            String sql2 = "DELETE FROM jogos_favoritos WHERE id_jogo  = " + id_jogo + "";
            String sql3 = "DELETE FROM convites WHERE id_jogo  = " + id_jogo + "";
            String sql4 = "DELETE FROM jogos WHERE id_jogo  = " + id_jogo + "";

            PreparedStatement stmt1 = connection.prepareStatement(sql1);
            PreparedStatement stmt2 = connection.prepareStatement(sql2);
            PreparedStatement stmt3 = connection.prepareStatement(sql3);
            PreparedStatement stmt4 = connection.prepareStatement(sql4);

            stmt1.execute();
            stmt2.execute();
            stmt3.execute();
            stmt4.execute();

            stmt1.close();
            stmt2.close();
            stmt3.close();
            stmt4.close();
            connection.close();

        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public int[] idCategoriasJogo(int id_jogo) {

        try {
            ArrayList<Integer> idCategorias = new ArrayList<Integer>();

            String sql = "";
            sql = "SELECT id_categoria FROM categorias_do_jogo WHERE id_jogo = " + id_jogo;
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                idCategorias.add(rs.getInt("id_categoria")
                );
            }

            ps.close();
            rs.close();

            int[] arr = idCategorias.stream().mapToInt(i -> i).toArray();

            return arr;

        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "Erro preencher o ArrayList");
            return null;
        }
    }

    public String nomeUsuario(int id_usuario) {
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
            JOptionPane.showMessageDialog(null, "nomeUsuario():" + e.getMessage());
            return null;
        }
    }

    public String encontrarParceiro(int id_jogo) {
        try {
            String nome_usuario = "";

            PreparedStatement ps = connection.prepareStatement("SELECT id_usuario FROM jogos_favoritos WHERE id_jogo = "+ id_jogo +" ORDER BY RAND() LIMIT 1");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                nome_usuario = nomeUsuario(rs.getInt("id_usuario"));

            }
            ps.close();
            rs.close();
            connection.close();

            return nome_usuario;
        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "encontrarParceiro():" + e.getMessage());
            return null;
        }
    }

    public String stringCategoriasJogo(int id_jogo) {

        try {
            int[] idCategorias = idCategoriasJogo(id_jogo);
            String[] categorias = new String[idCategorias.length];

            String[] sql = new String[idCategorias.length];;
            for (int i = 0; i < idCategorias.length; i++) {

                sql[i] = "SELECT nome_categoria FROM categoria_de_jogo WHERE id_categoria = " + idCategorias[i];
                PreparedStatement ps = connection.prepareStatement(sql[i]);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {

                    categorias[i] = (rs.getString("nome_categoria"));
                }
                ps.close();
                rs.close();
            }

            String nomeCategorias = "";

            int outras = idCategorias.length - 3;

            if (idCategorias.length > 3) {
                for (int i = 0; i < 3; i++) {
                    if (i < 2) {
                        nomeCategorias += categorias[i] + ", ";
                    } else {
                        nomeCategorias += categorias[i];
                    }
                }
                nomeCategorias = nomeCategorias + " e outras " + outras + " categorias";
            } else {
                for (int i = 0; i < idCategorias.length; i++) {
                    if (i < idCategorias.length - 1) {
                        nomeCategorias += categorias[i] + ", ";
                    } else {
                        nomeCategorias += categorias[i];
                    }
                }
            }

            return nomeCategorias;

        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }

    public void alterarDescricao(int id_jogo, String nova_descricao) {
        try {
            String sql = "";
            sql = "UPDATE jogos SET desc_jogo = '" + nova_descricao + "' WHERE id_jogo = " + id_jogo;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();
            connection.close();

        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }
    
    public ArrayList listarJogos() {
        try {
            ArrayList dado = new ArrayList();

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM jogos ORDER BY nome_jogo");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                dado.add(new Object[]{
                    rs.getString("nome_jogo"),
                    stringCategoriasJogo(rs.getInt("id_jogo"))

                });

            }
            ps.close();
            rs.close();
            connection.close();

            return dado;
        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "listarJogos():" + e.getMessage());
            return null;
        }
    }
    
    public String pegarNomeJogoCC(int id_jogo) {

        try {
            String nome_jogo = "";

            PreparedStatement ps = connection.prepareStatement("SELECT nome_jogo FROM jogos WHERE id_jogo =" + id_jogo);
            ResultSet rs = ps.executeQuery();

            rs.next();
            nome_jogo = rs.getString("nome_jogo");

            ps.close();
            rs.close();
            connection.close();

            return nome_jogo;
        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "pegarNomeJogoCC():" + e.getMessage());
            return null;
        }
    }
    
    public void adicionarJogo(Jogos objJogos) {
        try {
            String sql = "";
            sql = "INSERT INTO jogos(nome_jogo, desc_jogo) VALUES(?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, objJogos.getNome_jogo());
            stmt.setString(2, objJogos.getDesc_jogo());

            stmt.execute();
            stmt.close();

        } catch (SQLException u) {
            throw new RuntimeException(u);
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
    
    public int pegarIdJogoCC(String nome_jogo) {

        try {
            int id_jogo = 0;

            PreparedStatement ps = connection.prepareStatement("SELECT id_jogo FROM jogos WHERE nome_jogo ='" + nome_jogo + "'");
            ResultSet rs = ps.executeQuery();

            rs.next();
            id_jogo = rs.getInt("id_jogo");

            ps.close();
            rs.close();
            connection.close();

            return id_jogo;
        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "pegarIdJogo():" + e.getMessage());
            return 0;
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

    public int[] filtrarJogosPorCategoria(int id_categoria) {
        try {
            ArrayList<Integer> jogosFiltrados = new ArrayList<Integer>();

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM categorias_do_jogo WHERE id_categoria =" + id_categoria);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                jogosFiltrados.add(rs.getInt("id_jogo"));
            }
            ps.close();
            rs.close();

            int[] arr = jogosFiltrados.stream().mapToInt(i -> i).toArray();
            return arr;
        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "filtrarJogosPorCategoria():" + e.getMessage());
            return null;
        }
    }

    public ArrayList listarJogosPorCategoria(String nome_categoria) {
        try {
            int id_categoria = pegarIdCategoria(nome_categoria);
            int[] id_jogos = filtrarJogosPorCategoria(id_categoria);

            ArrayList dado = new ArrayList();

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM jogos");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                for (int i = 0; i < id_jogos.length; i++) {
                    if (id_jogos[i] == rs.getInt("id_jogo")) {
                        dado.add(new Object[]{
                            rs.getString("nome_jogo"),
                            stringCategoriasJogo(rs.getInt("id_jogo"))

                        });
                    }
                }

            }
            ps.close();
            rs.close();
            connection.close();

            return dado;
        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "listarJogosPorCategoria():" + e.getMessage());
            return null;
        }
    }

    public int[] filtrarJogosPorPesquisa(String pesquisa) {
        try {
            ArrayList<Integer> resultados = new ArrayList<Integer>();

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM jogos WHERE LOWER(nome_jogo) LIKE '%" + pesquisa + "%'");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                resultados.add(rs.getInt("id_jogo"));
            }
            ps.close();
            rs.close();

            int[] arr = resultados.stream().mapToInt(i -> i).toArray();
            return arr;
        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "filtrarJogosPorPesquisa():" + e.getMessage());
            return null;
        }
    }

    public ArrayList listarJogosPorPesquisa(String pesquisa) {
        try {
            int[] id_jogos = filtrarJogosPorPesquisa(pesquisa);

            ArrayList dado = new ArrayList();

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM jogos");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                for (int i = 0; i < id_jogos.length; i++) {
                    if (id_jogos[i] == rs.getInt("id_jogo")) {
                        dado.add(new Object[]{
                            rs.getString("nome_jogo"),
                            stringCategoriasJogo(rs.getInt("id_jogo"))

                        });
                    }
                }
            }
            ps.close();
            rs.close();
            connection.close();

            return dado;
        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "listarJogosPorPesquisa():" + e.getMessage());
            return null;
        }
    }
}
