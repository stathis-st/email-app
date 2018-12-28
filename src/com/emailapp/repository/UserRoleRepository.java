package com.emailapp.repository;

import com.emailapp.domain.UserRole;

public interface UserRoleRepository extends CrudRepository<UserRole> {

    UserRole getRoleIdByUserId(Long userId);
}