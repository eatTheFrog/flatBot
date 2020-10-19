package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UnloggedMainMenu extends MainMenuDialogState {
    @Autowired
    InfoState infoState;

    @Autowired
    EchoState echoState;

    @Autowired
    LoginAskLoginDialogState loginAskLoginDialogState;

    @Autowired
    RegistrationAskLoginDialogState registrationAskLoginDialogState;

    @PostConstruct
    public void fillStateMap() {
        nextStatesMap.put("1", loginAskLoginDialogState);
        nextStatesMap.put("2", registrationAskLoginDialogState);
        nextStatesMap.put("3", infoState);
        nextStatesMap.put("4", echoState);
    }
}
