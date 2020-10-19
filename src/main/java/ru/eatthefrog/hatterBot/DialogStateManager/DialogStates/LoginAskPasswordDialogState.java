package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;

@Component
public class LoginAskPasswordDialogState extends DialogState {

    public DialogState moveToOtherState(String userInput, DialogStatePosition dialogStatePosition) {
        dialogStatePosition.loginInstance.password = userInput;
        loginValidChecker.checkValidLoginInMongoAndUpdateVerification(dialogStatePosition.loginInstance);
        if (isLogged(dialogStatePosition.loginInstance)) {
            telegramChatSTDOUT.printInChat("You have logged!", dialogStatePosition.chatID);
        }
        else {
            telegramChatSTDOUT.printInChat("Login isn't correct", dialogStatePosition.chatID);
        }
        return getMenuState(dialogStatePosition);
    }

    @Override
    public void fillStateMap() {

    }

    @Override
    public String getResponse(String userInput, DialogState previousDialogState) {
        return null;
    }
}
