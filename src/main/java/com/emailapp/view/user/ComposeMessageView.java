package com.emailapp.view.user;

import com.emailapp.domain.Message;
import com.emailapp.domain.User;
import com.emailapp.exception.ExceptionResolver;
import com.emailapp.view.BaseView;
import com.emailapp.view.functionality.MessageEditor;
import com.emailapp.view.functionality.ValidationProvider;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ComposeMessageView extends UserDashboardView implements BaseView, ValidationProvider, MessageEditor, ExceptionResolver {

    private static final String DELIMITER = ", ";
    private List<User> availableUsers;
    private String userList;

    public ComposeMessageView(User sessionUser, List<User> availableUsers) {
        super(sessionUser);
        this.availableUsers = availableUsers;
        this.userList = availableUsers.stream()
                .map(User::getUsername)
                .collect(Collectors.joining(DELIMITER));
    }

    @Override
    public void render() {
        Scanner scanner = new Scanner(System.in);

        String subject = extractSubject(scanner);
        User receiver = extractReceiver(scanner);
        String message = extractMessage(scanner);

        Message newMessage = new Message();
        newMessage.setSubject(subject);
        newMessage.setMessageData(message);
        newMessage.setReceiver(receiver);

        try {
            userController.composeMessage(sessionUser, newMessage);
            System.out.println("Message was sent!");
        } catch (Exception e) {
            System.out.println("Failed to send the message!");
            handleException(e);
        }
    }

    private User extractReceiver(Scanner scanner) {
        Optional<User> receiverUserOptional = Optional.empty();

        while (!receiverUserOptional.isPresent()) {
            System.out.println("Available users: " + userList);
            System.out.println("Receiver (username): ");
            String inputUsername = scanner.nextLine();
            receiverUserOptional = availableUsers.stream()
                    .filter(user -> user.getUsername().equalsIgnoreCase(inputUsername))
                    .findFirst();
            if (!receiverUserOptional.isPresent()) {
                System.out.println("Invalid username!");
            }
        }
        return receiverUserOptional.get();
    }

}
