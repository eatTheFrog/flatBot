package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.UserDialogStatePosition;

import javax.annotation.PostConstruct;

@Component
public class RootDialogState extends DialogState {
    @PostConstruct
    void initIdentifier() {
        dialogStateIdentifier = "rootDialogState";
    }

    @Autowired
    UnloggedMainMenuDialogState unloggedMainMenuDialogState;

    @Autowired
    LoggedMainMenuDialogState loggedMainMenuDialogState;

    public DialogState moveOtherState(String arg, UserDialogStatePosition userDialogStatePosition) {
        if (userDialogStatePosition.recentCreated) {
            userDialogStatePosition.recentCreated = false;
            return this;
        }
        else {
            return getNextMenuState(userDialogStatePosition);
        }
    }

}
