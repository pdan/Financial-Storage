package org.sematec;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Database {
    private static DataSource dataSource;

    public static DataSource getDatasource() throws SQLException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
        String dbHost = resourceBundle.getString("db.host");
        String dbPort = resourceBundle.getString("db.port");
        String dbName = resourceBundle.getString("db.name");


        String url = "jdbc:postgresql://" + dbHost + ":" + dbPort + "/" + dbName;

        if (dataSource == null) {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(url);
            config.setMaximumPoolSize(10);
            config.setAutoCommit(false);
            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepStmtCacheSize", "250");
            config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

            dataSource = new HikariDataSource(config);
        }

        return dataSource;
    }

}
