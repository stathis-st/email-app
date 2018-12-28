package com.emailapp.repositories;

import com.emailapp.datasource.Database;
import com.emailapp.domain.UserRole;

import java.sql.*;
import java.util.ArrayList;

import static com.emailapp.datasource.Database.COLUMN_ID;

public class UserRoleRepositoryImpl implements UserRoleRepository {

    // table `users_roles`
    public static final String TABLE_USERS_ROLES = "`users_roles`";
    public static final String COLUMN_USERS_ROLES_USERS_ID = "`users_id`";
    public static final String COLUMN_USERS_ROLES_ROLES_ID = "`roles_id`";

    public static final String INSERT_USER_ROLE = "INSERT INTO " + TABLE_USERS_ROLES +
            "(" + COLUMN_USERS_ROLES_USERS_ID + ", " + COLUMN_USERS_ROLES_ROLES_ID + ") VALUES(?, ?);";

    public static final String DELETE_USER_ROLE = "DELETE FROM " + TABLE_USERS_ROLES + " WHERE " + COLUMN_ID + " = ?";

    public static final String SELECT_ALL_USER_ROLES = "SELECT * FROM " + TABLE_USERS_ROLES + ";";

    public static final String SELECT_USER_ROLE_BY_ID = "SELECT * FROM " + TABLE_USERS_ROLES + " WHERE " + COLUMN_ID + " = ?";

    private Database db = Database.getInstance();


    @Override
    public long save(UserRole userRole) {

        Connection connection = db.getConnection();
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        long id = 0;

        try {
            pstmt = connection.prepareStatement(INSERT_USER_ROLE, Statement.RETURN_GENERATED_KEYS);
            pstmt.setLong(1, userRole.getUsersId());
            pstmt.setLong(2, userRole.getRolesId());

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
            pstmt = connection.prepareStatement(DELETE_USER_ROLE);
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
    public UserRole getOne(long id) {

        UserRole userRole = null;

        Connection connection = db.getConnection();
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        try {
            pstmt = connection.prepareStatement(SELECT_USER_ROLE_BY_ID);
            pstmt.setLong(1, id);
            resultSet = pstmt.executeQuery();

            resultSet.beforeFirst();
            if (resultSet.next()) {

                userRole = new UserRole();

                userRole.setId(resultSet.getLong(1));
                userRole.setUsersId(resultSet.getLong(2));
                userRole.setRolesId(resultSet.getLong(3));
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

        return userRole;
    }

    @Override
    public ArrayList<UserRole> getAll() {

        ArrayList<UserRole> userRolesList = new ArrayList<>();
        UserRole userRole = null;

        Connection connection = db.getConnection();
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        try {
            pstmt = connection.prepareStatement(SELECT_ALL_USER_ROLES);

            resultSet = pstmt.executeQuery();

            resultSet.beforeFirst();
            while (resultSet.next()) {
                userRole = new UserRole();
                userRole.setId(resultSet.getLong(1));
                userRole.setUsersId(resultSet.getLong(2));
                userRole.setRolesId(resultSet.getLong(3));

                userRolesList.add(userRole);
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

        return userRolesList;
    }
}
