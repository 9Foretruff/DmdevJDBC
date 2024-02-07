package com.foretruff.jdbc.starter;

import com.foretruff.jdbc.starter.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionRunner {
    public static void main(String[] args) throws SQLException {
        long flightId = 9;
        String deleteFlightSql = """
                DELETE
                FROM flight
                WHERE id = ?
                """;
        String deleteTicketSql = """
                DELETE
                FROM ticket
                WHERE flight_id = ?
                """;

        Connection connection = null;
        PreparedStatement deleteFlightStatement = null;
        PreparedStatement deleteTicketStatement = null;

        try {

            connection = ConnectionManager.open();
            deleteFlightStatement = connection.prepareStatement(deleteFlightSql);
            deleteTicketStatement = connection.prepareStatement(deleteTicketSql);

            connection.setAutoCommit(false);

            deleteFlightStatement.setLong(1, flightId);
            deleteTicketStatement.setLong(1, flightId);

            deleteTicketStatement.executeUpdate();
            deleteFlightStatement.executeUpdate();

            connection.commit();
        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (deleteFlightStatement != null) {
                deleteFlightStatement.close();
            }
            if (deleteTicketStatement != null) {
                deleteTicketStatement.close();
            }
        }
    }
}
