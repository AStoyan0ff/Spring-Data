package _01.JavaDB_Lab;

import Enums.SqlQueries;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class PlayGames {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String user = scanner.nextLine();
        user = user.isBlank() ? "root" : user;

        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();

        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);

        Connection connection =
            DriverManager.getConnection("jdbc:mysql://localhost:3306/diablo", props);

        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.println();

        PreparedStatement selectQuery = // Enum Class (CONST)
            connection.prepareStatement(SqlQueries.GET_GAMER_NAME.getQuery());

        selectQuery.setString(1, username);
        ResultSet rs = selectQuery.executeQuery();

        // while (rs.next()) -> Not suitable in this case
        if (!rs.next()) {
            System.out.println("No such user exists");

        } else {

            System.out.println("User: " + rs.getString("user_name"));
            System.out.printf("%s %s has played %d games%n",
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getInt("games_played"));
        }
        connection.close();
    }
}