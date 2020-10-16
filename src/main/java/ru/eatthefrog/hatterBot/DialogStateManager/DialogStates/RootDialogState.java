package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;

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

    public DialogState moveOtherState(String userInput, DialogStatePosition dialogStatePosition) {
        if (dialogStatePosition.recentlyCreated) {
            dialogStatePosition.recentlyCreated = false;
            return this;
        }
        else
            return getNextMenuState(dialogStatePosition);
    }
}
