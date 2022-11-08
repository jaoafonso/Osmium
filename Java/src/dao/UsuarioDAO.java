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
    
    public void excluirUsuario(int id_usuario) {
        try {
            String sql = "";
            sql = "DELETE FROM usuario WHERE id_usuario = " + id_usuario;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();

        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }
    
    public void alterarEmail(int id_usuario, String novo_email) {
        try {
            String sql = "";
            sql = "UPDATE usuario SET email_usuario = "+ novo_email +" WHERE id_usuario = "+ id_usuario;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();

        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }
    
    public void alterarDescricao(int id_usuario, String nova_descricao) {
        try {
            String sql = "";
            sql = "UPDATE usuario SET desc_usuario = "+ nova_descricao +" WHERE id_usuario = "+ id_usuario;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();

        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }
    
    public void alterarNome(int id_usuario, String novo_nome) {
        try {
            String sql = "";
            sql = "UPDATE usuario SET nome_usuario = "+ novo_nome +" WHERE id_usuario = "+ id_usuario;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();

        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }
    
    public void alterarImagem(int id_usuario, int imagem) {
        try {
            String sql = "";
            sql = "UPDATE usuario SET foto_usuario = "+ imagem +" WHERE id_usuario = "+ id_usuario;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();

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

        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }
    
     public String pegarNomeUsuario(int id_usuario) {
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
            JOptionPane.showMessageDialog(null, "pegarNomeUsuario():" + e.getMessage());
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
