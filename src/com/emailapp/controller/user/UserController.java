package com.emailapp.controller.user;

import com.emailapp.controller.BaseController;
import com.emailapp.controller.MenuProvider;
import com.emailapp.domain.Message;
import com.emailapp.domain.User;
import com.emailapp.service.UserMessageService;
import com.emailapp.service.UserService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class UserController extends BaseController implements MenuProvider<User> {

    private static UserController userControllerInstance;

    private UserMessageService userMessageService = new UserMessageService();

    private UserService userService = new UserService();

    private UserController() {
    }

    public static UserController getInstance() {
        if (userControllerInstance == null) {
            userControllerInstance = new UserController();
        }
        return userControllerInstance;
    }

    public void showMenuForUser(User user) {
        Map<Integer, Consumer<User>> consumerMap = new HashMap<>();
        consumerMap.put(1, e -> this.showReceivedMessagesOfUser(user));
        consumerMap.put(2, e -> this.showSentMessagesOfUser(user));
        consumerMap.put(3, e -> this.composeMessage(user));
        provideSelectionMenu(consumerMap);
    }

    private void showReceivedMessagesOfUser(User user) {
        User userWithMessages = userMessageService.getUserWithReceivedMessages(user);
        printMessages(userWithMessages.getReceivedMessages(), this::showReceivedMessage);
    }

    private void showReceivedMessage(Message message) {
        StringBuilder stringBuilder = showMessage(message);
        stringBuilder.append("\tFrom: ").append(message.getSender().getFirstName()).append(" ").append(message.getSender().getLastName()).append("\n");
        System.out.println(stringBuilder);
    }

    private StringBuilder showMessage(Message message) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Message (").append(message.getId()).append(")\n");
        stringBuilder.append("\tSubject: ").append(message.getSubject()).append("\n");
        stringBuilder.append("\tDate of submission: ").append(DateTimeFormatter.ISO_DATE_TIME.format(message.getDateOfSubmission())).append("\n");
        return stringBuilder;
    }

    private void showSentMessagesOfUser(User user) {
        User userWithMessages = userMessageService.getUserWithSentMessages(user);
        printMessages(userWithMessages.getSentMessages(), this::showSentMessage);
    }

    private void showSentMessage(Message message) {
        StringBuilder stringBuilder = showMessage(message);
        stringBuilder.append("\tTo: ").append(message.getReceiver().getFirstName()).append(" ").append(message.getReceiver().getLastName()).append("\n");
        System.out.println(stringBuilder);
    }

    private void composeMessage(User fromUser) {
        userService.deleteUser(userService.getAllUsers().get(0));
        List<User> users = userService.getAllUsers();

        String usernamesList = users.stream()
                .map(User::getUsername)
                .collect(Collectors.joining(", "));

        Scanner scanner = new Scanner(System.in);

        System.out.print("Subject: ");
        String subject = scanner.nextLine();

        Optional<User> receiverUserOptional = Optional.empty();

        while (!receiverUserOptional.isPresent()) {
            System.out.println("Usernames: " + usernamesList);
            System.out.println("Receiver (username): ");
            String inputUsername = scanner.nextLine();
            receiverUserOptional = users.stream()
                    .filter(user -> user.getUsername().equalsIgnoreCase(inputUsername))
                    .findFirst();
            if (!receiverUserOptional.isPresent()) {
                System.out.println("Invalid username!");
            }
        }

        System.out.print("Message (max length 250 characters): ");
        String message = scanner.nextLine();

        while (message.length() > 250) {
            System.out.println("Your message is longer than 250 characters!");
            System.out.println("Please type a message up to 250 characters");
            System.out.print("Message (max length 250 characters): ");
            message = scanner.nextLine();
        }


        Message newMessage = new Message();
        newMessage.setSubject(subject);
        newMessage.setMessageData(message);
        newMessage.setDateOfSubmission(LocalDateTime.now());
        newMessage.setReceiver(receiverUserOptional.get());
        newMessage.setSender(fromUser);

        long messageId = 0;
        try {
            messageId = userMessageService.sendMessage(newMessage);
        } catch (Exception e) {
            handleException(e);
        }

        if (messageId > 0) {
            System.out.println("Message sent!");
        } else {
            System.out.println("Failed to send the message");
        }

        selectNumber(scanner);
    }

    private void printMessages(List<Message> inbox, Consumer<Message> messagePrinter) {
        inbox.forEach(messagePrinter);
        Scanner scanner = new Scanner(System.in);
        selectNumber(scanner);
    }

    private void selectNumber(Scanner scanner) {
        while (true) {
            System.out.println("Type '0' to go back");

            while (!scanner.hasNextInt()) {
                System.out.print("That's not a number! ");
                scanner.next();
            }
            int number = scanner.nextInt();

            if (number == 0) {
                return;
            }
        }
    }

    @Override
    public void provideMenu() {
        System.out.println("1. Received");
        System.out.println("2. Sent");
        System.out.println("3. Compose");
        System.out.println("4. Logout");
    }
}


