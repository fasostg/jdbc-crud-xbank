package br.com.xbank;

import com.zaxxer.hikari.*;
import java.sql.SQLException;
import java.sql.Connection;

public class ConnectionFactory {
    public Connection getConnection() {
        try {
            Connection conn = createDataSource().getConnection();
            return conn;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private HikariDataSource createDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/xbank");
        config.setUsername("root");
        config.setPassword("Stoch123");

        return new HikariDataSource(config);
    }

}
