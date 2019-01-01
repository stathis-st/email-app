package com.emailapp;


import com.emailapp.controller.HomeController;

public class EmailApplication {

    public static void main(String[] args) {
        HomeController.getInstance().getHomeView();
    }
}
