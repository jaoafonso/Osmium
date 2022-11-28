package factory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    
    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost/osmium?autoReconnect=true&useSSL=false&maxReconnects=1500", "root", "");
        }
        catch(SQLException excecao) {
            throw new RuntimeException(excecao); 
        }
    }
}
