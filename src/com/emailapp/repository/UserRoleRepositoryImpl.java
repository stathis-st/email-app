package com.emailapp.repository;

import com.emailapp.domain.UserRole;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRoleRepositoryImpl implements UserRoleRepository {

    private static final String TABLE_NAME = "`users_roles`";
    private static final String COLUMN_USERS_ROLES_USERS_ID = "`users_id`";
    private static final String COLUMN_USERS_ROLES_ROLES_ID = "`roles_id`";

    private static final String INSERT_STATEMENT = String.format("INSERT INTO %s (%s, %s) VALUES(?, ?)",
            TABLE_NAME, COLUMN_USERS_ROLES_USERS_ID, COLUMN_USERS_ROLES_ROLES_ID);

    private static final String UPDATE_STATEMENT = String.format("UPDATE %s SET %s = ?, %s = ? WHERE `id` = ?",
            TABLE_NAME, COLUMN_USERS_ROLES_USERS_ID, COLUMN_USERS_ROLES_ROLES_ID);

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
    public UserRole extractEntityFromResultSet(ResultSet resultSet) throws SQLException {
        UserRole userRole = new UserRole();
        userRole.setId(resultSet.getLong(1));
        userRole.setUsersId(resultSet.getLong(2));
        userRole.setRolesId(resultSet.getLong(3));
        return userRole;
    }

    @Override
    public void setInsertPreparedStatement(UserRole userRole, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setLong(1, userRole.getUsersId());
        preparedStatement.setLong(2, userRole.getRolesId());
    }

    @Override
    public void setUpdatePreparedStatement(UserRole userRole, PreparedStatement preparedStatement) throws SQLException {
        setInsertPreparedStatement(userRole, preparedStatement);
        preparedStatement.setLong(3, userRole.getId());
    }
}
