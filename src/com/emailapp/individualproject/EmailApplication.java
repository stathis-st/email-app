package com.emailapp.individualproject;


public class EmailApplication {

//    static Database db;
//    User admin;
//
//    public EmailApplication() {
//        db = new Database();
//        admin = new User(); // make a User() constructor to initialize admin
//    }

    public static void main(String[] args) {
//        EmailApplication app = new EmailApplication();
////        db.connectionTest(true);
//
//        if (!db.openConnection()) {
//            System.out.println("Can't open emailapp.datasource");
//            return;
//        }
//
//        User user = new User();
//        user.createUser(db, "user1", "user1", "Maria", "Maria");
////        user.id = user.saveUser(db, "user1", "user1", "Maria", "Maria");
//
//        db.closeConnection();

        LoginRegister loginRegister = new LoginRegister();
        loginRegister.welcome();




    }
}
