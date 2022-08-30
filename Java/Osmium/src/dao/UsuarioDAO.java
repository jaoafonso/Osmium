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
    
    public void posCadastrar(Usuario objUsuario) {
        try {
            String sql = "";
            sql = "INSERT INTO usuario(desc_usuario,foto_usuario,banner_usuario) VALUES(?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, objUsuario.getDesc_usuario());
            stmt.setInt(2, objUsuario.getFoto_usuario());
            stmt.setInt(3, objUsuario.getBanner_usuario());

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
        
        
        try { //Será verificado se a variavel de pesquisa é uma String numérica ou não, para a busca por ID ou Nome
            String sql = "";
            if (isNumeric(String.valueOf(objUsuario)) == true) {
                sql = "SELECT * FROM usuario WHERE id_usuario LIKE " + objUsuario;

            } else if (isNumeric(String.valueOf(objUsuario)) == false) {
                sql = "SELECT * FROM usuario WHERE nome_usuario LIKE '%" + objUsuario + "%' ";
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
    
    public void loginUsuario(Usuario objUsuario) {
            
    }
}
