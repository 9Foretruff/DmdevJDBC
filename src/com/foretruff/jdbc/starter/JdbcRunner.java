package com.foretruff.jdbc.starter;

import com.foretruff.jdbc.starter.util.ConnectionManager;

import java.sql.SQLException;
import java.sql.Statement;

public class JdbcRunner {
    public static void main(String[] args) throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS info
                (
                    id SERIAL PRIMARY KEY ,
                    data TEXT NOT NULL
                );
                """;
        try (var connection = ConnectionManager.open();
             var statement = connection.createStatement()) {

            System.out.println(connection.getSchema());
            System.out.println(connection.getTransactionIsolation());

            var executeResult = statement.execute(sql);

            System.out.println(executeResult);
        }
    }

}
