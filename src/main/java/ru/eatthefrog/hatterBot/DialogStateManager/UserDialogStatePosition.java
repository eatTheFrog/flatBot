package ru.eatthefrog.hatterBot.DialogStateManager;

import ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.DialogState;
import ru.eatthefrog.hatterBot.LoginManager.LoginInstance;

public class UserDialogStatePosition {
    public LoginInstance loginInstance;
    DialogState userDialogState = null;
    public Boolean recentCreated = true;
    public int chatID;
    public long lastTimeTouched;
    public UserDialogStatePosition(DialogState userDialogStateArg, int chatIDArg) {
        lastTimeTouched = System.currentTimeMillis();
        userDialogState = userDialogStateArg;
        chatID = chatIDArg;
        loginInstance = new LoginInstance();
    }

    public String makeStep(String arg) {
        userDialogState = userDialogState.moveOtherState(arg, this);

        return userDialogState.getInitString();
    }
}
