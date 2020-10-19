package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates;

import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;

public abstract class MainMenuDialogState extends DialogState {

    @Override
    public void fillStateMap() { }

    @Override
    public String getOutPrompt(DialogStatePosition dialogStatePosition) {
        return null;
    }

    @Override
    public String[] getResponse(String userInput, DialogStatePosition dialogStatePosition) {
        return new String[]{getInPrompt(dialogStatePosition)};
    }
}
