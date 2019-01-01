package com.emailapp.view.moderator;

import com.emailapp.controller.user.DeleteModeratorController;
import com.emailapp.controller.user.ReadModeratorController;
import com.emailapp.controller.user.UpdateModeratorController;
import com.emailapp.domain.User;
import com.emailapp.view.BaseView;
import com.emailapp.view.functionality.MenuProvider;

import java.util.HashMap;
import java.util.Map;

import static com.emailapp.domain.Role.RUD_MODERATOR;
import static com.emailapp.domain.Role.RU_MODERATOR;

public class ModeratorDashboardView implements BaseView, MenuProvider {

    private ReadModeratorController readModeratorController = new ReadModeratorController();
    private UpdateModeratorController updateModeratorController = new UpdateModeratorController();
    private DeleteModeratorController deleteModeratorController = new DeleteModeratorController();

    private User sessionUser;

    public ModeratorDashboardView(User sessionUser) {
        this.sessionUser = sessionUser;
    }

    @Override
    public void render() {
        Map<Long, String> availableOptions = new HashMap<>();
        availableOptions.put(0L, "Logout");
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

        while (true) {
            long choice = provideSelectionMenu(availableOptions);
            if (choice == 0) {
                return;
            } else if (choice == 1) {
                readModeratorController.showAllMessages(sessionUser);
                goBack();
            } else if (choice == 2) {
                //TODO SELECT AND EDIT A MESSAGE - SAME AS EDITING A USER MORE OR LESS
                updateModeratorController.getMessageEditView(sessionUser);

            } else if (choice == 3) {
                //TODO delete message
                deleteModeratorController.getMessageDeleteView(sessionUser);
            }
        }
    }
}
