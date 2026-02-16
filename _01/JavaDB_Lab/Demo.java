package _01.JavaDB_Lab;

import db.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Demo {
    static void main() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Connection connection = DatabaseConnection.getConnection();

        PreparedStatement stmt = connection.prepareStatement(
            """
                SELECT *
                FROM employees
                LIMIT 10
                """
        );

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            String firstName = rs.getString("first_name");
            String lastname = rs.getString("last_name");
            double salary = rs.getDouble("salary");

            System.out.printf("%s %s %s%n", firstName, lastname, salary);
        }
        connection.close();
    }
}
