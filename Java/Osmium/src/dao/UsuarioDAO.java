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
    int idade_usuario;
    int foto_usuario;
    int banner_usuario;
    boolean tema_escuro;
    boolean administrador;

    public UsuarioDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void cadastrar(Usuario objUsuario) {
        try {
            String sql = "";
            sql = "INSERT INTO usuario(nome_usuario,email_usuario,senha_usuario,desc_usuario,idade_usuario,foto_usuario,banner_usuario) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, objUsuario.getNome_usuario());
            stmt.setString(2, objUsuario.getEmail_usuario());
            stmt.setString(3, objUsuario.getSenha_usuario());
            stmt.setString(4, objUsuario.getDesc_usuario());
            stmt.setInt(5, objUsuario.getIdade_usuario());
            stmt.setInt(6, objUsuario.getFoto_usuario());
            stmt.setInt(7, objUsuario.getBanner_usuario());

            stmt.execute();
            stmt.close();

        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public boolean isNumeric(String pesquisa) {
        if (pesquisa == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(pesquisa);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public ArrayList buscarUsuario(Usuario objUsuario) {
        
        String variavelTeste = "2"; //Essa variavel será recebida do GUI na hora da pesquisa por um usuario. Mudar o nome futuramente
        boolean isNumeric = isNumeric(variavelTeste);
        try {
            String sql = "";
            if (isNumeric == true) {
                sql = "SELECT * FROM usuario WHERE id_usuario LIKE '%" + variavelTeste + "%' ";

            } else if (isNumeric == false) {
                sql = "SELECT * FROM usuario WHERE nome_usuario LIKE '%" + variavelTeste + "%' ";
            }
            ArrayList dado = new ArrayList();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                dado.add(new Object[]{
                    rs.getString("nome"),});

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
