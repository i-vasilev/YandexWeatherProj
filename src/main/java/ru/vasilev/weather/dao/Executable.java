package ru.vasilev.weather.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface Executable<R> {
    /**
     * Action to execute using connection.
     *
     * @param connection SQL connection to database
     * @return Any result of action.
     * @throws SQLException SQL Exception.
     */
    R execute(Connection connection) throws SQLException;
}
