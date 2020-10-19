package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates;

import org.springframework.stereotype.Component;

@Component
public class NotYetImplementedState extends DialogState{
    @Override
    public void fillStateMap() {
    }

    public DialogState getNextState(String userInput){
        return mainMenuDialogState;
    }

    @Override
    public String[] getResponse(String userInput, DialogState previousDialogState) {
        return new String[]{getInPrompt()};
    }
}
