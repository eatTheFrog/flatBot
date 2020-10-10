package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.UserDialogStatePosition;
import ru.eatthefrog.hatterBot.TelegramChatSTDOUT;

import javax.annotation.PostConstruct;

@Component
public class RegistrationAskPasswordDialogState extends DialogState {
    @PostConstruct
    void initIdentifier() {
        dialogStateIdentifier = "registrationAskPasswordDialogState";
    }
    @Autowired
    TelegramChatSTDOUT telegramChatSTDOUT;
    public DialogState moveOtherState(String arg, UserDialogStatePosition userDialogStatePosition) {
        userDialogStatePosition.loginInstance.password = arg;
        loginValidChecker.rememberLoginInDB(userDialogStatePosition.loginInstance);
        loginValidChecker.checkValidLoginInDB(userDialogStatePosition.loginInstance);
        telegramChatSTDOUT.printInChat(
                "You have made registration.",
                userDialogStatePosition.chatID
        );
        return getNextMenuState(userDialogStatePosition);
    }


}
