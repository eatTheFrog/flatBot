package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates;

import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.UserDialogStatePosition;

import javax.annotation.PostConstruct;

@Component
public class UnloggedMainMenuDialogState extends AbstractMainMenuDialogState {
    @PostConstruct
    void initIdentifier() {
        dialogStateIdentifier = "unloggedMainMenuDialogState";
    }
}
