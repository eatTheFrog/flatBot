package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.CoreStates;

import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.CoreStates.DialogState;

@Component
public class RegistrationAskPasswordDialogState extends DialogState {

    public DialogState getNextState(String userInput, DialogStatePosition dialogStatePosition) {
        dialogStatePosition.loginInstance.setPassword(userInput);
        loginValidChecker.rememberLoginInDB(dialogStatePosition.loginInstance);
        loginValidChecker.checkValidLoginInMongoAndUpdateVerification(dialogStatePosition.loginInstance);
        return getMainMenu(dialogStatePosition);
    }

    @Override
    public void fillStateMap() {
    }
}