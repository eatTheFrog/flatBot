package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates;

public class InfoState extends DialogState
{
    @Override
    public void fillStateMap() {
    }

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
