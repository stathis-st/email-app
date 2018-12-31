package com.emailapp.view.moderator;

import com.emailapp.controller.user.DeleteModeratorController;
import com.emailapp.controller.user.UpdateModeratorController;
import com.emailapp.domain.Message;
import com.emailapp.domain.User;
import com.emailapp.exception.ExceptionResolver;
import com.emailapp.view.BaseView;
import com.emailapp.view.functionality.MenuProvider;
import com.emailapp.view.functionality.MessagesRenderer;

import java.util.HashMap;
import java.util.Map;

import static com.emailapp.domain.Role.ADMINISTRATOR;
import static com.emailapp.domain.Role.RUD_MODERATOR;
import static com.emailapp.domain.Role.RU_MODERATOR;

public class ModeratorDashboardView implements BaseView, MenuProvider, MessagesRenderer, ExceptionResolver {

    private UpdateModeratorController updateModeratorController = new UpdateModeratorController();
    private DeleteModeratorController deleteModeratorController = new DeleteModeratorController();

    private User sessionUser;
//    private List<Message> messageList;


    public ModeratorDashboardView(User sessionUser/*, List<Message> messageList*/) {
        this.sessionUser = sessionUser;
//        this.messageList = messageList;
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
            case ADMINISTRATOR:
                availableOptions.put(3L, "Delete Message");
                break;
        }

        while (true) {
            long choice = provideSelectionMenu(availableOptions);
            if (choice == 0) {
                return;
            } else if (choice == 1) {
//                printMessages(messageList);
                //readModeratorController.showAllMessages(sessionUser)
            } else if (choice == 2) {
                //TODO SELECT AND EDIT A MESSAGE - SAME AS EDITING A USER MORE OR LESS
                //updateModeratorController.getUpdateMessageView(sessionUser)
            } else if (choice == 3) {
//                Map<Long, String> availableMessagesToDelete = messageList.stream()
//                        .collect(Collectors.toMap(Message::getId, (message) -> message.getSubject() + " " + DateTimeFormatter.ISO_DATE_TIME.format(message.getDateOfSubmission())));
//                long chosenId = provideSelectionMenu(availableMessagesToDelete);
//                try {
//                    deleteModeratorController.deleteMessage(sessionUser, chosenId);
//                    System.out.println("Successfully deleted message with id " + chosenId);
//                } catch (Exception e) {
//                    handleException(e);
//                    System.out.println("Failed to delete user.");
//                }
                //deleteModeratorController.getDeleteMessageView(sessionUser)
            }
        }
    }

    @Override
    public void showSpecificDetailsForMessage(Message message) {
        System.out.println(String.format("\tFrom: %s %s", message.getSender().getFirstName(), message.getSender().getLastName()));
        System.out.println(String.format("\tTo: %s %s\n", message.getReceiver().getFirstName(), message.getReceiver().getLastName()));
    }
}
