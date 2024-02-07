package com.foretruff.jdbc.starter;

import com.foretruff.jdbc.starter.util.ConnectionManager;

import java.sql.SQLException;

public class JdbcRunner {
    public static void main(String[] args) throws SQLException {
        try (var connection = ConnectionManager.open()) {
            System.out.println(connection.getTransactionIsolation());
        }

    }

}
