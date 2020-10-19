package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;
import ru.eatthefrog.hatterBot.FinalStringProvider.FinalStringProvider;
import ru.eatthefrog.hatterBot.LoginManager.LoginInstance;
import ru.eatthefrog.hatterBot.LoginManager.LoginValidChecker;

import java.util.HashMap;

@Component
public abstract class DialogState {

    @Autowired
    LoginValidChecker loginValidChecker;

    @Autowired
    FinalStringProvider finalStringProvider;

    @Autowired
    UnknownDialogState unknownDialogState;

    @Autowired
    LoggedMainMenu loggedMainMenu;

    @Autowired
    UnloggedMainMenu unloggedMainMenu;

    @Autowired
    HashMap<String, DialogState> nextStatesMap;

    public MainMenuDialogState getMainMenu(DialogStatePosition dialogStatePosition){
        return dialogStatePosition.loginInstance.getIsValid()
                ? loggedMainMenu
                : unloggedMainMenu;
    }

    public abstract void fillStateMap();

    public Boolean isLogged(LoginInstance loginInstance) {
        return loginValidChecker.checkValidLogin(loginInstance);
    }

    public DialogState getNextState(String userInput, DialogStatePosition dialogStatePosition) {
        return nextStatesMap.getOrDefault(userInput, unknownDialogState);
    }

    public String getInPrompt(DialogStatePosition dialogStatePosition) {
        return finalStringProvider
                .getFinalString(this.getClass().getSimpleName());
    }

    public abstract String getOutPrompt(DialogStatePosition dialogStatePosition);

    public abstract String[] getResponse(String userInput, DialogStatePosition dialogStatePosition);
}
