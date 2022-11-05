package dao;

import factory.ConnectionFactory;
import modelo.Publicacoes;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class PublicacoesDAO {
    
    Connection connection;

    public PublicacoesDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public void enviarPublicacao(Publicacoes objPublicacao) {
        try {
            String sql = "";
            sql = "INSERT INTO publicacoes(id_usuario, assunto, titulo, descricao) VALUES(?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, objPublicacao.getId_usuario());
            stmt.setString(2, objPublicacao.getAssunto());
            stmt.setString(3, objPublicacao.getTitulo());
            stmt.setString(4, objPublicacao.getDescricao());

            stmt.execute();
            stmt.close();

        } catch (SQLException u) {
            throw new RuntimeException(u);
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
    
    public ArrayList listarPublicacoes(int id_usuario) {
        try {
            ArrayList dado = new ArrayList();

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM publicacoes WHERE id_usuario IN (SELECT id_usuario FROM seguidores where id_seguidor= "+ id_usuario +")");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                dado.add(new Object[]{
                    rs.getString("assunto"),
                    nomeUsuario(rs.getInt("id_usuario")),
                    rs.getString("titulo")
                });

            }
            ps.close();
            rs.close();
            connection.close();

            return dado;
        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "listarPublicacoes():" + e.getMessage());
            return null;
        }
    }
}
