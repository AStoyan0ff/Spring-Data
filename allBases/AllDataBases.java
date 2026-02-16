package allBases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// Connection connection =
// DatabaseConnection.getConnection("soft_uni_database");

public final class AllDataBases {

    private static final String HOST = "localhost";
    private static final String PORT = "3306";

    private static final String USER = "root";
    private static final String PASSWORD = "07808";

    private AllDataBases() {}

    public static Connection getConnection(String databaseName) throws SQLException {

        String jdbc = String.format(
                "jdbc:mysql://%s:%s/%s",
                HOST, PORT, databaseName
        );

        Properties properties = new Properties();
        properties.setProperty("user", USER);
        properties.setProperty("password", PASSWORD);

        return DriverManager.getConnection(jdbc, properties);
    }
}
