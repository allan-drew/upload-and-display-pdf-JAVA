package dev.aadc.connection;

import dev.aadc.connection.ConnectionFactory;

import java.sql.SQLException;

public class ConnectionTest {

    public static void main(String[] args) {

        try {
            System.out.println(ConnectionFactory.connection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
