package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;
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

    public DialogState moveOtherState(String userInput, DialogStatePosition dialogStatePosition) {
        dialogStatePosition.loginInstance.password = userInput;
        loginValidChecker.checkValidLoginInMongoAndUpdateVerification(dialogStatePosition.loginInstance);
        if (isLogged(dialogStatePosition.loginInstance)) {
            telegramChatSTDOUT.printInChat("You have logged!", dialogStatePosition.chatID);
        }
        else {
            telegramChatSTDOUT.printInChat("Login isn't correct", dialogStatePosition.chatID);
        }
        return getNextMenuState(dialogStatePosition);
    }

}
