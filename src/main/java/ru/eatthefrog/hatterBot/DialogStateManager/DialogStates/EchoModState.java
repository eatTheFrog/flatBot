package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.UserDialogStatePosition;
import ru.eatthefrog.hatterBot.TelegramChatSTDOUT;

import javax.annotation.PostConstruct;

@Component
public class EchoModState extends AbstractMainMenuDialogState {
    @Autowired
    TelegramChatSTDOUT telegramChatSTDOUT;
    @PostConstruct
    void initIdentifier() {
        dialogStateIdentifier = "echoModDialogState";
    }
    public DialogState moveOtherState(String arg, UserDialogStatePosition userDialogStatePosition) {
        switch (arg) {
            case "putin":
                return getNextMenuState(userDialogStatePosition);
            default:
                telegramChatSTDOUT.printInChat(arg, userDialogStatePosition.chatID);
                return this;
        }
    }
}
