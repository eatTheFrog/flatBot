package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.UserDialogStatePosition;

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
    public DialogState moveOtherState(String arg, UserDialogStatePosition userDialogStatePosition) {
        userDialogStatePosition.loginInstance.login = arg;
        return loginAskPasswordDialogState;
    }
}
