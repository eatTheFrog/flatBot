package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
    MainMenuDialogState mainMenuDialogState;

    HashMap<String, DialogState> nextStatesMap;

    public abstract void fillStateMap();

    public Boolean isLogged(LoginInstance loginInstance) {
        return loginValidChecker.checkValidLogin(loginInstance);
    }

    public DialogState getNextState(String userInput) {
        return nextStatesMap.getOrDefault(userInput, unknownDialogState);
    }

    public String getInPrompt() {
        return finalStringProvider
                .getFinalString(this.getClass().getSimpleName());
    }

    public abstract String getOutPrompt();

    public abstract String[] getResponse(String userInput, DialogState previousDialogState);
}
