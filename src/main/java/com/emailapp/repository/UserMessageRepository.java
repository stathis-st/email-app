package com.emailapp.repository;

import com.emailapp.domain.UserMessage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class UserMessageRepository implements CrudRepository<UserMessage> {

    private static final String TABLE_NAME = "`users_messages`";
    private static final String COLUMN_USER_MESSAGES_MESSAGE_ID = "`message_id`";
    private static final String COLUMN_USER_MESSAGE_USER_ID = "`user_id`";
    private static final String COLUMN_USER_MESSAGE_MESSAGE_TYPE = "`message_type`";

    private static final String INSERT_STATEMENT = String.format("INSERT INTO %s (%s, %s, %s) VALUES(?, ?, ?)",
            TABLE_NAME, COLUMN_USER_MESSAGES_MESSAGE_ID, COLUMN_USER_MESSAGE_USER_ID, COLUMN_USER_MESSAGE_MESSAGE_TYPE);

    private static final String UPDATE_STATEMENT = String.format("UPDATE %s SET %s = ?, %s = ?, %s = ? WHERE `id` = ?",
            TABLE_NAME, COLUMN_USER_MESSAGES_MESSAGE_ID, COLUMN_USER_MESSAGE_USER_ID, COLUMN_USER_MESSAGE_MESSAGE_TYPE);

    private static final String SELECT_USER_MESSAGES_BY_MESSAGE_ID = String.format("SELECT * FROM %s WHERE %s = ?", TABLE_NAME, COLUMN_USER_MESSAGES_MESSAGE_ID);

    private static UserMessageRepository userMessageRepositoryInstance;

    private UserMessageRepository() {
    }

    public static UserMessageRepository getInstance() {
        if (userMessageRepositoryInstance == null) {
            userMessageRepositoryInstance = new UserMessageRepository();
        }
        return userMessageRepositoryInstance;
    }

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
    public UserMessage extractEntityFromResultSet(ResultSet resultSet) throws SQLException {
        UserMessage userMessage = new UserMessage();
        userMessage.setId(resultSet.getLong(1));
        userMessage.setMessageId(resultSet.getLong(2));
        userMessage.setUserId(resultSet.getLong(3));
        userMessage.setMessageType(resultSet.getString(4));
        return userMessage;
    }

    @Override
    public void setInsertPreparedStatement(UserMessage entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setLong(1, entity.getMessageId());
        preparedStatement.setLong(2, entity.getUserId());
        preparedStatement.setString(3, entity.getMessageType());
    }

    @Override
    public void setUpdatePreparedStatement(UserMessage entity, PreparedStatement preparedStatement) throws SQLException {
        setInsertPreparedStatement(entity, preparedStatement);
        preparedStatement.setLong(4, entity.getId());
    }

    public List<UserMessage> getUserMessagesByMessageId(long messageId) {
        Consumer<PreparedStatement> preparedStatementConsumer = preparedStatement -> {
            try {
                preparedStatement.setLong(1, messageId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        };
        Function<ResultSet, UserMessage> function = resultSet -> {
            try {
                return extractEntityFromResultSet(resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        };
        return executeNativeSelectQuery(SELECT_USER_MESSAGES_BY_MESSAGE_ID, preparedStatementConsumer, function);

    }
}
