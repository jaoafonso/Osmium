package dao;

import factory.ConnectionFactory;
import modelo.Plataformas;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class PlataformasDAO {
    
    private Connection connection;

    public PlataformasDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public ArrayList listarPlataformas(int id_usuario) {
        try {
            ArrayList dado = new ArrayList();

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM plataformas WHERE id_usuario = " + id_usuario);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                
                if (rs.getBoolean("pc") == true) {dado.add(new Object[]{"PC"});}
                if (rs.getBoolean("console") == true) {dado.add(new Object[]{"Console"});}
                if (rs.getBoolean("mobile") == true) {dado.add(new Object[]{"Mobile"});}

            }
            ps.close();
            rs.close();
            connection.close();

            return dado;
        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "listarPlataformas():" + e.getMessage());
            return null;
        }
    }
    
    public void cadastrarPlataformas(Plataformas objPlataforma) {
        try {
            String sql = "";
            sql = "INSERT INTO plataformas(id_usuario, pc, console, mobile) VALUES(?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, objPlataforma.getId_usuario());
            stmt.setBoolean(2, objPlataforma.isPc());
            stmt.setBoolean(3, objPlataforma.isConsole());
            stmt.setBoolean(4, objPlataforma.isMobile());

            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "cadastrarPlataformas():" + e.getMessage());
        }
    }
    
    public void atualizarPlataformas(Plataformas objPlataforma) {
        
    }
}