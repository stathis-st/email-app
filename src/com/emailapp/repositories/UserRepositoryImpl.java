package com.emailapp.repositories;

import com.emailapp.datasource.Database;
import com.emailapp.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

import static com.emailapp.datasource.Database.COLUMN_ID;

public class UserRepositoryImpl implements UserRepository {

    // table `users`
    public static final String TABLE_USERS = "`users`";
    public static final String COLUMN_USERS_USERNAME = "`username`";
    public static final String COLUMN_USERS_PASSWORD = "`password`";
    public static final String COLUMN_USERS_LASTNAME = "`lastname`";
    public static final String COLUMN_USERS_FIRSTNAME = "`firstname`";


    public static final String INSERT_USER = "INSERT INTO " + TABLE_USERS +
            "(" + COLUMN_USERS_USERNAME + ", " + COLUMN_USERS_PASSWORD + ", " + COLUMN_USERS_LASTNAME +
            ", " + COLUMN_USERS_FIRSTNAME + ") VALUES(?, ?, ?, ?);";

    public static final String DELETE_USER = "DELETE FROM " + TABLE_USERS + " WHERE " + COLUMN_ID + " = ?";

    public static final String SELECT_ALL_USERS = "SELECT * FROM " + TABLE_USERS + ";";

    public static final String SELECT_USER_BY_ID = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_ID + " = ?";

    public static final String SELECT_USER_BY_USERNAME_AND_PASSWORD = "SELECT * FROM " + TABLE_USERS + " WHERE " +
            COLUMN_USERS_USERNAME + " = ?" + " AND " + COLUMN_USERS_PASSWORD + " = ?;";

    private Database db = Database.getInstance();


    //JUST FOR TESTING!
//    private Connection connection;
//
//    PreparedStatement preparedStatement;
//
//    {
//        try {
//            preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

//    public static final String TABLE_USERS_MAX_ID = "SELECT MAX(" + COLUMN_ID + ")" + " FROM " + TABLE_USERS + ";";

    @Override
    public long save(User user) {

        Connection connection = db.getConnection();
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        long id = 0;
        try {
            pstmt = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getLastName());
            pstmt.setString(4, user.getFirstName());

            if (pstmt.executeUpdate() > 0) {
                resultSet = pstmt.getGeneratedKeys();

                if (resultSet.next())
                    id = resultSet.getLong(1);
            }

            //for debugging purposes
//            System.out.println("Printing the final statement before executing it: " + statement);
//            int rowsAffected = statement.executeUpdate();
//            System.out.println(rowsAffected + " row(s) affected");

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
            pstmt = connection.prepareStatement(DELETE_USER);
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
    public User getOne(long id) {

        User user = null;

        Connection connection = db.getConnection();
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        try {
            pstmt = connection.prepareStatement(SELECT_USER_BY_ID);
            pstmt.setLong(1, id);
            resultSet = pstmt.executeQuery();

            resultSet.beforeFirst();
            if (resultSet.next()) {

                user = new User();

                user.setId(resultSet.getLong(1));
                user.setUsername(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setLastName(resultSet.getString(4));
                user.setFirstName(resultSet.getString(5));

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

        return user;
    }

    @Override
    public ArrayList<User> getAll() {

        ArrayList<User> usersList = new ArrayList<>();
        User user = null;

        Connection connection = db.getConnection();
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        try {
            pstmt = connection.prepareStatement(SELECT_ALL_USERS);

            resultSet = pstmt.executeQuery();

            resultSet.beforeFirst();
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong(1));
                user.setUsername(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setLastName(resultSet.getString(4));
                user.setFirstName(resultSet.getString(5));

                usersList.add(user);
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

        return usersList;
    }

    @Override
    public User getUserByUsernameAndPassword(String username, String password) {

        User user = null;

        Connection connection = db.getConnection();
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        try {
            pstmt = connection.prepareStatement(SELECT_USER_BY_USERNAME_AND_PASSWORD);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            resultSet = pstmt.executeQuery();

            resultSet.beforeFirst();
            if (resultSet.next()) {

                user = new User();

                user.setId(resultSet.getLong(1));
                user.setUsername(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setLastName(resultSet.getString(4));
                user.setFirstName(resultSet.getString(5));

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

        return user;
    }

    //    int getUserId(Connection conn) {
//        int id = 0;
//
//        // try with resources
//        try (PreparedStatement statement = conn.prepareStatement(TABLE_USERS_MAX_ID)) {
//            ResultSet resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                id = resultSet.getInt(1);
//            }
//        } catch (SQLException e) {
//            System.out.println("Query failed: " + e.getMessage());
//            return -1;
//        }
//        return id;
//    }

}
