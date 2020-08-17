package ru.vasilev.weather.dao;

import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Objects;

public class Dao {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String dbUsername;
    @Value("${spring.datasource.password}")
    private String dbPassword;

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url, dbUsername, dbPassword);
    }

    /**
     * Connects and execute method, implemented in {@param lambda}.
     *
     * @param executable implementing {@link Executable} instance.
     * @param <T>        Type of returning.
     * @return result of executing {@param executable}.
     * @throws SQLException   SQL Exception.
     * @throws ParseException Parse data exception.
     */
    protected <T> T connectAndExecute(Executable<T> executable)
            throws SQLException {
        final T result;
        final Connection connection = connect();
        if (!connection.isClosed()) {
            result = executable.execute(connection);
        } else {
            result = null;
        }
        disconnect(connection);
        return result;
    }

    /**
     * Disconnects from database.
     *
     * @param connection connection for disconnecting.
     */
    private void disconnect(Connection connection) {
        Objects.requireNonNull(connection);
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
