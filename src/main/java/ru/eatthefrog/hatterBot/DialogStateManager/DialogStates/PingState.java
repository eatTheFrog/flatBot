package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;

import javax.annotation.PostConstruct;

@Component
public class PingState extends DialogState {
    @Autowired
    NetworkingToolsMenuState networkingToolsMenuState;

    @Autowired
    PingSpecificCountState pingSpecificCountState;

    @Autowired
    PingBurstState pingBurstState;

    @PostConstruct
    public void fillStateMap() {
        nextStatesMap.put("/burst", pingBurstState);
        nextStatesMap.put("/specific", pingSpecificCountState);
        nextStatesMap.put("/back", networkingToolsMenuState);
    }

    @Override
    public DialogState getNextState(String userInput, DialogStatePosition dsp) {
        return super.getNextState(userInput, dsp);
    }
}
