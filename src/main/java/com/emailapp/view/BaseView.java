package com.emailapp.view;

import com.emailapp.view.functionality.ConsoleCleaner;

public abstract class BaseView implements ConsoleCleaner {

    public void render() {
        clearConsole();
    }
}
