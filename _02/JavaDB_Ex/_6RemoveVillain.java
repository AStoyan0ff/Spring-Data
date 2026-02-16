package _02.JavaDB_Ex;

import java.sql.*;
import java.util.Scanner;

public class _6RemoveVillain {
    public static void main(String[] args) throws SQLException {
        Scanner input = new Scanner(System.in);

        int villainID = Integer.parseInt(input.nextLine());

        Connection connection =
        DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/minions_db",
            "root",
            "07808");

        connection.setAutoCommit(false);

        PreparedStatement selectStmt = connection.prepareStatement(
                """
                SELECT name
                FROM villains
                WHERE id = ?
                """
        );

        selectStmt.setInt(1, villainID);

        ResultSet rs = selectStmt.executeQuery();

        if(!rs.next()) {
            System.out.println("No such villain was found!");

            connection.rollback();
            return;
        }

        String name = rs.getString("name");

        PreparedStatement releaseStmt = connection.prepareStatement(
                """
                DELETE FROM minions_villains
                WHERE villain_id = ?
                """
        );

        releaseStmt.setInt(1, villainID);

        int result = releaseStmt.executeUpdate();

        PreparedStatement deleteStmt = connection.prepareStatement(
                """
                DELETE FROM villains
                WHERE id = ?
                """
        );

        deleteStmt.setInt(1, villainID);
        deleteStmt.executeUpdate();

        connection.commit();

        System.out.println(name + " was deleted!");
        System.out.println(result + " minions released!");

        connection.close();
    }
}
