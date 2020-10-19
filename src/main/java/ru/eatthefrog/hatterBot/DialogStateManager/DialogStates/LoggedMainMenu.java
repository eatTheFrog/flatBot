package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoggedMainMenu extends MainMenuDialogState {
    @Autowired
    NotYetImplementedState notYetImplementedState;

    @Autowired
    InfoState infoState;

    @Autowired
    EchoState echoState;

    @Override
    public void fillStateMap() {
        nextStatesMap.put("1", notYetImplementedState);
        nextStatesMap.put("2", infoState);
        nextStatesMap.put("3", echoState);
    }
}
