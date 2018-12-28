package com.emailapp.repositories;

import com.emailapp.domain.User;

import java.util.ArrayList;

public interface UserRepository extends Crud<User> {

    User getUserByUsernameAndPassword(String username, String password);
}
