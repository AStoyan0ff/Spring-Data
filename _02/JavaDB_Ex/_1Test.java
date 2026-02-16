package _02.JavaDB_Ex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class _1Test {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        String HOST = "jdbc:mysql://localhost:3306/...";
        String USER = "root";
        String PASS = "pass";

        Connection connection =
            DriverManager.getConnection(HOST, USER, PASS);


    }
}
