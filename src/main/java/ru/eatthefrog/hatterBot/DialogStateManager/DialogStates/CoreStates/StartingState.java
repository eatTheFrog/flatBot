package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.CoreStates;

import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.CoreStates.DialogState;

@Component
public class StartingState extends DialogState {

    @Override
    public void fillStateMap() {
    }

    @Override
    public DialogState getNextState(String userInput, DialogStatePosition dsp) {
        return getMainMenu(dsp);
    }
}
