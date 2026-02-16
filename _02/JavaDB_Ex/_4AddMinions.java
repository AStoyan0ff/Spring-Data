package _02.JavaDB_Ex;

import java.sql.*;
import java.util.Scanner;

public class _4AddMinions {
    static void main() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Connection connection = DriverManager.getConnection(
            Connect.DatabaseConfiguration.GET_CONNECTION.getJDBC(),
            Connect.DatabaseConfiguration.GET_CONNECTION.getProps()
        );

        String[] minionData = scanner.nextLine().split("\\s+");
        String minionName = minionData[1];
        int minionAge = Integer.parseInt(minionData[2]);
        String townName = minionData[3];

        String[] villainData = scanner.nextLine().split("\\s+");
        String villainName = villainData[1];

        int townID = getTown(connection, townName);
        int villainID = getVillain(connection, villainName);
        int minionID = getMinion(connection, minionName, minionAge, townID);

        uniteMinionWithVillain(connection, minionID, villainID);

        System.out.printf("Successfully added %s to be minion of %s.%n",
            minionName, villainName);
    }

    private static int getTown(Connection connection, String name) throws SQLException {

        PreparedStatement selectStmt = connection.prepareStatement(
            "SELECT t.id " +
                "FROM towns AS t " +
                "WHERE t.name = ?");

        selectStmt.setString(1, name);
        ResultSet rs = selectStmt.executeQuery();

        if (rs.next()) return rs.getInt(1);

        PreparedStatement insertStmt = connection.prepareStatement(
            "INSERT INTO towns(name) " +
                "VALUES (?)",
            Statement.RETURN_GENERATED_KEYS);

        insertStmt.setString(1, name);
        insertStmt.executeUpdate();

        ResultSet generatedKeys = insertStmt.getGeneratedKeys();
        if (!generatedKeys.next()) throw new IllegalStateException("Insert failed");

        System.out.printf("Town %s was added to the database.%n", name);
        return generatedKeys.getInt(1);

    }

    private static int getVillain(Connection connection, String name) throws SQLException {

        PreparedStatement stmt = connection.prepareStatement(
            "SELECT v.id " +
                "FROM villains AS v " +
                "WHERE v.name = ?");

        stmt.setString(1, name);
        ResultSet rs = stmt.executeQuery();

        if (rs.next())
            return rs.getInt(1);

        PreparedStatement instStatement = connection.prepareStatement(
            "INSERT INTO villains (name, evilness_factor) " +
                "VALUES (?, 'evil')",
            Statement.RETURN_GENERATED_KEYS);

        instStatement.setString(1, name);
        instStatement.executeUpdate();

        ResultSet generatedKeys = instStatement.getGeneratedKeys();
        if (!generatedKeys.next()) throw new IllegalStateException("Not access generated keys for Villain");

        System.out.printf("Villain %s was added to the database.%n", name);
        return generatedKeys.getInt(1);
    }

    private static int getMinion(Connection connection, String name, int age, int townID) throws SQLException {

        PreparedStatement insertStmt = connection.prepareStatement(
            "INSERT INTO minions(name, age, town_id) " +
                "VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

        insertStmt.setString(1, name);
        insertStmt.setInt(2, age);
        insertStmt.setInt(3, townID);
        insertStmt.executeUpdate();

        ResultSet generatedKeys = insertStmt.getGeneratedKeys();

        if (!generatedKeys.next()) throw new IllegalStateException(
            "Not access generated keys for Minion");
        return generatedKeys.getInt(1);
    }

    private static void uniteMinionWithVillain(Connection connection, int minionID, int villainID) throws SQLException {

        PreparedStatement stmt = connection.prepareStatement(
            "INSERT INTO minions_villains(minion_id, villain_id) " +
                "VALUES (?, ?)");

        stmt.setInt(1, minionID);
        stmt.setInt(2, villainID);

        stmt.executeUpdate();
    }
}
