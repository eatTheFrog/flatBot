package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;

import javax.annotation.PostConstruct;

@Component
public class LoggedMainMenu extends MainMenuDialogState {
    @Autowired
    InfoState infoState;

    @Autowired
    EchoState echoState;

    @Autowired
    LogoutState logoutState;

    @Autowired
    NetworkingToolsMenuState networkingToolsMenuState;

    @PostConstruct
    public void fillStateMap() {
        nextStatesMap.put("/networking", networkingToolsMenuState );
        nextStatesMap.put("/info", infoState);
        nextStatesMap.put("/echo", echoState);
        nextStatesMap.put("/settings", notYetImplementedState);
        nextStatesMap.put("/logout", logoutState);
    }

    @Override
    public DialogState getNextState(String userInput, DialogStatePosition dsp) {
        DialogState nextState = super.getNextState(userInput, dsp);
        System.out.println(nextState.getClass().getSimpleName());
        return nextState;
    }
}
