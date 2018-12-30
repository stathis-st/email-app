package com.emailapp.controller;

import com.emailapp.view.HomeView;

public class HomeController implements BaseController {

    public void getHomeView() {
        new HomeView().render();
    }
}
