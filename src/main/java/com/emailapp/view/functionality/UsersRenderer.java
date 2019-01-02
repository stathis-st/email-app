package com.emailapp.view.functionality;

import com.emailapp.domain.User;

import java.util.List;

public interface UsersRenderer {

    default void printUsers(List<User> users) {
//        System.out.println("Id\tUsername\tFirst Name\tLast Name\tRole");
        users.forEach(this::showUser);
    }

    default void showUser(User user) {
        System.out.println(String.format("User (%s)\n\tUsername: %s\n\tFirst Name: %s\n\tLast Name: %s\n\tRole: %s\n\t",
                user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getRole().getRoleType()));
    }
}
