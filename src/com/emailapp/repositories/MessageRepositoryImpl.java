package com.emailapp.repositories;

import com.emailapp.datasource.Database;
import com.emailapp.domain.Message;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static com.emailapp.datasource.Database.COLUMN_ID;
import static com.emailapp.repositories.UserRepositoryImpl.COLUMN_USERS_USERNAME;
import static com.emailapp.repositories.UserRepositoryImpl.TABLE_USERS;


public class MessageRepositoryImpl implements MessageRepository {

    // table `messages`
    public static final String TABLE_MESSAGES = "`messages`";
    public static final String COLUMN_MESSAGES_SENDER_ID = "`sender_id`";
    public static final String COLUMN_MESSAGES_RECEIVER_ID = "`receiver_id`";
    public static final String COLUMN_MESSAGES_DELETED_FROM_SENDER = "`deleted_from_sender`";
    public static final String COLUMN_MESSAGES_DELETED_FROM_RECEIVER = "`deleted_from_receiver`";
    public static final String COLUMN_MESSAGES_MESSAGE_DATA = "`message_data`";
    public static final String COLUMN_MESSAGES_SUBJECT = "`subject`";
    public static final String COLUMN_MESSAGES_DATE_OF_SUBMISSION = "`date_of_submission`";

    public static final String INSERT_MESSAGE = "INSERT INTO " + TABLE_MESSAGES +
            "(" + COLUMN_MESSAGES_SENDER_ID + ", " + COLUMN_MESSAGES_RECEIVER_ID + ", " + COLUMN_MESSAGES_DELETED_FROM_SENDER +
            ", " + COLUMN_MESSAGES_DELETED_FROM_RECEIVER + ", " + COLUMN_MESSAGES_MESSAGE_DATA +
            ", " + COLUMN_MESSAGES_SUBJECT + ", " + COLUMN_MESSAGES_DATE_OF_SUBMISSION + ") VALUES(?, ?, ?, ?, ?, ?, ?);";

    public static final String DELETE_MESSAGE = "DELETE FROM " + TABLE_MESSAGES + " WHERE " + COLUMN_ID + " = ?" +
            " AND " + COLUMN_MESSAGES_DELETED_FROM_SENDER + " = 1" + " AND " + COLUMN_MESSAGES_DELETED_FROM_RECEIVER +
            " = 1;";

    public static final String SELECT_ALL_MESSAGES = "SELECT * FROM " + TABLE_MESSAGES + ";";

    public static final String SELECT_MESSAGE_BY_ID = "SELECT * FROM " + TABLE_MESSAGES + " WHERE " + COLUMN_ID + " = ?";

    public static final String UPDATE_SENDER_BIT = "UPDATE " + TABLE_MESSAGES + " SET " + COLUMN_MESSAGES_DELETED_FROM_SENDER +
            " = 1" + " WHERE " + COLUMN_ID + " = ?;";

    public static final String UPDATE_RECEIVER_BIT = "UPDATE " + TABLE_MESSAGES + " SET " + COLUMN_MESSAGES_DELETED_FROM_RECEIVER +
            " = 1" + " WHERE " + COLUMN_ID + " = ?;";

    public static final String EDIT_MESSAGE_CONTENT = "UPDATE " + TABLE_MESSAGES + " SET " + COLUMN_MESSAGES_MESSAGE_DATA +
            " = ?" + " WHERE " + COLUMN_ID + " = ?;";

    public static final String SELECT_USER_INBOX = "SELECT " + TABLE_MESSAGES + "." + COLUMN_MESSAGES_SUBJECT + ", " +
            TABLE_MESSAGES + "." + COLUMN_MESSAGES_MESSAGE_DATA + ", " + "`sender`." + COLUMN_USERS_USERNAME + " AS `from`" +
            ", " + TABLE_MESSAGES + "." + COLUMN_MESSAGES_DATE_OF_SUBMISSION + " AS `date`" +
            " FROM " + TABLE_MESSAGES +
            " INNER JOIN " + TABLE_USERS + " AS `receiver`" +
            " ON `receiver`." + COLUMN_ID + " = " + TABLE_MESSAGES + "." + COLUMN_MESSAGES_RECEIVER_ID +
            " INNER JOIN " + TABLE_USERS + " AS `sender`" +
            " ON `sender`." + COLUMN_ID + " = " + TABLE_MESSAGES + "." + COLUMN_MESSAGES_SENDER_ID +
            " WHERE `receiver`." + COLUMN_ID + " = ?" +
            " ORDER BY " + TABLE_MESSAGES + "." + COLUMN_MESSAGES_DATE_OF_SUBMISSION + " DESC;";

