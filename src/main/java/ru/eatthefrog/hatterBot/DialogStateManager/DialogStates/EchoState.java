package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class EchoState extends DialogState {

    @Autowired
    MainMenuDialogState mainMenuDialogState;

    @Autowired
    EchoState echoState;

    @PostConstruct
    public void fillStateMap(){
        nextStatesMap.put("putin", mainMenuDialogState);
    }

    @Override
    public DialogState getNextState(String userInput){
        return nextStatesMap.getOrDefault(userInput, echoState);
    }

    @Override
    public String getOutPrompt() {
        return null;
    }

    @Override
    public String[] getResponse(String userInput, DialogState previousState){
        return new String[]{ previousState instanceof EchoState
                ? userInput
                : getInPrompt()};
    }
}
