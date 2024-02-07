package com.foretruff.jdbc.starter;

import com.foretruff.jdbc.starter.util.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcRunner {
    public static void main(String[] args) throws SQLException {
        String sql = """
                SELECT *
                FROM ticket
                """;
        try (var connection = ConnectionManager.open();
             var statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            System.out.println(connection.getSchema());
            System.out.println(connection.getTransactionIsolation());

            var executeResult = statement.executeQuery(sql);
            while (executeResult.next()) {
                System.out.println(executeResult.getLong("id"));
                System.out.println(executeResult.getString("passenger_no"));
                System.out.println(executeResult.getBigDecimal("cost"));
//                executeResult.previous();
//                executeResult.afterLast();
                System.out.println("----------------");
            }
        }
    }

}
