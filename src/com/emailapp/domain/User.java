package com.emailapp.domain;


public class User extends Entity {

    private long id;
    private String username;
    private String password;
    private String lastName;
    private String firstName;

    public User() {
    }

    public User(String username, String password, String lastName, String firstName) {
        this.username = username;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

//    public long createUser(Database db, String username, String password, String lastName, String firstName) {
//        this.id = db.createUser(db, username, password, lastName, firstName);
//        return this.id;
//    }

}
