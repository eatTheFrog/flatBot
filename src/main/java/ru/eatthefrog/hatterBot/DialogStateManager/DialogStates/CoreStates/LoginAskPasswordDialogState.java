package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.CoreStates;

import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.CoreStates.DialogState;

@Component
public class LoginAskPasswordDialogState extends DialogState {

    public DialogState getNextState(String userInput, DialogStatePosition dsp) {
        dsp.loginInstance.setPassword(userInput);
        loginValidChecker.checkValidLoginInMongoAndUpdateVerification(dsp.loginInstance);
        if (! isLogged(dsp.loginInstance)){
            sendResponse("Sorry, but something's wrong with your login or password!", dsp);
        }
        return getMainMenu(dsp);
    }

    @Override
    public void fillStateMap() {
    }
}
