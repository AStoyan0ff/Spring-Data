package _02.JavaDB_Ex;

import Enums.SqlQueries;

import java.sql.*;
import java.util.Scanner;

public class _3GetMinionsNames {
    static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        int villainID = scanner.nextInt();

        Connection connection = DriverManager.getConnection(
            Connect.DatabaseConfiguration.GET_CONNECTION.getJDBC(),
            Connect.DatabaseConfiguration.GET_CONNECTION.getProps()
        );

        boolean isVillain = printVillain(connection, villainID);

        if (isVillain) printMinions(connection, villainID);

        connection.close();
    }

    private static boolean printVillain(Connection connection, int villainID) throws SQLException {

        PreparedStatement selectQuery =
            connection.prepareStatement(SqlQueries.GET_VILLAIN_NAME_BY_ID.getQuery());

        selectQuery.setInt(1, villainID);
        ResultSet rs = selectQuery.executeQuery();

        if (rs.next()) {
            String name = rs.getString(1);

            System.out.printf("Villain: %s%n", name);
            return true;

        } else {

            System.out.println("Villain does not exist");
            return false;
        }
    }

    private static void printMinions(Connection connection, int villainID) throws SQLException {

        PreparedStatement selectQuery =
            connection.prepareStatement(SqlQueries.GET_MINIONS_BY_VILLAIN_ID.getQuery());

        selectQuery.setInt(1, villainID);

        int idx = 1;
        ResultSet rs = selectQuery.executeQuery();

        while (rs.next()) {

            String name = rs.getString(1); // колкона 1 по idx
            int age = rs.getInt(2); // колона 2 по idx

            System.out.printf("%d. %s %d%n", idx++, name, age);
        }
    }
}