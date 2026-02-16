package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// Connection connection = DatabaseConnection.getConnection();

public final class DatabaseConnection {

    private static final String JDBC_URL =
        "jdbc:mysql://localhost:3306/soft_uni_database";

        private static final String USER = "root";
        private static final String PASSWORD = "07808";

        private DatabaseConnection() {}

    public static Connection getConnection() throws SQLException {

        Properties properties = new Properties();
            properties.setProperty("user", USER);
            properties.setProperty("password", PASSWORD);

            return DriverManager.getConnection(JDBC_URL, properties);
    }
}
