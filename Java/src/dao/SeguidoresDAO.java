package dao;

import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Seguidores;

public class SeguidoresDAO {

    private Connection connection;

    public SeguidoresDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public String pegarNomeSeguidor(int id_seguidor) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT nome_usuario FROM usuario WHERE id_usuario=" + id_seguidor);
            ResultSet rs = ps.executeQuery();

            rs.next();
            String nomeRemetente = rs.getString("nome_usuario");

            ps.close();
            rs.close();

            return nomeRemetente;
        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "pegarNomeSeguidor():" + e.getMessage());
            return null;
        }
    }

    public ArrayList listarSeguidores(int id_usuario) {
        try {
            ArrayList dado = new ArrayList();

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM seguidores WHERE id_usuario = " + id_usuario);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                dado.add(new Object[]{
                    pegarNomeSeguidor(rs.getInt("id_seguidor"))
                });
            }
            ps.close();
            rs.close();

            return dado;
        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "listarSeguidores():" + e.getMessage());
            return null;
        }
    }
    
    public ArrayList listarSeguidoresRecentes(int id_usuario) {
        try {
            ArrayList dado = new ArrayList();

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM seguidores WHERE id_usuario = " + id_usuario + " ORDER BY id_interacao DESC");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                dado.add(new Object[]{
                    pegarNomeSeguidor(rs.getInt("id_seguidor"))
                });
            }
            ps.close();
            rs.close();
            connection.close();

            return dado;
        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "listarSeguidoresRecentes():" + e.getMessage());
            return null;
        }
    }

    public ArrayList listarSeguidoresMutuos(int id_usuario) {
        try {
            ArrayList dado = new ArrayList();

            PreparedStatement ps = connection.prepareStatement("SELECT id_seguidor FROM seguidores a WHERE id_seguidor IN (SELECT id_usuario FROM seguidores b WHERE id_seguidor = "+ id_usuario +") and id_usuario = " + id_usuario);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                dado.add(new Object[]{
                    pegarNomeSeguidor(rs.getInt("id_seguidor"))
                });
            }
            ps.close();
            rs.close();
            connection.close();

            return dado;
        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "listarSeguidoresMutuos():" + e.getMessage());
            return null;
        }
    }

    public void seguir(Seguidores objSeg) {
        try {
            String sql = "";
            sql = "INSERT INTO seguidores(id_usuario, id_seguidor) VALUES(?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, objSeg.getId_usuario());
            stmt.setInt(2, objSeg.getId_seguidor());

            stmt.execute();
            stmt.close();
            connection.close();

        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public void deixarDeSeguir(Seguidores objSeg) {
        try {
            String sql = "";
            sql = "DELETE FROM seguidores WHERE id_usuario = ? AND id_seguidor = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, objSeg.getId_usuario());
            stmt.setInt(2, objSeg.getId_seguidor());

            stmt.execute();
            stmt.close();
            connection.close();

        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }
}
