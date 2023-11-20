package org.sematec;

import java.sql.*;
import java.util.function.Predicate;

public class Main {
    static final String JDBC_DRIVER = "org.sqlite.JDBC";

    public static void main(String[] args) {
        String url = "jdbc:sqlite:src/main/database.db";

        try {
            Class.forName(JDBC_DRIVER);

            try (Connection connection = DriverManager.getConnection(url);
                 PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS example (id INTEGER PRIMARY KEY, name TEXT)")) {
                preparedStatement.executeUpdate();
                if (connection != null) {
                    Statement smt = connection.createStatement();
//                    insertData(connection, 16, "Cup Do 16");
                    deleteData(connection, "Cup Do");
                    ResultSet result = smt.executeQuery("SELECT * FROM example");

                    do {
                        String name = result.getString(2);
                        System.out.println("Result:");
                        System.out.println(name);
                    } while (result.next());
                }
            } catch (SQLException e) {
                System.out.println(e);
            }


        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } finally {

        }
//        String createTableSQL = "CREATE TABLE IF NOT EXISTS employees ("
//                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
//                + "name TEXT NOT NULL,"
//                + "age INTEGER);";
//
//        String url = "jdbc:sqlite:src/main/database.db";
//        try (Connection connection = DriverManager.getConnection(url);
//             PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS example (id INTEGER PRIMARY KEY, name TEXT)")) {
//
//            // Create the table if it doesn't exist
//            preparedStatement.executeUpdate();
//
//            // Insert data into the table
////            insertData(connection, 1, "John Doe");
//            insertData(connection, 4, "Main Doe2");
//
//        } catch (SQLException e) {
////            e.printStackTrace();
//        }

    }

    private static void insertData(Connection connection, int id, String name) throws SQLException {
        String insertSQL = "INSERT INTO example (id, name) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.executeUpdate();
        }
    }

    private static void deleteData(Connection connection, String name) throws  SQLException {
        String deleteSQL = "DELETE FROM example WHERE name=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        }
    }

    public static Predicate<Customer> filterLoyaltyRate() {
        return c -> c.getLoyaltyRate() > 5;
    }
}