package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.NmapStates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.CoreStates.DialogState;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.NmapStates.NmapState;
import ru.eatthefrog.hatterBot.Tools.Nmapor;

@Component
public class NmapPortState extends DialogState {
    @Override
    public void fillStateMap() {
    }

    @Autowired
    NmapState nmapState;

    @Override
    public DialogState getNextState(String userInput, DialogStatePosition dsp) {
        Nmapor.nmapPort(dsp, apiProvider, userInput);
        return nmapState.sendPromptAndYourself(dsp);
    }
}
