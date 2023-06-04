package com.escola.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.postgresql.PGProperty;

public class ConnectionFactory {
    static String jdbcUrl;
    static String jdbcUser;
    static String jdbcPassword;

    public static Connection getConnection() throws IOException {

        try {

            return DriverManager.getConnection(
                    "jdbc:postgresql://172.107.45.215:32/edu-sistema-falta", "postgres", "devdbhiga");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
