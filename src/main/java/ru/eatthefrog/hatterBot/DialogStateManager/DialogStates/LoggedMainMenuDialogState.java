package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class LoggedMainMenuDialogState extends AbstractMainMenuDialogState {
    @PostConstruct
    void initIdentifier() {
        dialogStateIdentifier = "loggedMainMenuDialogState";
    }
}
