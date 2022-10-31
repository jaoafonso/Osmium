package dao;

import factory.ConnectionFactory;
import modelo.Mensagens;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class MensagensDAO {
    
    Connection connection;

    public MensagensDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public void enviarMensagem(Mensagens objMsg) {
        try {
            String sql = "";
            sql = "INSERT INTO mensagens(id_remetente, id_destinatario, mensagem) VALUES(?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, objMsg.getId_remetente());
            stmt.setInt(2, objMsg.getId_destinatario());
            stmt.setString(3, objMsg.getMensagem());

            stmt.execute();
            stmt.close();

        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }
}
