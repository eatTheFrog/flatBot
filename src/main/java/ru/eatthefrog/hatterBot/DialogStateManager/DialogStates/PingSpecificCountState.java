package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;
import ru.eatthefrog.hatterBot.Tools.Pingomator;

import javax.annotation.PostConstruct;

@Component
public class PingSpecificCountState extends DialogState {
    @Autowired
    PingState pingState;

    @PostConstruct
    public void fillStateMap() {
    }

    @Override
    public DialogState getNextState(String userInput, DialogStatePosition dsp) {
        Pingomator.pingSpecific(dsp, apiProvider, userInput);
        return pingState.sendPromptAndYourself(dsp);
    }
}
