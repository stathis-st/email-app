package com.emailapp.repository;

import com.emailapp.domain.Message;
import com.emailapp.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import static com.emailapp.domain.UserMessage.RECEIVED;
import static com.emailapp.domain.UserMessage.SENT;


public class MessageRepositoryImpl implements MessageRepository {

    private static final String TABLE_NAME = "`messages`";

    private static final String COLUMN_MESSAGES_MESSAGE_DATA = "`message_data`";
    private static final String COLUMN_MESSAGES_SUBJECT = "`subject`";
    private static final String COLUMN_MESSAGES_DATE_OF_SUBMISSION = "`date_of_submission`";

    private static final String INSERT_STATEMENT = String.format("INSERT INTO %s (%s, %s, %s) VALUES(?, ?, ?)",
            TABLE_NAME, COLUMN_MESSAGES_MESSAGE_DATA, COLUMN_MESSAGES_SUBJECT, COLUMN_MESSAGES_DATE_OF_SUBMISSION);

    private static final String UPDATE_STATEMENT = String.format("UPDATE %s SET %s = ?, %s = ?, %s = ? WHERE `id` = ?",
            TABLE_NAME, COLUMN_MESSAGES_MESSAGE_DATA, COLUMN_MESSAGES_SUBJECT, COLUMN_MESSAGES_DATE_OF_SUBMISSION);

    private static final String GET_MESSAGES_STATEMENT_TEMPLATE =
            "SELECT mes.id, mes.message_data, mes.subject, mes.date_of_submission, us.id, us.username, us.lastname, us.firstname FROM messages mes" +
                    " INNER JOIN users_messages um" +
                    "  on mes.id = um.message_id" +
                    " INNER JOIN users us" +
                    "  on us.id = user_id" +
                    " WHERE um.message_type = '%s'" +
                    " AND mes.id in" +
                    " (SELECT message_id" +
                    " FROM messages" +
                    "       INNER JOIN users_messages um2" +
                    "                  ON messages.id = um2.message_id" +
                    "                    AND message_type = '%s'" +
                    "WHERE user_id = ?)";

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
    public Message extractEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Message message = new Message();
        message.setId(resultSet.getLong(1));
        message.setMessageData(resultSet.getString(2));
        message.setSubject(resultSet.getString(3));
        message.setDateOfSubmission(LocalDateTime.parse(resultSet.getString(4), getDateTimeFormatter()));
        return message;
    }

    @Override
    public void setInsertPreparedStatement(Message message, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, message.getMessageData());
        preparedStatement.setString(2, message.getSubject());
        preparedStatement.setString(3, message.getDateOfSubmission().format(getDateTimeFormatter()));
    }

    @Override
    public void setUpdatePreparedStatement(Message message, PreparedStatement preparedStatement) throws SQLException {
        setInsertPreparedStatement(message, preparedStatement);
        preparedStatement.setLong(4, message.getId());
    }

    private Message extractMessageWithUserFromResultSet(ResultSet resultSet, BiConsumer<Message, User> biConsumer) throws SQLException {
        Message message = extractEntityFromResultSet(resultSet);
        User user = extractUserFromResultSet(resultSet);
        biConsumer.accept(message, user);
        return message;
    }

    private User extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong(5));
        user.setUsername(resultSet.getString(6));
        user.setLastName(resultSet.getString(7));
        user.setFirstName(resultSet.getString(8));
        return user;
    }

    @Override
    public List<Message> getReceivedMessagesByUser(long userId) {
        return getMessages(userId, String.format(GET_MESSAGES_STATEMENT_TEMPLATE, SENT, RECEIVED), Message::setSender);
    }

    @Override
    public List<Message> getSentMessagesByUser(long userId) {
        return getMessages(userId, String.format(GET_MESSAGES_STATEMENT_TEMPLATE, RECEIVED, SENT), Message::setReceiver);
    }

    private List<Message> getMessages(long userId, String query, BiConsumer<Message, User> biConsumer) {
        List<Message> messages = new ArrayList<>();
        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.beforeFirst();
                while (resultSet.next()) {
                    messages.add(extractMessageWithUserFromResultSet(resultSet, biConsumer));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }
}
