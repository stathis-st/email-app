package com.emailapp.view.functionality;

import com.emailapp.domain.User;

import java.util.List;

public interface UsersRenderer {

    default void printUsers(List<User> users) {
        users.forEach(this::showUser);
    }

    default void showUser(User user) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(user.getId()).append("\t")
                .append(user.getUsername()).append("\t")
                .append(user.getFirstName()).append("\t")
                .append(user.getLastName()).append("\n");
        System.out.println(stringBuilder);
    }

    void showSpecificDetailsForUser(User user);
}
