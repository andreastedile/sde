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
    private String databasePassword;
    @Value("${DB_USER}")
    private String databaseUser;
    @Value("${DB_URL}")
    private String dbURL;

    private HikariConfig config = new HikariConfig();
    private HikariDataSource ds;

    static {
    }
    @PostConstruct
    public void init() {
        System.out.println(dbURL+databaseUser+databasePassword);
        config.setJdbcUrl(dbURL);
        config.setUsername(databaseUser);
        config.setPassword(databasePassword);
        ds = new HikariDataSource(config);
    }
    private DataSource() {

    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}