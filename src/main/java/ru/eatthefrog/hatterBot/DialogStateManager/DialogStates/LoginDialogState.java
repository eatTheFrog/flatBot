package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;

import javax.annotation.PostConstruct;

@Component
public class LoginDialogState extends DialogState {
    @PostConstruct
    void initIdentifier() {
        dialogStateIdentifier = "loginDialogState";
    }

    @Autowired
    RegistrationDialogStateEnabler registrationEnablerDialogState;

    @Autowired
    LoginAskLoginDialogState loginAskLoginDialogState;

    public DialogState moveOtherState(String userInput, DialogStatePosition dialogStatePosition) {
        int chatID = dialogStatePosition.chatID;
        switch (userInput) {
            case "1":
                return loginAskLoginDialogState;
            case "2":
                return registrationEnablerDialogState.getRegistrationState();
            case "3":
                return getNextMenuState(dialogStatePosition);
            default:
                stdOutUnknownTool(chatID);
                return this;
        }
    }


}
