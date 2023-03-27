package com.escola.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    static String jdbcUrl;
    static String jdbcUser;
    static String jdbcPassword;

    public static Connection getConnection() throws IOException {

        try {
            String resourceName = "application.properties"; // could also be a constant
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            Properties props = new Properties();
            try (InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
                props.load(resourceStream);
                jdbcUrl = props.getProperty("spring.datasource.url");
                jdbcUser = props.getProperty("spring.datasource.user");
                jdbcPassword = props.getProperty("spring.datasource.password");

                // get the property value and print it out

                return DriverManager.getConnection(
                        jdbcUrl, jdbcUser, jdbcPassword);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
