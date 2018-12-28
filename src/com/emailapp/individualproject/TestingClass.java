package com.emailapp.individualproject;

public class TestingClass {

//    public static void main(String[] args) {


    //test database connection
//        Database database = Database.getInstance();
//        database.connectionTest();

    //testing UserRepositoryImpl

//        UserRepositoryImpl userRepository = new UserRepositoryImpl();

    //test save()
//        UserRepositoryImpl userRepository = new UserRepositoryImpl();
//        String userName = "tony";
//        String password = "54321";
//        String lastName = "green";
//        String firstName = "anthony";
//        User user = new User(userName, password, lastName, firstName);
//        long id = userRepository.save(user);
//        System.out.println(id);

    //test delete()
//        int rows = userRepository.delete(10);
//        System.out.println(rows);

    //test getAll()
//        ArrayList<User> users = userRepository.getAll();
//
//        for (User user : users) {
//            System.out.println(user.getId() + "\t" + user.getUsername() + "\t" + user.getPassword() + "\t" + user.getLastName()
//                    + "\t" + user.getFirstName() + "\n");
//        }

    //test getOne()
//        User user = userRepository.getOne(9);
//        System.out.println(user.getId() + "\t" + user.getUsername() + "\t" + user.getPassword() + "\t" + user.getLastName()
//                + "\t" + user.getFirstName() + "\n");


    //testing RoleRepositoryImpl


//        RoleRepositoryImpl roleRepository = new RoleRepositoryImpl();

    //test save()
//        Role role = new Role("rookie", "he is noob");
//        long id = roleRepository.save(role);
//        System.out.println(id);

    //test delete()
//        int row = roleRepository.delete(5);
//        System.out.println(row);

    //test getAll()
//        ArrayList<Role> roles = roleRepository.getAll();
//
//        for (Role role : roles) {
//            System.out.println(role.getId() + "\t" + role.getRoleType() + "\t" + role.getDescription() + "\n");
//        }

    //test getOne()
//        Role role = roleRepository.getOne(1);
//        System.out.println(role.getId() + "\t" + role.getRoleType() + "\t" + role.getDescription() + "\n");


    //testing UserRoleRepositoryImpl

//        UserRoleRepositoryImpl userRoleRepository = new UserRoleRepositoryImpl();


    //test save()
//        UserRole userRole = new UserRole(11, 3);
//
//        long id = userRoleRepository.save(userRole);
//        System.out.println(id);


    //test delete()
//        int row = userRoleRepository.delete(9);
//        System.out.println(row);

    //test getAll()
//        ArrayList<UserRole> userRolesList = userRoleRepository.getAll();
//
//        for (UserRole userRole : userRolesList) {
//            System.out.println(userRole.getId() + "\t" + userRole.getUsersId() + "\t" + userRole.getRolesId() + "\n");
//        }

    //test getOne()
//        UserRole userRole = userRoleRepository.getOne(7);
//        System.out.println(userRole.getId() + "\t" + userRole.getUsersId() + "\t" + userRole.getRolesId() + "\n");

//        System.out.println("=====");

//        byte i = 0;
//        boolean bool = (i != 0);
//        System.out.println(bool);


    //test
//        Date date = new Date();
//        LocalDate localDate = LocalDate.now();

//        Date dt = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        String currentTime = sdf.format(dt);
//
//        System.out.println(currentTime);


//        Message message = new Message(7, 9, false, false, );

    //testing MessageRepositoryImpl

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

//        String str = LocalDateTime.now().format(dateTimeFormatter);

//        MessageRepositoryImpl messageRepository = new MessageRepositoryImpl();

    //test save()
//        String msg = "Okaaay Okaay! Relax!";
//        String subject = "What for lunch?";
//        Message message = new Message(1, 8, false, false, msg, subject, LocalDateTime.now());
//        long id = messageRepository.save(message);
//        System.out.println(id);

    //test getAll()
//        ArrayList<Message> messages = messageRepository.getAll();
//
//        for (Message message : messages) {
//            System.out.println(message.getId() + "\t" + message.getSenderId() + "\t" + message.getReceiverId() + "\t" +
//                            message.isDeletedFromSender() + "\t" + message.isDeletedFromReceiver() + "\t" +
//                            message.getDateOfSubmission().format(formatter) + "\t" + message.getMessageData() + "\n");
//        }

    //test raiseDeletedFromSender
//        int row = messageRepository.updateSenderFlag(21);
//        System.out.println(row);

//        System.out.println("========");

    //test raiseDeletedFromReceiver
//        int row2 = messageRepository.updateReceiverFlag(21);
//        System.out.println(row2);

    //test delete()
//        int row = messageRepository.delete(21);
//        System.out.println(row);


    //test getOne()
//        Message message = messageRepository.getOne(7);
//
//        System.out.println(message.getId() + "\t" + message.getSenderId() + "\t" + message.getReceiverId() + "\t" +
//                message.isDeletedFromSender() + "\t" + message.isDeletedFromReceiver() + "\t" +
//                message.getDateOfSubmission().format(formatter) + "\t" + message.getMessageData() + "\n");


    //test editMessageContent()
//        String newMessage = "This message is edited two times and is going to be deleted!";
//        int row = messageRepository.editMessageContent(4, newMessage);
//        System.out.println(row);

//        ArrayList<String> names = new ArrayList<>();
//
//        names.add("stathis");
//        names.add("aggeliki");
//        names.add("foivos");
//        names.add("athanasia");
//
//        if (names.contains("athanassdffaia")) {
//            System.out.println("Ω ΝΑΙ!");
//        } else {
//            System.out.println("Ω ΟΧΙ!");
//        }

//        boolean stop = false;
//        Scanner scanner = new Scanner(System.in);


//
//
//        while (!stop) {
//
//
//            System.out.print("Message (max length 250 characters): ");
//            String message = scanner.nextLine();
//
//            while (message.length() > 250) {
//                System.out.println("Your message is longer than 250 characters!");
//                System.out.println("Please type a message up to 250 characters");
//                System.out.print("Message (max length 250 characters): ");
//                message = scanner.nextLine();
//            }
//
//            System.out.println("Message is: \n" + message);
//            System.out.println("Message length: " + message.length());
//
//            System.out.print("Type again? [Y/N]");
//            String ans = scanner.nextLine();
//            if (ans.equalsIgnoreCase("N")) {
//                stop = true;
//            }

//        Scanner scanner = new Scanner(System.in);
//        Console console = System.console();
//
//        String username = scanner.nextLine();
//
//        if (console == null) {
//
//        }
//
//        }


//    }
}
