package dao;

import factory.ConnectionFactory;
import modelo.Usuario;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class UsuarioDAO {

    private Connection connection;

    public UsuarioDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public boolean verificarDisponibilidade(String nome_usuario) {
        try {
            boolean isDisponivel = false;

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM usuario WHERE nome_usuario='" + nome_usuario + "'");
            ResultSet rs = ps.executeQuery();

            if (rs.next() == false) {
                isDisponivel = true;
            } else {
                connection.close();
            }

            ps.close();
            rs.close();

            return isDisponivel;
        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "verificarDisponibilidade():" + e.getMessage());
            return false;
        }
    }

    public void excluirUsuario(int id_usuario) {
        try {
            String sql1 = "DELETE FROM interesses_do_usuario WHERE id_usuario  = "+ id_usuario +"";
            String sql2 = "DELETE FROM jogos_favoritos WHERE id_usuario  = "+ id_usuario +"";
            String sql3 = "DELETE FROM plataformas WHERE id_usuario  = "+ id_usuario +"";
            String sql4 = "DELETE FROM publicacoes WHERE id_usuario  = "+ id_usuario +"";
            String sql5 = "DELETE FROM seguidores WHERE id_usuario  = "+ id_usuario +"";
            String sql6 = "DELETE FROM seguidores WHERE id_seguidor  = "+ id_usuario +"";
            String sql8 = "DELETE FROM convites WHERE remetente  = "+ id_usuario +"";
            String sql9 = "DELETE FROM convites WHERE destinatario  = "+ id_usuario +"";
            String sql10 = "DELETE FROM mensagens WHERE id_remetente  = "+ id_usuario +"";
            String sql11 = "DELETE FROM mensagens WHERE id_destinatario  = "+ id_usuario +"";
            String sql12 = "DELETE FROM usuario WHERE id_usuario  = " + id_usuario +"";
            
            
            PreparedStatement stmt1 = connection.prepareStatement(sql1);
            PreparedStatement stmt2 = connection.prepareStatement(sql2);
            PreparedStatement stmt3 = connection.prepareStatement(sql3);
            PreparedStatement stmt4 = connection.prepareStatement(sql4);
            PreparedStatement stmt5 = connection.prepareStatement(sql5);
            PreparedStatement stmt6 = connection.prepareStatement(sql6);
            PreparedStatement stmt8 = connection.prepareStatement(sql8);
            PreparedStatement stmt9 = connection.prepareStatement(sql9);
            PreparedStatement stmt10 = connection.prepareStatement(sql10);
            PreparedStatement stmt11 = connection.prepareStatement(sql11);
            PreparedStatement stmt12 = connection.prepareStatement(sql12);
            
            stmt1.execute();
            stmt2.execute();
            stmt3.execute();
            stmt4.execute();
            stmt5.execute();
            stmt6.execute();
            stmt8.execute();
            stmt9.execute();
            stmt10.execute();
            stmt11.execute();
            stmt12.execute();
            
            stmt1.close();
            stmt2.close();
            stmt3.close();
            stmt4.close();
            stmt5.close();
            stmt6.close();
            stmt8.close();
            stmt9.close();
            stmt10.close();
            stmt11.close();
            stmt12.close();
            connection.close();

        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public void alterarEmail(int id_usuario, String novo_email) {
        try {
            String sql = "";
            sql = "UPDATE usuario SET email_usuario = '" + novo_email + "' WHERE id_usuario = " + id_usuario;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();
            connection.close();

        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public void alterarDescricao(int id_usuario, String nova_descricao) {
        try {
            String sql = "";
            sql = "UPDATE usuario SET desc_usuario = '" + nova_descricao + "' WHERE id_usuario = " + id_usuario;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();
            connection.close();

        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public void alterarNome(int id_usuario, String novo_nome) {
        try {
            String sql = "";
            sql = "UPDATE usuario SET nome_usuario = '" + novo_nome + "' WHERE id_usuario = " + id_usuario;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();
            connection.close();

        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public void alterarImagem(int id_usuario, int imagem) {
        try {
            String sql = "";
            sql = "UPDATE usuario SET foto_usuario = " + imagem + " WHERE id_usuario = " + id_usuario;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();
            connection.close();

        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public void alterarSenha(int id_usuario, String senha) {
        try {
            String sql = "";
            sql = "UPDATE usuario SET senha_usuario = '" + senha + "' WHERE id_usuario = " + id_usuario;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();
            connection.close();

        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public void preCadastrar(Usuario objUsuario) {
        try {
            String sql = "";
            sql = "INSERT INTO usuario(nome_usuario,email_usuario,senha_usuario,dataNasc_usuario) VALUES(?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, objUsuario.getNome_usuario());
            stmt.setString(2, objUsuario.getEmail_usuario());
            stmt.setString(3, objUsuario.getSenha_usuario());
            stmt.setString(4, objUsuario.getDataNasc_usuario());

            stmt.execute();
            stmt.close();
            connection.close();

        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public void posCadastrar(Usuario objUsuario, String nome_usuario) {
        try {
            String sql = "";
            sql = "UPDATE usuario SET desc_usuario = ?, foto_usuario = ? WHERE nome_usuario = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, objUsuario.getDesc_usuario());
            stmt.setInt(2, objUsuario.getFoto_usuario());
            stmt.setString(3, objUsuario.getNome_usuario());

            stmt.execute();
            stmt.close();
            connection.close();

        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public String pegarNomeUsuario(int id_usuario) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT nome_usuario FROM usuario WHERE id_usuario=" + id_usuario);
            ResultSet rs = ps.executeQuery();

            rs.next();
            String nome_usuario = rs.getString("nome_usuario");

            ps.close();
            rs.close();

            return nome_usuario;
        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "pegarNomeUsuario():" + e.getMessage());
            return null;
        }
    }
    
    public String pegarNomeUsuarioCC(int id_usuario) { // Pega o nome do usuário e fecha a conexão
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT nome_usuario FROM usuario WHERE id_usuario=" + id_usuario);
            ResultSet rs = ps.executeQuery();

            rs.next();
            String nome_usuario = rs.getString("nome_usuario");

            ps.close();
            rs.close();
            connection.close();

            return nome_usuario;
        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "pegarNomeUsuarioCC():" + e.getMessage());
            return null;
        }
    }
    
    public int pegarIdUsuario(String nome_usuario) {
        try {
            int id_usuario = 0;

            PreparedStatement ps = connection.prepareStatement("SELECT id_usuario FROM usuario WHERE nome_usuario ='" + nome_usuario + "'");
            ResultSet rs = ps.executeQuery();

            rs.next();
            id_usuario = rs.getInt("id_usuario");

            ps.close();
            rs.close();

            return id_usuario;
        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "pegarIdUsuario():" + e.getMessage());
            return 0;
        }
    }
    
    public int pegarIdUsuarioCC(String nome_usuario) {
        try {
            int id_usuario = 0;

            PreparedStatement ps = connection.prepareStatement("SELECT id_usuario FROM usuario WHERE nome_usuario ='" + nome_usuario + "'");
            ResultSet rs = ps.executeQuery();

            rs.next();
            id_usuario = rs.getInt("id_usuario");

            ps.close();
            rs.close();
            connection.close();

            return id_usuario;
        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "pegarIdUsuario():" + e.getMessage());
            return 0;
        }
    }

    public ArrayList facaNovasAmizades(int id_usuario) {

        try {
            ArrayList dado = new ArrayList();

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM usuario WHERE id_usuario NOT IN (" + id_usuario + ", 1, 2) AND id_usuario NOT IN (SELECT id_usuario FROM seguidores WHERE id_seguidor = " + id_usuario + ") ORDER BY RAND() LIMIT 11");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                dado.add(new Object[]{
                    rs.getString("nome_usuario")
                });
            }
            ps.close();
            rs.close();
            connection.close();

            return dado;
        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "facaNovasAmizades():" + e.getMessage());
            return null;
        }
    }

    public ArrayList listarUsuariosQueJogam(int id_jogo) {
        try {
            ArrayList dado = new ArrayList();

            PreparedStatement ps = connection.prepareStatement("SELECT id_usuario FROM jogos_favoritos WHERE id_jogo = " + id_jogo);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                dado.add(new Object[]{
                    pegarNomeUsuario(rs.getInt("id_usuario"))
                });
            }
            ps.close();
            rs.close();
            connection.close();

            return dado;
        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "listarUsuariosQueJogam():" + e.getMessage());
            return null;
        }
    }
}