    public static final String SELECT_USER_SENT = "SELECT " + TABLE_MESSAGES + "." + COLUMN_MESSAGES_SUBJECT + ", " +
            TABLE_MESSAGES + "." + COLUMN_MESSAGES_MESSAGE_DATA + ", " + "`receiver`." + COLUMN_USERS_USERNAME + " AS `from`" +
            ", " + TABLE_MESSAGES + "." + COLUMN_MESSAGES_DATE_OF_SUBMISSION + " AS `date`" +
            " FROM " + TABLE_MESSAGES +
            " INNER JOIN " + TABLE_USERS + " AS `receiver`" +
            " ON `receiver`." + COLUMN_ID + " = " + TABLE_MESSAGES + "." + COLUMN_MESSAGES_RECEIVER_ID +
            " INNER JOIN " + TABLE_USERS + " AS `sender`" +
            " ON `sender`." + COLUMN_ID + " = " + TABLE_MESSAGES + "." + COLUMN_MESSAGES_SENDER_ID +
            " WHERE `sender`." + COLUMN_ID + " = ?" +
            " ORDER BY " + TABLE_MESSAGES + "." + COLUMN_MESSAGES_DATE_OF_SUBMISSION + " DESC;";


    private Database db = Database.getInstance();

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    @Override
    public long save(Message message) {

        Connection connection = db.getConnection();
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;


        long id = 0;
        try {
            pstmt = connection.prepareStatement(INSERT_MESSAGE, Statement.RETURN_GENERATED_KEYS);
            pstmt.setLong(1, message.getSenderId());
            pstmt.setLong(2, message.getReceiverId());
            pstmt.setBoolean(3, message.isDeletedFromSender());
            pstmt.setBoolean(4, message.isDeletedFromReceiver());
            pstmt.setString(5, message.getMessageData());
            pstmt.setString(6, message.getSubject());
            pstmt.setString(7, message.getDateOfSubmission().format(formatter));

            if (pstmt.executeUpdate() > 0) {
                resultSet = pstmt.getGeneratedKeys();

                if (resultSet.next())
                    id = resultSet.getLong(1);
            }

        } catch (SQLException | NullPointerException e) {
            System.out.println("Query failed: " + e.getMessage());
            return -1;
        } finally {
            try {
                resultSet.close();
                pstmt.close();
                connection.close();
            } catch (SQLException | NullPointerException e) {
                System.out.println(e.getMessage());
            }
        }
        return id;
    }

    @Override
    public int delete(long id) {

        Connection connection = db.getConnection();
        PreparedStatement pstmt = null;
        int rowsAffected = 0;

        try {
            pstmt = connection.prepareStatement(DELETE_MESSAGE);
            pstmt.setLong(1, id);

            rowsAffected = pstmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("Query failed: " + e.getMessage());
        } finally {
            try {
                pstmt.close();
                connection.close();
            } catch (SQLException | NullPointerException e) {
                System.out.println(e.getMessage());
            }

        }

        return rowsAffected;
    }

    @Override
    public Message getOne(long id) {

        Message message = null;

        Connection connection = db.getConnection();
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        try {
            pstmt = connection.prepareStatement(SELECT_MESSAGE_BY_ID);
            pstmt.setLong(1, id);
            resultSet = pstmt.executeQuery();

            resultSet.beforeFirst();
            if (resultSet.next()) {

                message = new Message();

                message.setId(resultSet.getLong(1));
                message.setSenderId(resultSet.getLong(2));
                message.setReceiverId(resultSet.getLong(3));
                message.setDeletedFromSender(resultSet.getBoolean(4));
                message.setDeletedFromReceiver(resultSet.getBoolean(5));
                message.setMessageData(resultSet.getString(6));
                message.setSubject(resultSet.getString(7));
                message.setDateOfSubmission(LocalDateTime.parse(resultSet.getString(8), formatter));

            }

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        } finally {
            try {
                resultSet.close();
                pstmt.close();
                connection.close();
            } catch (SQLException | NullPointerException e) {
                System.out.println(e.getMessage());
            }
        }

        return message;
    }

