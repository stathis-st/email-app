package com.emailapp.individualproject;

import com.emailapp.domain.Message;
import com.emailapp.domain.User;
import com.emailapp.repository.MessageRepositoryImpl;
import com.emailapp.repository.UserRepositoryImpl;
import com.emailapp.service.UserMessageService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private User user;
    private UserMessageService userMessageService = new UserMessageService();

    public UserInterface(User user) {
        this.user = user;
    }

    void showMenu() {
        System.out.println("1. Inbox");
        System.out.println("2. Sent");
        System.out.println("3. Compose");
        System.out.println("4. Logout");

        Scanner scanner = new Scanner(System.in);

        while (!scanner.hasNextInt()) {
            System.out.print("That's not a number! Please enter a number from 1 to 4 > ");
            scanner.next();
        }
        int number = scanner.nextInt();

        switch (number) {
            case 1:
                showInbox();
                break;
            case 2:
                showSent();
                break;
            case 3:
                composeEmail();
                break;
            case 4:
                logout();
            default:
                break;
        }
    }

    private void showInbox() {
        User foundUser = userMessageService.getUserWithReceivedMessages(user.getId());
        printMessages(foundUser.getReceivedMessages());
    }

    private void printMessages(List<Message> inbox) {
        for (Message message : inbox) {
            System.out.println(message);
        }
        Scanner scanner = new Scanner(System.in);
        selectNumber(scanner);
    }

    private void selectNumber(Scanner scanner) {
        System.out.println("Type '0' to go back");

        while (!scanner.hasNextInt()) {
            System.out.print("That's not a number! ");
            scanner.next();
        }
        int number = scanner.nextInt();

        if (number == 0) {
            showMenu();
        }
    }

    public void showSent() {

        MessageRepositoryImpl msgDAO = new MessageRepositoryImpl();

        List<Message> sent = msgDAO.getSentMessagesByUser(this.user.getId());

        printMessages(sent);

    }

    public void composeEmail() {

        Scanner scanner = new Scanner(System.in);

        UserRepositoryImpl userDAO = new UserRepositoryImpl();

        MessageRepositoryImpl msgDAO = new MessageRepositoryImpl();

        List<User> users = userDAO.getAll();

        ArrayList<String> userNames = new ArrayList<>();

        for (User user : users) {
            userNames.add(user.getUsername());
        }

        System.out.print("Subject: ");
        String subject = scanner.nextLine();

        String receiver = "";

        while (!userNames.contains(receiver)) {
            System.out.print("Receiver (username): ");
            receiver = scanner.nextLine();
        }

        long receiverId = 0;

        for (User user : users) {
            if (user.getUsername().equals(receiver)) {
                receiverId = user.getId();
                break;
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

        long senderId = this.user.getId();

        Message newMessage = new Message(message, subject, LocalDateTime.now());

        long messageId = msgDAO.save(newMessage);

        if (messageId > 0) {
            System.out.println("Message sent!");
        } else {
            System.out.println("Failed to send the message");
        }

        selectNumber(scanner);
    }

    public void logout() {
        this.user = null;
        LoginRegister loginRegister = new LoginRegister();
        loginRegister.welcome();
    }
}
