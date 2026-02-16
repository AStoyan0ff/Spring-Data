package _02.JavaDB_Ex;

import java.sql.*;
import java.util.Scanner;

public class _9IncreaseProcedure {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        int minionId = Integer.parseInt(scanner.nextLine());

        Connection connection =
        DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/minions_db",
                "root",
                "07808");

        CallableStatement callableStatement =
            connection.prepareCall("CALL usp_get_older(?)");

        callableStatement.setInt(1, minionId);
        callableStatement.execute();

        PreparedStatement selectStmt = connection.prepareStatement(
            """
            SELECT name, age
            FROM minions
            WHERE id = ?
            """
    );
        selectStmt.setInt(1, minionId);

        ResultSet rs = selectStmt.executeQuery();

        while (rs.next()) {

            System.out.println(
                rs.getString("name") + " " +
                rs.getInt("age"));
        }
        selectStmt.close();
        callableStatement.close();
    }
}
