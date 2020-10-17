package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;

import javax.annotation.PostConstruct;

@Component
public class LoginAskLoginDialogState extends DialogState {
    @PostConstruct
    void initIdentifier() {
        dialogStateIdentifier = "loginAskLoginDialogState";
    }
    @Autowired
    LoginAskPasswordDialogState loginAskPasswordDialogState;
    @Override
    public DialogState moveOtherState(String userInput, DialogStatePosition dialogStatePosition) {
        dialogStatePosition.loginInstance.setLogin(userInput);
        return loginAskPasswordDialogState;
    }
}
