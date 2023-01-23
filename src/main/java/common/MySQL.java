package common;

import java.sql.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class MySQL {
    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/vkdb";
    private static String user;
    private static String password;

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;

    static {
        try {
            while (!login()) {
                System.out.println("login failed");
            }
            // getting Statement object to execute query
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
    }

    static boolean login() {
        System.out.println("enter login: ");
        user = Control.scanner.next();
        System.out.println("enter password: ");
        password = Control.scanner.next();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url, user, password);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            return false;
        }
        return true;
        // opening database connection to MySQL server
    }

    static ResultSet select(String query) {
        ResultSet rs;
        try {
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    public static int exe(String query) {
        try {
            return stmt.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
