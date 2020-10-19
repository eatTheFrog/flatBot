package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;

@Component
public class LoginAskLoginDialogState extends DialogState {
    @Autowired
    LoginAskPasswordDialogState loginAskPasswordDialogState;

    public DialogState getNextState(String userInput, DialogStatePosition dialogStatePosition) {
        dialogStatePosition.loginInstance.login = userInput;
        return loginAskPasswordDialogState;
    }

    @Override
    public void fillStateMap() {
    }

    @Override
    public String getOutPrompt(DialogStatePosition dialogStatePosition) {
        return null;
    }

    @Override
    public String[] getResponse(String userInput, DialogStatePosition dialogStatePosition) {
        return new String[]{getInPrompt(dialogStatePosition)};
    }
}
