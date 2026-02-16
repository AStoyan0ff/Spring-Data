package _02.JavaDB_Ex;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class _7PrintAllMinionsNames {

    private static final String JDBC = "jdbc:mysql://localhost:3306/minions_db";
    private static final String USER = "root";
    private static final String PASS = "07808";

    static void main() throws SQLException {

        Connection connection = DriverManager.getConnection(JDBC, USER, PASS);

        PreparedStatement stmt = connection.prepareStatement(
                """
                SELECT name
                FROM minions
                """
        );

        ResultSet resultSet = stmt.executeQuery();
        List<String> nameList = new ArrayList<>();

        while (resultSet.next()) {
            nameList.add(resultSet.getString("name"));
        }

        int start = 0;
        int end = nameList.size() - 1;

        while (start <= end) {

            if(start == end) {
                System.out.println(nameList.get(start));

            } else {
                System.out.println(nameList.get(start));
                System.out.println(nameList.get(end));
            }
            start++;
            end --;
        }
        connection.close();
    }
}
