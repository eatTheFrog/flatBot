package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates;

import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;

@Component
public class InfoState extends DialogState
{
    @Override
    public void fillStateMap() {
    }

    public DialogState getNextState(String userInput, DialogStatePosition dialogStatePosition){
        return getMainMenu(dialogStatePosition);
    }

    @Override
    public String getOutPrompt(DialogStatePosition dialogStatePosition) {
        return null;
    }

    @Override
    public String[] getResponse(String userInput, DialogStatePosition dialogStatePosition) {
        return skipToMenuAndGetCombinedResponse(userInput, dialogStatePosition);
    }
}
