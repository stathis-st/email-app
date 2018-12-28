package com.emailapp.repository;

import com.emailapp.datasource.Database;
import com.emailapp.domain.Entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public interface CrudRepository<T extends Entity> {

    Database database = Database.getInstance();

    String DELETE_TEMPLATE = "DELETE FROM %s WHERE `id` = ?";
    String SELECT_BY_ID_TEMPLATE = "SELECT * FROM %s WHERE `id` = ?";
    String SELECT_ALL_TEMPLATE = "SELECT * FROM %s";

    String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    default DateTimeFormatter getDateTimeFormatter() {
        return DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
    }

    String getTableName();

    String getInsertStatement();

    String getUpdateStatement();

    T extractEntityFromResultSet(ResultSet resultSet) throws SQLException;

    void setInsertPreparedStatement(T entity, PreparedStatement preparedStatement) throws SQLException;

    void setUpdatePreparedStatement(T entity, PreparedStatement preparedStatement) throws SQLException;

    default long save(T entity) {
        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getInsertStatement(), Statement.RETURN_GENERATED_KEYS)) {
            setInsertPreparedStatement(entity, preparedStatement);
            if (preparedStatement.executeUpdate() > 0) {
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        return resultSet.getLong(1);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    default boolean update(T entity) {
        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getUpdateStatement())) {
            setUpdatePreparedStatement(entity, preparedStatement);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    default T getOne(long id) {
        String selectStatement = String.format(SELECT_BY_ID_TEMPLATE, getTableName());
        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectStatement)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.beforeFirst();
                if (resultSet.next()) {
                    return extractEntityFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    default List<T> getAll() {
        List<T> entityList = new ArrayList<>();
        String selectAllStatement = String.format(SELECT_ALL_TEMPLATE, getTableName());
        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectAllStatement);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            resultSet.beforeFirst();
            while (resultSet.next()) {
                entityList.add(extractEntityFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entityList;
    }

    default boolean delete(T entity) {
        String deleteStatement = String.format(DELETE_TEMPLATE, getTableName());
        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteStatement)) {
            preparedStatement.setLong(1, entity.getId());
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
