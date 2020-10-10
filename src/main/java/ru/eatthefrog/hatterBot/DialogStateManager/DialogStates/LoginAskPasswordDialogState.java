package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.UserDialogStatePosition;
import ru.eatthefrog.hatterBot.TelegramChatSTDOUT;

import javax.annotation.PostConstruct;

@Component
public class LoginAskPasswordDialogState extends DialogState {
    @PostConstruct
    void initIdentifier() {
        dialogStateIdentifier = "loginAskPasswordDialogState";
    }
    @Autowired
    TelegramChatSTDOUT telegramChatSTDOUT;

    public DialogState moveOtherState(String arg, UserDialogStatePosition userDialogStatePosition) {
        userDialogStatePosition.loginInstance.password = arg;
        if (isLogged(userDialogStatePosition.loginInstance)) {
            telegramChatSTDOUT.printInChat("You have logged!", userDialogStatePosition.chatID);
        }
        else {
            telegramChatSTDOUT.printInChat("Login isn't correct", userDialogStatePosition.chatID);
        }
        return getNextMenuState(userDialogStatePosition);
    }

}
