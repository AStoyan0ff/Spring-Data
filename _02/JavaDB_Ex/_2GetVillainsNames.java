package _02.JavaDB_Ex;

import java.sql.*;

public class _2GetVillainsNames {
    static void main(String[] args) throws SQLException {

        Connection connection =
        DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/minions_db",
                "root",
                "07808");

        PreparedStatement stmt =
            connection.prepareStatement(getVillain);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            String name = rs.getString(1);
            int count = rs.getInt(2);

            System.out.printf("%s %d%n", name, count);
        }
        connection.close();
    }

    private static final String getVillain =
        """
            SELECT v.name,
                COUNT(*) AS 'Count'
            FROM villains v
            JOIN minions_villains mv
                ON v.id = mv.villain_id
            GROUP BY v.id
            HAVING Count > 15
            ORDER BY Count DESC;
        """;

}
