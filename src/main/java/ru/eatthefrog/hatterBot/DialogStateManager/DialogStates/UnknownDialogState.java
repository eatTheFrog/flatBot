package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates;

import org.springframework.stereotype.Component;

@Component
public class UnknownDialogState extends DialogState {

    @Override
    public void fillStateMap() {
    }

    @Override
    public DialogState getNextState(String userInput){
        return mainMenuDialogState;
    }

    @Override
    public String getOutPrompt() {
        return null;
    }

    @Override
    public String[] getResponse(String userInput, DialogState previousDialogState) {
        return new String[]{getInPrompt()};
    }
}
