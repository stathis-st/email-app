package com.emailapp.individualproject;

import com.emailapp.domain.User;
import com.emailapp.repository.UserRepositoryImpl;

import java.io.Console;
import java.util.Scanner;

public class LoginRegister {

    UserRepositoryImpl userDAO = new UserRepositoryImpl();


    public static final String WELCOME_MESSAGE = "Welcome to the email application";



    public void welcome() {
        System.out.println(WELCOME_MESSAGE);

        System.out.println("Available options: ");
        System.out.println("\tType '1' to login");
        System.out.println("\tType '2' for Registration");
        System.out.println("\tType '3' to exit the application");

        Scanner scanner = new Scanner(System.in);

        while (!scanner.hasNextInt()) {
            System.out.print("That's not a number! Please enter a number from 1 to 3 > ");
            scanner.next();
        }
        int number = scanner.nextInt();


        switch (number) {
            case 1:
                login();
                break;
            case 2:
                register();
                break;
            case 3:
                System.exit(0);
                break;
            default:
                break;

        }

    }

    public void login() {

        Scanner scanner = new Scanner(System.in);
        Console console = System.console();

        System.out.println("Enter your credentials to login");

        System.out.print("Username: ");
        String username = scanner.nextLine();

        String password = null;

        if (console == null) {
            System.out.print("Password: ");
            password = scanner.nextLine();
        } else {
            password = new String(console.readPassword("Password: "));
        }



        User user = userDAO.getUserByUsernameAndPassword(username, password);

        if (user == null) {
            System.out.println("Wrong credentials");
        } else {
            System.out.println("Dear " + user.getFirstName() + " welcome to your account");
            UserInterface userInterface = new UserInterface(user);
            userInterface.showMenu();
        }
    }

    public void register() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please fill in your personal info");

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        System.out.print("Last name: ");
        String lastName = scanner.nextLine();

        System.out.print("First name: ");
        String firstName = scanner.nextLine();

        UserRepositoryImpl userDAO = new UserRepositoryImpl();

        User user = new User(username, password, lastName, firstName);

        long id = userDAO.save(user);

        if (id > 0) {
            System.out.println("Dear " + user.getFirstName() + " thank you for registering to the email application!");
        } else {
            System.out.println("something went wrong");
        }

    }
}
