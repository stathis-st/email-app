package com.emailapp.repository;

import com.emailapp.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepositoryImpl implements UserRepository {

    private static final String TABLE_NAME = "`users`";
    private static final String COLUMN_USERS_USERNAME = "`username`";
    private static final String COLUMN_USERS_PASSWORD = "`password`";
    private static final String COLUMN_USERS_LASTNAME = "`lastname`";
    private static final String COLUMN_USERS_FIRSTNAME = "`firstname`";

    private static final String INSERT_STATEMENT = String.format("INSERT INTO %s (%s, %s, %s, %s) VALUES(?, ?, ?, ?);",
            TABLE_NAME, COLUMN_USERS_USERNAME, COLUMN_USERS_PASSWORD, COLUMN_USERS_LASTNAME, COLUMN_USERS_FIRSTNAME);

    private static final String UPDATE_STATEMENT = String.format("UPDATE %s SET %s = ?, %s = ?, %s = ?, %s = ? WHERE `id` = ?",
            TABLE_NAME, COLUMN_USERS_USERNAME, COLUMN_USERS_PASSWORD, COLUMN_USERS_LASTNAME, COLUMN_USERS_FIRSTNAME);

    private static final String SELECT_USER_BY_USERNAME_AND_PASSWORD = String.format("SELECT * FROM %s WHERE %s = ? AND %s = ?;",
            TABLE_NAME, COLUMN_USERS_USERNAME, COLUMN_USERS_PASSWORD);


    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public String getInsertStatement() {
        return INSERT_STATEMENT;
    }

    @Override
    public String getUpdateStatement() {
        return UPDATE_STATEMENT;
    }

    @Override
    public User extractEntityFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong(1));
        user.setUsername(resultSet.getString(2));
        user.setPassword(resultSet.getString(3));
        user.setLastName(resultSet.getString(4));
        user.setFirstName(resultSet.getString(5));
        return user;
    }

    @Override
    public void setInsertPreparedStatement(User user, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getLastName());
        preparedStatement.setString(4, user.getFirstName());
    }

    @Override
    public void setUpdatePreparedStatement(User user, PreparedStatement preparedStatement) throws SQLException {
        setInsertPreparedStatement(user, preparedStatement);
        preparedStatement.setLong(5, user.getId());
    }

    @Override
    public User getUserByUsernameAndPassword(String username, String password) {


            try (Connection connection = database.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_USERNAME_AND_PASSWORD)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

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

}
