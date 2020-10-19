package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.CoreStates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;

@Component
public class LoginAskLoginDialogState extends DialogState {
    @Autowired
    LoginAskPasswordDialogState loginAskPasswordDialogState;

    public DialogState getNextState(String userInput, DialogStatePosition dsp) {
        dsp.loginInstance.setLogin(userInput);
        return loginAskPasswordDialogState.sendPromptAndYourself(dsp);
    }

    @Override
    public void fillStateMap() {
    }
}