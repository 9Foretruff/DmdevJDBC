package com.foretruff.jdbc.starter.dao;

import com.foretruff.jdbc.starter.entity.FlightEntity;
import com.foretruff.jdbc.starter.exception.DaoException;
import com.foretruff.jdbc.starter.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class FlightDao implements Dao<Long, FlightEntity> {
    private static final FlightDao INSTANCE = new FlightDao();
    private static final String FIND_BY_ID_SQL = """
                SELECT
                id,
                flight_no,
                departure_date,
                departure_airport_code,
                arrival_date,
                arrival_airport_code,
                aircraft_id,
                status
                FROM flight
                WHERE id = ?
            """;

    private FlightDao() {
    }

    public static FlightDao getInstance() {
        return INSTANCE;
    }


    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public FlightEntity save(FlightEntity entity) {
        return null;
    }

    @Override
    public void update(FlightEntity entity) {

    }

    @Override
    public List<FlightEntity> findAll() {
        return null;
    }

    @Override
    public Optional<FlightEntity> findById(Long id) {
        try (var connection = ConnectionManager.get()) {
            return findById(id, connection);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Optional<FlightEntity> findById(Long id, Connection connection) {
        try (var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);

            var resultSet = preparedStatement.executeQuery();
            FlightEntity flightEntity = null;
            if (resultSet.next()) {
                flightEntity = buildFlight(resultSet);
            }

            return Optional.ofNullable(flightEntity);

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private FlightEntity buildFlight(ResultSet resultSet) throws SQLException {
        return new FlightEntity(
                resultSet.getLong("id"),
                resultSet.getString("flight_no"),
                resultSet.getTimestamp("departure_date").toLocalDateTime(),
                resultSet.getString("departure_airport_code"),
                resultSet.getTimestamp("arrival_date").toLocalDateTime(),
                resultSet.getString("arrival_airport_code"),
                resultSet.getInt("aircraft_id"),
                resultSet.getString("status")
        );
    }
}
