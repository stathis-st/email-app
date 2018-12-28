package com.emailapp.individualproject;


import com.emailapp.repository.MessageRepository;
import com.emailapp.repository.MessageRepositoryImpl;
import com.emailapp.repository.RoleRepository;
import com.emailapp.repository.RoleRepositoryImpl;
import com.emailapp.repository.UserRepository;
import com.emailapp.repository.UserRepositoryImpl;
import com.emailapp.repository.UserRoleRepository;
import com.emailapp.repository.UserRoleRepositoryImpl;

public class EmailApplication {

    private static final UserRepository userRepository = new UserRepositoryImpl();
    private static final MessageRepository messageRepostory = new MessageRepositoryImpl();
    private static final RoleRepository roleRepository = new RoleRepositoryImpl();
    private static final UserRoleRepository userRoleRepository = new UserRoleRepositoryImpl();

//    static Database database;
//    User admin;
//
//    public EmailApplication() {
//        database = new Database();
//        admin = new User(); // make a User() constructor to initialize admin
//    }

    public static void main(String[] args) {
//        EmailApplication app = new EmailApplication();
////        database.connectionTest(true);
//
//        if (!database.openConnection()) {
//            System.out.println("Can't open emailapp.datasource");
//            return;
//        }
//
//        User user = new User();
//        user.createUser(database, "user1", "user1", "Maria", "Maria");
////        user.id = user.saveUser(database, "user1", "user1", "Maria", "Maria");
//
//        database.closeConnection();

        LoginRegister loginRegister = new LoginRegister();
        loginRegister.welcome();


    }
}
