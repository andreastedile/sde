package it.unitn.sde.config;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.PostConstruct;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DataSource {

    @Value("${DB_PASSWORD}")
    private String password;
    @Value("${DB_USER}")
    private String user;
    @Value("${DB_URL}")
    private String jdbcUrl;

    private HikariConfig config;
    private HikariDataSource ds;

    @PostConstruct
    public void init() {
        System.out.println("Connected to " + jdbcUrl + " as " + user);

        config = new HikariConfig();
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(user);
        config.setPassword(password);

        ds = new HikariDataSource(config);
    }

    private DataSource() {
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}