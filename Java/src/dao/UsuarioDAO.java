package dao;

import factory.ConnectionFactory;
import modelo.Usuario;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class UsuarioDAO {

    private Connection connection;
    int id_usuario;
    String nome_usuario;
    String email_usuario;
    String senha_usuario;
    String desc_usuario;
    String dataNasc_usuario;
    int foto_usuario;
    int banner_usuario;
    boolean administrador;

    public UsuarioDAO() {
        this.connection = new ConnectionFactory().getConnection();
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
            sql = "UPDATE usuario SET desc_usuario = ?, foto_usuario = ?, banner_usuario = ? WHERE nome_usuario = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, objUsuario.getDesc_usuario());
            stmt.setInt(2, objUsuario.getFoto_usuario());
            stmt.setInt(3, objUsuario.getBanner_usuario());
            stmt.setString(4, nome_usuario);

            stmt.execute();
            stmt.close();

        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }
}
