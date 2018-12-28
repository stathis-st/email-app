package com.emailapp.service;

import com.emailapp.domain.Role;
import com.emailapp.domain.User;
import com.emailapp.domain.UserRole;
import com.emailapp.exception.InvalidCredentialsException;
import com.emailapp.repository.RoleRepository;
import com.emailapp.repository.RoleRepositoryImpl;
import com.emailapp.repository.UserRepository;
import com.emailapp.repository.UserRepositoryImpl;
import com.emailapp.repository.UserRoleRepository;
import com.emailapp.repository.UserRoleRepositoryImpl;

import java.util.List;

public class UserService {

    private UserRepository userRepository = new UserRepositoryImpl();
    private UserRoleRepository userRoleRepository = new UserRoleRepositoryImpl();
    private RoleRepository roleRepository = new RoleRepositoryImpl();

    public User login(String username, String password) throws InvalidCredentialsException {
        User user = userRepository.getUserByUsernameAndPassword(username, password);
        UserRole userRole = userRoleRepository.getRoleIdByUserId(user.getId());
        Role role = roleRepository.getOne(userRole.getRolesId());
        user.setRole(role);
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.getAll();
    }
}
