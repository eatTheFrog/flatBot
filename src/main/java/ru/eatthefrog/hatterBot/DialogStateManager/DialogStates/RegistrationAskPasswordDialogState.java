package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;

@Component
public class RegistrationAskPasswordDialogState extends DialogState {

    public DialogState getNextState(String userInput, DialogStatePosition dialogStatePosition) {
        dialogStatePosition.loginInstance.password = userInput;
        loginValidChecker.rememberLoginInDB(dialogStatePosition.loginInstance);
        loginValidChecker.checkValidLoginInMongoAndUpdateVerification(dialogStatePosition.loginInstance);
        return getMainMenu(dialogStatePosition);
    }

    @Override
    public void fillStateMap() {

    }

    @Override
    public String getOutPrompt(DialogStatePosition dialogStatePosition) {
        return String.format("User %s registered.", dialogStatePosition.loginInstance.login);
    }

    @Override
    public String[] getResponse(String userInput, DialogStatePosition dialogStatePosition) {
        return new String[]{getInPrompt(dialogStatePosition)};
    }
}
