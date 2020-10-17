package ru.eatthefrog.hatterBot.DialogStateManager;

import org.springframework.beans.factory.annotation.Autowired;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.DialogState;
import ru.eatthefrog.hatterBot.LoginManager.LoginInstance;
import ru.eatthefrog.hatterBot.LoginManager.LoginInstanceFactory;

public class DialogStatePosition {

    public LoginInstance loginInstance;
    DialogState userDialogState;
    public Boolean recentlyCreated = true;
    public int chatID;
    public long lastTimeTouched;

    public DialogStatePosition(DialogState dialogState, int chatID) {
        lastTimeTouched = System.currentTimeMillis();
        userDialogState = dialogState;
        this.chatID = chatID;
        loginInstance = LoginInstanceFactory.getLoginInstance(null, null);
    }

    public String updateState(String userInput) {
        userDialogState = userDialogState.moveOtherState(userInput, this);
        return userDialogState.getInitString();
    }
}
