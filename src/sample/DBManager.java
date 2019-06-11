package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.sql.DataSource;


public class DBManager {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }


    public DBManager() throws SQLException{
        this.connect();
    }


    private void connect() throws SQLException{
        try {
            String url = "jdbc:mysql://localhost/bdBoletaje";
            connection = DriverManager.getConnection(url, "root", "");
            System.out.println("Conexion exitosa");
        } catch (SQLException e) {
            connection = null;
            e.printStackTrace();
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

}
