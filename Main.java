import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        String HOST = "jdbc:mysql://localhost:3306/soft_uni_database";
        String USER = "root";
        String PASS = "07808";

        Connection connection =
            DriverManager.getConnection(HOST, USER, PASS);


        PreparedStatement stmtQuery = connection.prepareStatement(

            """
                SELECT employee_id, first_name, last_name
                FROM employees
                WHERE salary > ?;
                """
        );

        double salary = Double.parseDouble(scanner.nextLine());
        stmtQuery.setDouble(1, salary);

        ResultSet rs = stmtQuery.executeQuery();

        while (rs.next()) {

            System.out.println(

                rs.getString("employee_id") + ". " +
                    rs.getString("first_name") + " " +
                    rs.getString("last_name"));
        }

        connection.close();
    }
}
