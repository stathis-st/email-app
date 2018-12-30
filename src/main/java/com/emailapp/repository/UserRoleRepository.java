package com.emailapp.repository;

import com.emailapp.domain.UserRole;

public interface UserRoleRepository extends CrudRepository<UserRole> {

    UserRole getUserRoleIdByUserId(Long userId);
}