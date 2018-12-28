package com.emailapp.service;

import com.emailapp.domain.Message;
import com.emailapp.domain.Role;
import com.emailapp.domain.User;
import com.emailapp.domain.UserMessage;
import com.emailapp.domain.UserRole;
import com.emailapp.exception.InvalidCredentialsException;
import com.emailapp.exception.NotFoundException;
import com.emailapp.repository.RoleRepository;
import com.emailapp.repository.RoleRepositoryImpl;
import com.emailapp.repository.UserRepository;
import com.emailapp.repository.UserRepositoryImpl;
import com.emailapp.repository.UserRoleRepository;
import com.emailapp.repository.UserRoleRepositoryImpl;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserService {

    private UserRepository userRepository = new UserRepositoryImpl();
    private UserRoleRepository userRoleRepository = new UserRoleRepositoryImpl();
    private RoleRepository roleRepository = new RoleRepositoryImpl();

    private UserMessageService userMessageService = new UserMessageService();

    public User login(String username, String password) throws InvalidCredentialsException {
        User user = userRepository.getUserByUsernameAndPassword(username, password);
        UserRole userRole = userRoleRepository.getRoleIdByUserId(user.getId());
        try {
            Role role = roleRepository.getOne(userRole.getRolesId());
            user.setRole(role);
        } catch (NotFoundException | SQLException ignored) {
            throw new InvalidCredentialsException();
        }
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.getAll();
    }

    public boolean deleteUser(User user) {
        //delete userRole
        UserRole userRole = userRoleRepository.getRoleIdByUserId(user.getId());
        userRoleRepository.delete(userRole);

        //get messages
        List<Message> messages = userMessageService.getUserWithReceivedMessages(user).getReceivedMessages();
        messages.addAll(userMessageService.getUserWithSentMessages(user).getSentMessages());

        //delete userMessages
        List<UserMessage> userMessages = messages.stream()
                .map(message -> userMessageService.getUserMessagesByMessage(message))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        userMessages.forEach(userMessageService::deleteUserMessage);

        //delete messages
        messages.forEach(userMessageService::deleteMessage);

        return userRepository.delete(user);
    }

}
