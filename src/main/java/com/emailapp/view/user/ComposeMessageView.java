package com.emailapp.view.user;

import com.emailapp.domain.Message;
import com.emailapp.domain.User;
import com.emailapp.exception.ExceptionResolver;
import com.emailapp.view.BaseView;
import com.emailapp.view.functionality.ValidationProvider;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ComposeMessageView extends UserDashboardView implements BaseView, ValidationProvider, ExceptionResolver {

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

    //TODO ADD (FRONTEND LIKE) VALIDATIONS IN THE FOLLOWING METHODS
    private String extractSubject(Scanner scanner) {
        System.out.print("Subject: ");
        return scanner.nextLine();
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

    private String extractMessage(Scanner scanner) {
        System.out.print("Message (max length 250 characters): ");
        String message = scanner.nextLine();

        while (message.length() > 250) {
            System.out.println("Your message is longer than 250 characters!");
            System.out.println("Please type a message up to 250 characters");
            System.out.print("Message (max length 250 characters): ");
            message = scanner.nextLine();
        }

        return message;
    }
}
