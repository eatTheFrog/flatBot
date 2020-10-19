package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates;

import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;

@Component
public class LoginAskPasswordDialogState extends DialogState {

    public DialogState getNextState(String userInput, DialogStatePosition dialogStatePosition) {
        dialogStatePosition.loginInstance.password = userInput;
        loginValidChecker.checkValidLoginInMongoAndUpdateVerification(dialogStatePosition.loginInstance);
        return getMainMenu(dialogStatePosition);
    }

    @Override
    public void fillStateMap() {

    }

    @Override
    public String getOutPrompt(DialogStatePosition dialogStatePosition) {
        return dialogStatePosition.loginInstance.getIsValid()
                ? "Successfully logined."
                : "Authorization failed.";
    }

    @Override
    public String[] getResponse(String userInput, DialogStatePosition dialogStatePosition) {
        return new String[]{getInPrompt(dialogStatePosition)};
    }
}
