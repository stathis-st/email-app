package com.emailapp.controller;

import com.emailapp.controller.user.AdminController;
import com.emailapp.controller.user.ReadModeratorController;
import com.emailapp.controller.user.UserController;
import com.emailapp.domain.User;
import com.emailapp.exception.InvalidCredentialsException;
import com.emailapp.service.UserService;
import com.emailapp.view.LoginView;

import static com.emailapp.domain.Role.ADMINISTRATOR;
import static com.emailapp.domain.Role.RUD_MODERATOR;
import static com.emailapp.domain.Role.RU_MODERATOR;
import static com.emailapp.domain.Role.R_MODERATOR;
import static com.emailapp.domain.Role.USER;

public class LoginController implements BaseController {

    private static LoginController loginControllerInstance;

    private UserService userService = UserService.getInstance();

    private AdminController adminController = AdminController.getInstance();
    private ReadModeratorController readModeratorController = ReadModeratorController.getInstance();
    private UserController userController = UserController.getInstance();

    private LoginController() {
    }

    public static LoginController getInstance() {
        if (loginControllerInstance == null) {
            loginControllerInstance = new LoginController();
        }
        return loginControllerInstance;
    }

    public void getLoginView() {
        new LoginView().render();
    }

    public void postFromLoginView(String username, String password) throws InvalidCredentialsException {
        User user = userService.login(username, password);
        switch (user.getRole().getRoleType()) {
            case ADMINISTRATOR:
                adminController.getAdminDashboardView(user);
                break;
            case R_MODERATOR:
            case RU_MODERATOR:
            case RUD_MODERATOR:
                readModeratorController.getBaseModeratorView(user);
                break;
            case USER:
                userController.getUserDashboardView(user);
                break;
        }
    }
}
