package com.emailapp.repository;

import com.emailapp.domain.Role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleRepositoryImpl implements RoleRepository {

    private static final String TABLE_NAME = "`roles`";
    private static final String COLUMN_ROLES_ROLE_TYPE = "`role_type`";
    private static final String COLUMN_ROLES_DESCRIPTION = "`description`";

    private static final String INSERT_STATEMENT = String.format("INSERT INTO %s (%s, %s) VALUES(?, ?)",
            TABLE_NAME, COLUMN_ROLES_ROLE_TYPE, COLUMN_ROLES_DESCRIPTION);

    private static final String UPDATE_STATEMENT = String.format("UPDATE %s SET %s = ?, %s = ? WHERE `id` = ?",
            TABLE_NAME, COLUMN_ROLES_ROLE_TYPE, COLUMN_ROLES_DESCRIPTION);


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
    public Role extractEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Role role = new Role();
        role.setId(resultSet.getLong(1));
        role.setRoleType(resultSet.getString(2));
        role.setDescription(resultSet.getString(3));
        return role;
    }

    @Override
    public void setInsertPreparedStatement(Role role, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, role.getRoleType());
        preparedStatement.setString(2, role.getDescription());
    }

    @Override
    public void setUpdatePreparedStatement(Role role, PreparedStatement preparedStatement) throws SQLException {
        setInsertPreparedStatement(role, preparedStatement);
        preparedStatement.setLong(3, role.getId());
    }

}
