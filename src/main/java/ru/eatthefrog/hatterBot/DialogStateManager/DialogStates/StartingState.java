package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class StartingState extends DialogState {

    @PostConstruct
    @Override
    public void fillStateMap() {
        nextStatesMap.put("/1", mainMenuDialogState);
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
