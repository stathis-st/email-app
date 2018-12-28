package com.emailapp.repository;

import com.emailapp.domain.UserMessage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMessageRepository implements CrudRepository<UserMessage> {

    private static final String TABLE_NAME = "`users_messages`";

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public String getInsertStatement() {
        return null;
    }

    @Override
    public String getUpdateStatement() {
        return null;
    }

    @Override
    public UserMessage extractEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public void setInsertPreparedStatement(UserMessage entity, PreparedStatement preparedStatement) throws SQLException {

    }

    @Override
    public void setUpdatePreparedStatement(UserMessage entity, PreparedStatement preparedStatement) throws SQLException {

    }
}
