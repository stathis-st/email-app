package com.emailapp.repository;

import com.emailapp.domain.User;
import com.emailapp.exception.InvalidCredentialsException;

public interface UserRepository extends CrudRepository<User> {

    User getUserByUsernameAndPassword(String username, String password) throws InvalidCredentialsException;
}
