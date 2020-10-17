package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;
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
    public DialogState moveOtherState(String userInput, DialogStatePosition dialogStatePosition) {
        dialogStatePosition.loginInstance.setPassword(userInput);
        loginValidChecker.rememberLoginInDB(dialogStatePosition.loginInstance);
        loginValidChecker.checkValidLoginInMongoAndUpdateVerification(dialogStatePosition.loginInstance);
        telegramChatSTDOUT.printInChat(
                "You have made registration.",
                dialogStatePosition.chatID
        );
        return getNextMenuState(dialogStatePosition);
    }


}
