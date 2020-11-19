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
    PingBurstState pingBurstState;

    @Autowired
    RegistrationAskLoginDialogState registrationAskLoginDialogState;

    @PostConstruct
    public void fillStateMap() {
        nextStatesMap.put("/login", loginAskLoginDialogState);
        nextStatesMap.put("/register", registrationAskLoginDialogState);
        nextStatesMap.put("/info", infoState);
        nextStatesMap.put("/echo", echoState);
    }
}
