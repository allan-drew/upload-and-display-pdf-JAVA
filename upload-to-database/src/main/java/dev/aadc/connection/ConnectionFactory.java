package dev.aadc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection connection() throws SQLException {

        String driver = "com.mysql.cj.jdbc.Driver";

        String url = "jdbc:mysql://localhost:3306/contratosArquivos";
        String username = "root";
        String password = "root";

        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
