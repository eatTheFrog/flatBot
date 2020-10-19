package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;

@Component
public class MainMenuDialogState extends DialogState {

    @Override
    public void fillStateMap() {
    }

    public String[] getResponse(String userInput, DialogState previousDialogState) {
        return null;
    }
}