    @Override
    public ArrayList<Message> getAll() {

        ArrayList<Message> messagesList = new ArrayList<>();
        Message message = null;

        Connection connection = db.getConnection();
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        try {
            pstmt = connection.prepareStatement(SELECT_ALL_MESSAGES);

            resultSet = pstmt.executeQuery();

            resultSet.beforeFirst();
            while (resultSet.next()) {
                message = new Message();
                message.setId(resultSet.getLong(1));
                message.setSenderId(resultSet.getLong(2));
                message.setReceiverId(resultSet.getLong(3));
                message.setDeletedFromSender(resultSet.getBoolean(4));
                message.setDeletedFromReceiver(resultSet.getBoolean(5));
                message.setMessageData(resultSet.getString(6));
                message.setSubject(resultSet.getString(7));
                message.setDateOfSubmission(LocalDateTime.parse(resultSet.getString(8), formatter));

                messagesList.add(message);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                resultSet.close();
                pstmt.close();
                connection.close();
            } catch (SQLException | NullPointerException e) {
                System.out.println(e.getMessage());
            }

        }

        return messagesList;
    }

    @Override
    public int updateSenderFlag(long id) {

        Connection connection = db.getConnection();
        PreparedStatement pstmt = null;
        int rowsAffected = 0;

        try {
            pstmt = connection.prepareStatement(UPDATE_SENDER_BIT);
            pstmt.setLong(1, id);

            rowsAffected = pstmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("Query failed: " + e.getMessage());
        } finally {
            try {
                pstmt.close();
                connection.close();
            } catch (SQLException | NullPointerException e) {
                System.out.println(e.getMessage());
            }

        }

        return rowsAffected;
    }

    @Override
    public int updateReceiverFlag(long id) {

        Connection connection = db.getConnection();
        PreparedStatement pstmt = null;
        int rowsAffected = 0;

        try {
            pstmt = connection.prepareStatement(UPDATE_RECEIVER_BIT);
            pstmt.setLong(1, id);

            rowsAffected = pstmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("Query failed: " + e.getMessage());
        } finally {
            try {
                pstmt.close();
                connection.close();
            } catch (SQLException | NullPointerException e) {
                System.out.println(e.getMessage());
            }

        }

        return rowsAffected;
    }

    @Override
    public int editMessageContent(long id, String newMessage) {

        Connection connection = db.getConnection();
        PreparedStatement pstmt = null;
        int rowsAffected = 0;

        try {
            pstmt = connection.prepareStatement(EDIT_MESSAGE_CONTENT);
            pstmt.setString(1, newMessage);
            pstmt.setLong(2, id);

            rowsAffected = pstmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("Query failed: " + e.getMessage());
        } finally {
            try {
                pstmt.close();
                connection.close();
            } catch (SQLException | NullPointerException e) {
                System.out.println(e.getMessage());
            }

        }

        return rowsAffected;
    }

    @Override
    public ArrayList<StringBuilder> getUserInbox(long userId) {

        ArrayList<StringBuilder> inboxList = new ArrayList<>();
        StringBuilder message = null;

        Connection connection = db.getConnection();
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        try {
            pstmt = connection.prepareStatement(SELECT_USER_INBOX);
            pstmt.setLong(1, userId);

            resultSet = pstmt.executeQuery();

            resultSet.beforeFirst();
            while (resultSet.next()) {
                message = new StringBuilder();
                message.append(resultSet.getString(1)).append("\t");
                message.append(resultSet.getString(2)).append("\t");
                message.append(resultSet.getString(3)).append("\t");
                message.append(resultSet.getString(4));

                inboxList.add(message);
            }

        } catch (SQLException e) {
            System.out.println("Query failed:" + e.getMessage());
        } finally {
            try {
                resultSet.close();
                pstmt.close();
                connection.close();
            } catch (SQLException | NullPointerException e) {
                System.out.println(e.getMessage());
            }

        }

        return inboxList;
    }

    @Override
    public ArrayList<StringBuilder> getUserSent(long userId) {

        ArrayList<StringBuilder> sentList = new ArrayList<>();
        StringBuilder message = null;

        Connection connection = db.getConnection();
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        try {
            pstmt = connection.prepareStatement(SELECT_USER_SENT);
            pstmt.setLong(1, userId);

            resultSet = pstmt.executeQuery();

            resultSet.beforeFirst();
            while (resultSet.next()) {
                message = new StringBuilder();
                message.append(resultSet.getString(1)).append("\t");
                message.append(resultSet.getString(2)).append("\t");
                message.append(resultSet.getString(3)).append("\t");
                message.append(resultSet.getString(4));

                sentList.add(message);
            }

        } catch (SQLException e) {
            System.out.println("Query failed:" + e.getMessage());
        } finally {
            try {
                resultSet.close();
                pstmt.close();
                connection.close();
            } catch (SQLException | NullPointerException e) {
                System.out.println(e.getMessage());
            }

        }

        return sentList;

    }
}
