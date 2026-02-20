package _00.ConnentedDataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    private static final String HOST = "jdbc:mysql://localhost:3306/minions_db";
    private static final String USER = "root";
    private static final String PASSWORD = "07808";

    private DatabaseConnection() {}

    public static Connection getConnection() throws SQLException {

        Properties props = new Properties();
        props.setProperty("user", USER);
        props.setProperty("password", PASSWORD);

        return DriverManager.getConnection(HOST, props);
    }

    // Connection connection = DatabaseConnection.getConnection();
}
