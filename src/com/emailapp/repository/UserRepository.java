package com.emailapp.repository;

import com.emailapp.domain.User;

public interface UserRepository extends CrudRepository<User> {

    User getUserByUsernameAndPassword(String username, String password);
}
