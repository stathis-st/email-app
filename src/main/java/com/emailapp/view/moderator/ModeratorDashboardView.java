package com.emailapp.view.moderator;

import com.emailapp.controller.user.DeleteModeratorController;
import com.emailapp.controller.user.ReadModeratorController;
import com.emailapp.controller.user.UpdateModeratorController;
import com.emailapp.domain.User;
import com.emailapp.view.BaseView;
import com.emailapp.view.functionality.MenuProvider;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.emailapp.domain.Role.RUD_MODERATOR;
import static com.emailapp.domain.Role.RU_MODERATOR;

public class ModeratorDashboardView extends BaseView implements MenuProvider {

    private ReadModeratorController readModeratorController = ReadModeratorController.getInstance();
    private UpdateModeratorController updateModeratorController = UpdateModeratorController.getInstance();
    private DeleteModeratorController deleteModeratorController = DeleteModeratorController.getInstance();

    private User sessionUser;

    public ModeratorDashboardView(User sessionUser) {
        this.sessionUser = sessionUser;
    }

    @Override
    public void render() {
        clearConsole();
        Map<Long, String> availableOptions = new LinkedHashMap<>();
        availableOptions.put(1L, "Read all Messages");
        switch (sessionUser.getRole().getRoleType()) {
            case RU_MODERATOR:
                availableOptions.put(2L, "Update Message");
                break;
            case RUD_MODERATOR:
                availableOptions.put(2L, "Update Message");
                availableOptions.put(3L, "Delete Message");
                break;
        }
        availableOptions.put(0L, "Logout");

        while (true) {
            long choice = provideSelectionMenu(availableOptions);
            if (choice == 0) {
                return;
            } else if (choice == 1) {
                readModeratorController.showAllMessages(sessionUser);
                goBack();
            } else if (choice == 2) {
                updateModeratorController.getMessageEditView(sessionUser);
            } else if (choice == 3) {
                deleteModeratorController.getMessageDeleteView(sessionUser);
            }
        }
    }
}
