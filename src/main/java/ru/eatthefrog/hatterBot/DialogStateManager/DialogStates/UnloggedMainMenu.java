package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UnloggedMainMenu extends MainMenuDialogState {
    @Autowired
    LoginAskLoginDialogState loginAskLoginDialogState;

    @Autowired
    InfoState infoState;

    @Autowired
    EchoState echoState;

    @Autowired
    NotYetImplementedState notYetImplementedState;

    @Autowired
    RegistrationAskLoginDialogState registrationAskLoginDialogState;

    @PostConstruct
    public void fillStateMap() {
        nextStatesMap.put("1", notYetImplementedState);
        nextStatesMap.put("2", notYetImplementedState);
        nextStatesMap.put("3", infoState);
        nextStatesMap.put("4", echoState);
    }
}
