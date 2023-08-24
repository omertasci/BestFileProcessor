package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    static Connection connection;
    public static Connection getConection() throws SQLException {

        if(connection == null || connection.isClosed()){
            connection = createConnection();
        }
        return connection;
    }

    public static Connection createConnection() throws SQLException {
        // MySQL bağlantısı oluştur
        String url = "jdbc:mysql://localhost:3306/fileprocessordb";
        String username = "root";
        String password = "root";
        return  DriverManager.getConnection(url, username, password);
    }
}
