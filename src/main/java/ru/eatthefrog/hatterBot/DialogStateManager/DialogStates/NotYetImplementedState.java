package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates;

import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;

@Component
public class NotYetImplementedState extends DialogState{
    @Override
    public void fillStateMap() {
    }

    @Override
    public boolean isMoveForward() {
        return true;
    }

    @Override
    public DialogState getNextState(String userInput, DialogStatePosition dsp) {
        return getMainMenu(dsp);
    }
}
