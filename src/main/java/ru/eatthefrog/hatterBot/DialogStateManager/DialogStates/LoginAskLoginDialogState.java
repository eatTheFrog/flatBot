package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;

import javax.annotation.PostConstruct;

@Component
public class LoginAskLoginDialogState extends DialogState {
    @Autowired
    LoginAskPasswordDialogState loginAskPasswordDialogState;

    public DialogState moveToOtherState(String userInput, DialogStatePosition dialogStatePosition) {
        dialogStatePosition.loginInstance.login = userInput;
        return loginAskPasswordDialogState;
    }

    @Override
    public void fillStateMap() {

    }

    @Override
    public String getOutPrompt() {
        return null;
    }

    @Override
    public String[] getResponse(String userInput, DialogState previousDialogState) {
        return new String[0];
    }
}
