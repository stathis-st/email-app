package com.emailapp.repositories;

import com.emailapp.datasource.Database;
import com.emailapp.domain.Role;

import java.sql.*;
import java.util.ArrayList;

import static com.emailapp.datasource.Database.COLUMN_ID;

public class RoleRepositoryImpl implements RoleRepository {


    // table `roles`
    public static final String TABLE_ROLES = "`roles`";
    public static final String COLUMN_ROLES_ROLE_TYPE = "`role_type`";
    public static final String COLUMN_ROLES_DESCRIPTION = "`description`";

    public static final String INSERT_ROLE = "INSERT INTO " + TABLE_ROLES +
            "(" + COLUMN_ROLES_ROLE_TYPE + ", " + COLUMN_ROLES_DESCRIPTION + ") VALUES(?, ?);";

    public static final String DELETE_ROLE = "DELETE FROM " + TABLE_ROLES + " WHERE " + COLUMN_ID + " = ?";

    public static final String SELECT_ALL_ROLES = "SELECT * FROM " + TABLE_ROLES + ";";

    public static final String SELECT_ROLE_BY_ID = "SELECT * FROM " + TABLE_ROLES + " WHERE " + COLUMN_ID + " = ?";

    private Database db = Database.getInstance();


    @Override
    public long save(Role role) {

        Connection connection = db.getConnection();
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        long id = 0;

        try {
            pstmt = connection.prepareStatement(INSERT_ROLE, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, role.getRoleType());
            pstmt.setString(2, role.getDescription());

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
            pstmt = connection.prepareStatement(DELETE_ROLE);
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
    public Role getOne(long id) {

        Role role = null;

        Connection connection = db.getConnection();
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        try {
            pstmt = connection.prepareStatement(SELECT_ROLE_BY_ID);
            pstmt.setLong(1, id);
            resultSet = pstmt.executeQuery();

            resultSet.beforeFirst();
            if (resultSet.next()) {

                role = new Role();

                role.setId(resultSet.getLong(1));
                role.setRoleType(resultSet.getString(2));
                role.setDescription(resultSet.getString(3));
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

        return role;
    }

    @Override
    public ArrayList<Role> getAll() {

        ArrayList<Role> rolesList = new ArrayList<>();
        Role role = null;

        Connection connection = db.getConnection();
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        try {
            pstmt = connection.prepareStatement(SELECT_ALL_ROLES);

            resultSet = pstmt.executeQuery();

            resultSet.beforeFirst();
            while (resultSet.next()) {
                role = new Role();
                role.setId(resultSet.getLong(1));
                role.setRoleType(resultSet.getString(2));
                role.setDescription(resultSet.getString(3));

                rolesList.add(role);
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

        return rolesList;
    }
}
