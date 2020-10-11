package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class  RegistrationDialogStateEnabler {

    @Autowired
    RegistrationAskLoginDialogState registrationAskLoginDialogState;

    public DialogState getRegistrationState() {
        return registrationAskLoginDialogState;
    }
}
