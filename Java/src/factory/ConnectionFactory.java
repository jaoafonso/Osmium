package factory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    
    public Connection getConnection() {
        try {
            //return DriverManager.getConnection("jdbc:mysql://172.31.0.131:3306/osmium?autoReconnect=true&useSSL=false", "root", "senai");
            return DriverManager.getConnection("jdbc:mysql://localhost/osmium?autoReconnect=true&useSSL=false", "root", "");
        }
        catch(SQLException excecao) {
            throw new RuntimeException(excecao); 
        }
    }
}
