package com.emailapp.controller.user;

import com.emailapp.controller.BaseController;
import com.emailapp.domain.Role;
import com.emailapp.domain.User;
import com.emailapp.exception.NotFoundException;
import com.emailapp.exception.user.UserPersistenceException;
import com.emailapp.repository.RoleRepository;
import com.emailapp.repository.RoleRepositoryImpl;
import com.emailapp.service.UserService;
import com.emailapp.view.admin.AdminDashboardView;
import com.emailapp.view.admin.DeleteUserView;
import com.emailapp.view.admin.EditUserView;
import com.emailapp.view.admin.RegisteredUsersView;
import com.emailapp.view.admin.UserRegistrationView;

import java.sql.SQLException;
import java.util.List;

public class AdminController extends DeleteModeratorController implements BaseController {

    private UserService userService = new UserService();
    //TODO CHANGE TO SERVICE
    private RoleRepository roleRepository = new RoleRepositoryImpl();

    public void getAdminDashboardView(User user) {
        new AdminDashboardView(user).render();
    }

    public void getUserRegistrationView(User user) {
        List<User> registeredUsers = userService.getAllUsers();
        List<Role> availableroles = roleRepository.getAll();
        new UserRegistrationView(user, registeredUsers, availableroles).render();
    }

    public void postRegisterUser(String username, String password, String firstName, String lastName, long roleId) throws UserPersistenceException, NotFoundException, SQLException {
        User userToSave = new User();
        userToSave.setUsername(username);
        userToSave.setPassword(password);
        userToSave.setLastName(lastName);
        userToSave.setFirstName(firstName);
        userService.saveUser(userToSave, roleId);
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
