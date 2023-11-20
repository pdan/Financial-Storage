package org.sematec;

import java.sql.*;
import java.util.function.Predicate;

public class Main {
    static final String JDBC_DRIVER = "org.sqlite.JDBC";

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/Sematec";

        try {
            Class.forName(JDBC_DRIVER);

            try (Connection connection = DriverManager.getConnection(url, "postgres", "postgres")) {
//                Statement createDbStatement = connection.createStatement();
//                createDbStatement.executeUpdate(createDatabaseQuery);

                if (connection != null) {
                    Statement statement = connection.createStatement();
                    statement.executeUpdate("CREATE TABLE IF NOT EXISTS example (id SERIAL PRIMARY KEY, name VARCHAR(255))");

                    // Insert data
                    int id = (int) (Math.random() * 1000);
                    insertData(connection, id, "Cup Do " + id);

                    // Delete data
                    deleteData(connection, "Cup Do");

                    // Query data
                    ResultSet result = statement.executeQuery("SELECT * FROM example");

                    while (result.next()) {
                        String name = result.getString("name");
                        System.out.println("Result:");
                        System.out.println(name);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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