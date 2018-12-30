package com.emailapp.controller;

import com.emailapp.controller.user.AdminController;
import com.emailapp.controller.user.UserController;
import com.emailapp.domain.User;
import com.emailapp.exception.InvalidCredentialsException;
import com.emailapp.service.UserService;
import com.emailapp.view.LoginView;

import static com.emailapp.domain.Role.ADMIN_TYPE;
import static com.emailapp.domain.Role.USER_TYPE;

public class LoginController implements BaseController {

    private UserService userService = new UserService();
    private UserController userController = new UserController();
    private AdminController adminController = new AdminController();

    public void getLoginView() {
        new LoginView().render();
    }

    public void postFromLoginView(String username, String password) throws InvalidCredentialsException {
        User user = userService.login(username, password);
        switch (user.getRole().getRoleType()) {
            case USER_TYPE:
                userController.getUserDashboardView(user);
                break;
            case ADMIN_TYPE:
                adminController.getAdminDashboardView(user);
                break;
        }
    }
}
