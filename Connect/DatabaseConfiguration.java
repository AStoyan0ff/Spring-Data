package Connect;
import java.util.Properties;

//Connection connection = DriverManager.getConnection(
//        Connect.DatabaseConfiguration.GET_CONNECTION.getJDBC(),
//        Connect.DatabaseConfiguration.GET_CONNECTION.getProps()
//);

public enum DatabaseConfiguration {

    GET_CONNECTION(
            "jdbc:mysql://localhost:3306/minions_db",
            "root",
            "07808"
    );

    private final String jdbc;
    private final String user;
    private final String password;

    DatabaseConfiguration(String jdbc, String user, String password) {
        this.jdbc = jdbc;
        this.user = user;
        this.password = password;
    }

    public String getJDBC() {
        return jdbc;
    }

    public Properties getProps() {

        Properties properties = new Properties();
        properties.setProperty("user", user);
        properties.setProperty("password", password);

        return properties;
    }
}
