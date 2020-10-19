package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;

import javax.annotation.PostConstruct;

@Component
public class EchoState extends DialogState {

    @PostConstruct
    public void fillStateMap(){
    }

    @Override
    public DialogState getNextState(String userInput, DialogStatePosition dialogStatePosition){
        System.out.println(userInput);
        return userInput.equals("putin")
                ? getMainMenu(dialogStatePosition)
                : this;
    }

    @Override
    public String getOutPrompt(DialogStatePosition dialogStatePosition){
        return null;
    }

    @Override
    public String[] getResponse(String userInput, DialogStatePosition dialogStatePosition){
        return new String[]{ dialogStatePosition.previousDialogState instanceof EchoState
                ? userInput
                : getInPrompt(dialogStatePosition)};
    }
}
