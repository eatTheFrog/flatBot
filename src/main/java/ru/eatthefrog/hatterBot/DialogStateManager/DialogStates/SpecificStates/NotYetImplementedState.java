package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.SpecificStates;

import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.CoreStates.DialogState;

@Component
public class NotYetImplementedState extends DialogState {
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
