package com.foretruff.jdbc.starter;

import com.foretruff.jdbc.starter.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionRunner {
    public static void main(String[] args) throws SQLException {
        long flightId = 9;
        String deleteFlightSql = " DELETE FROM flight WHERE id = " + flightId;
        String deleteTicketSql = " DELETE FROM ticket WHERE flight_id = " + flightId;

        Connection connection = null;
//        PreparedStatement deleteFlightStatement = null;
//        PreparedStatement deleteTicketStatement = null;
        Statement statement = null;

        try {

            connection = ConnectionManager.get();
            connection.setAutoCommit(false);
//            deleteFlightStatement = connection.prepareStatement(deleteFlightSql);
//            deleteTicketStatement = connection.prepareStatement(deleteTicketSql);
            statement = connection.createStatement();
            statement.addBatch(deleteTicketSql);
            statement.addBatch(deleteFlightSql);


//            deleteFlightStatement.setLong(1, flightId);
//            deleteTicketStatement.setLong(1, flightId);

//            var deletedTicketResult = deleteTicketStatement.executeUpdate();
//            var deletedFlightResult = deleteFlightStatement.executeUpdate();

            var ints = statement.executeBatch();

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
            if (statement != null) {
                statement.close();
            }
//            if (deleteTicketStatement != null) {
//                deleteTicketStatement.close();
//            }
        }
    }
}
