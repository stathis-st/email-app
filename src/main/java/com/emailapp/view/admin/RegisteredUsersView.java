package com.emailapp.view.admin;

import com.emailapp.domain.User;
import com.emailapp.view.functionality.UsersRenderer;

import java.util.List;

public class RegisteredUsersView extends AdminDashboardView implements UsersRenderer {

    protected List<User> registeredUsers;

    public RegisteredUsersView(User sessionUser, List<User> registeredUsers) {
        super(sessionUser);
        this.registeredUsers = registeredUsers;
    }

    @Override
    public void render() {
        clearConsole();
        printUsers(registeredUsers);
        goBack();
    }
}
