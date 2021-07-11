package com.tms.storage.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    protected Connection connection;

    protected DbConnection() {
        try {
            DbConnectionParameter in = new DbConnectionParameter();
            connection = DriverManager.getConnection(in.getUrl(), in.getUser(), in.getPassword());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}