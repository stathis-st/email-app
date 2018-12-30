package com.emailapp.controller.user;

import com.emailapp.controller.BaseController;
import com.emailapp.domain.User;
import com.emailapp.exception.NotFoundException;
import com.emailapp.exception.user.UserPersistenceException;
import com.emailapp.service.UserService;
import com.emailapp.view.admin.AdminDashboardView;
import com.emailapp.view.admin.DeleteUserView;
import com.emailapp.view.admin.EditUserView;
import com.emailapp.view.admin.RegisteredUsersView;
import com.emailapp.view.admin.UserRegistrationView;

import java.sql.SQLException;
import java.util.List;

public class AdminController implements BaseController {

    private UserService userService = new UserService();

    public void getAdminDashboardView(User user) {
        new AdminDashboardView(user).render();
    }

    public void getUserRegistrationView(User user) {
        List<User> registeredUsers = userService.getAllUsers();
        new UserRegistrationView(user, registeredUsers).render();
    }

    public void postRegisterUser(String username, String password, String firstName, String lastName) throws UserPersistenceException {
        User userToSave = new User();
        userToSave.setUsername(username);
        userToSave.setPassword(password);
        userToSave.setLastName(lastName);
        userToSave.setFirstName(firstName);
        userService.saveUser(userToSave);
    }

    public void showAllUsers(User sessionUser) {
        List<User> registeredUsers = userService.getAllUsers();
        new RegisteredUsersView(sessionUser, registeredUsers).render();
    }


    public void getUserDeleteView(User sessionUser) {
        List<User> registeredUsers = userService.getAllUsers();
        //TODO remove self with sessionUser
        new DeleteUserView(sessionUser, registeredUsers).render();
    }


    public void deleteUser(User sessionUser, long userId) throws NotFoundException, SQLException {
        User userToDelete = userService.getUserById(userId);
        userService.deleteUser(userToDelete);
    }

    public void getUserEditView(User sessionUser) {
        List<User> registeredUsers = userService.getAllUsers();
        new EditUserView(sessionUser, registeredUsers).render();
    }

    public void postEditUser(User sessionUser, User chosenUser) throws NotFoundException, SQLException {
        userService.editUser(chosenUser);
    }
}
