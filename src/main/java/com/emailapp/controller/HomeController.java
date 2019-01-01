package com.emailapp.controller;

import com.emailapp.view.HomeView;

public class HomeController implements BaseController {


    private static HomeController homeControllerInstance;

    private HomeController() {
    }

    public static HomeController getInstance() {
        if (homeControllerInstance == null) {
            homeControllerInstance = new HomeController();
        }
        return homeControllerInstance;
    }

    public void getHomeView() {
        new HomeView().render();
    }
}
