package _02.JavaDB_Ex;

import java.sql.*;
import java.util.Scanner;

public class _8IncreaseMinionsAge {

    private static final String JDBC = "jdbc:mysql://localhost:3306/minions_db";
    private static final String USER = "root";
    private static final String PASS = "07808";

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        String[] ids = scanner.nextLine().split("\\s+");

        Connection connection = DriverManager.getConnection(JDBC, USER, PASS);

        PreparedStatement updateStmt =  connection.prepareStatement(
                """
                UPDATE minions
                SET age = age + 1, name = LOWER(name)
                WHERE id = ?
                """
        );

        for(String id : ids) {
            updateStmt.setString(1, String.valueOf(Integer.parseInt(id)));
            updateStmt.executeUpdate();
        }

        PreparedStatement selectStmt = connection.prepareStatement(
                """
                SELECT name, age
                FROM minions
                """
        );

        ResultSet resultSet = selectStmt.executeQuery();

        while(resultSet.next()) {

            System.out.println(
                resultSet.getString("name") + " " +
                resultSet.getInt("age"));
        }
        connection.close();
    }
}
