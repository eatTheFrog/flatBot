package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates;

import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;

import javax.annotation.PostConstruct;

@Component
public class StartingState extends DialogState {

    @Override
    public void fillStateMap() {
    }

    @Override
    public DialogState getNextState(String userInput, DialogStatePosition dialogStatePosition) {
        return getMainMenu(dialogStatePosition);
    }

    @Override
    public String getOutPrompt(DialogStatePosition dialogStatePosition) {
        return getInPrompt(dialogStatePosition);
    }

    @Override
    public String[] getResponse(String userInput, DialogStatePosition dialogStatePosition) {
        return new String[]{getInPrompt(dialogStatePosition)};
    }
}