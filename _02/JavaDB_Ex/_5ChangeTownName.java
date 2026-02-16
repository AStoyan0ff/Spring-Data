package _02.JavaDB_Ex;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class _5ChangeTownName {
    static void main() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        String countryName = scanner.nextLine();

        String JDBC = "jdbc:mysql://localhost:3306/minions_db";
        String username = "root";
        String password = "07808";

        Properties prop = new Properties();
        prop.setProperty("user", username);
        prop.setProperty("password", password);

        Connection connection = DriverManager.getConnection(JDBC, prop);

        PreparedStatement updateQuery = connection.prepareStatement(
            """
                UPDATE `towns`
                SET name = UPPER(name)
                WHERE country = ?
                """
        );

        updateQuery.setString(1, countryName);
        int rowsAffected = updateQuery.executeUpdate();

        if (rowsAffected == 0) {
            System.out.println("No towns name ware affected!");
            return;
        }

        PreparedStatement selectQuery = connection.prepareStatement(
            """
                SELECT *
                FROM `towns`
                WHERE country = ?
                """
        );

        selectQuery.setString(1, countryName);

        ResultSet rs = selectQuery.executeQuery();
        List<String> townsList = new ArrayList<>();

        while (rs.next()) {
            townsList.add(rs.getString("name"));
        }

        System.out.printf("%d town names were affected.%n", rowsAffected);
        System.out.println(townsList);

        connection.close();
    }
}
