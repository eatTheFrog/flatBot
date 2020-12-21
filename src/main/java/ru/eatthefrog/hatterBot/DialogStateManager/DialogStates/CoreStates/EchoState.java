package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.CoreStates;

import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.CoreStates.DialogState;

import javax.annotation.PostConstruct;

@Component
public class EchoState extends DialogState {

    @PostConstruct
    public void fillStateMap(){
    }

    @Override
    public void sendPrompt(DialogStatePosition dsp) {
        super.sendPrompt(dsp);
    }

    @Override
    public DialogState getNextState(String userInput, DialogStatePosition dsp){
        if (userInput.equals("Putin"))
            return getMainMenu(dsp);
        sendResponse(userInput, dsp);
        return this;
    }
}
