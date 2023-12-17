package org.sematec;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Database {
    private static HikariDataSource dataSource;

    static {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
        String dbHost = resourceBundle.getString("db.host");
        String dbPort = resourceBundle.getString("db.port");
        String dbName = resourceBundle.getString("db.name");
        String dbUsername = resourceBundle.getString("db.username");
        String dbPassword = resourceBundle.getString("db.password");



        String url = "jdbc:mysql://" + dbUsername + ":" + dbPassword + "@" + dbHost + ":" + dbPort + "/" + dbName;

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://" + dbUsername + ":" + dbPassword + "@" + dbHost + ":" + dbPort)) {
            connection.createStatement().execute("CREATE DATABASE IF NOT EXISTS customers_data");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (dataSource == null) {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(url);
            config.setMaximumPoolSize(10);
            config.setAutoCommit(false);
            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepStmtCacheSize", "250");
            config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

            dataSource = new HikariDataSource(config);
//            connection =  dataSource.getConnection();
        }

        try {
            Connection conn = dataSource.getConnection();

            Statement statement = conn.createStatement();
            String createAccountQuery = "CREATE TABLE IF NOT EXISTS accounts (" +
                    "ACCOUNT_NUMBER BIGINT PRIMARY KEY," +
                    "ACCOUNT_OPEN_DATE VARCHAR(255)," +
                    "ACCOUNT_LIMIT DOUBLE," +
                    "ACCOUNT_CUSTOMER_ID BIGINT," +
                    "ACCOUNT_TYPE VARCHAR(20)," +
                    "ACCOUNT_BALANCE DOUBLE)";
            String  createCustomerQuery = "CREATE TABLE IF NOT EXISTS customers (" +
                    "CUSTOMER_ID BIGINT PRIMARY KEY," +
                    "CUSTOMER_NAME VARCHAR(255)," +
                    "CUSTOMER_SURNAME VARCHAR(255)," +
                    "CUSTOMER_ADDRESS VARCHAR(255)," +
                    "CUSTOMER_ZIP_CODE VARCHAR(20)," +
                    "CUSTOMER_NATIONAL_ID VARCHAR(20)," +
                    "CUSTOMER_BIRTH_DATE DATE)";
            statement.execute("CREATE DATABASE IF NOT EXISTS customers_data");
            statement.execute(createAccountQuery);
            statement.execute(createCustomerQuery);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void closeConnection() {
        if (dataSource != null) {
            dataSource.close();
        }
    }

}
