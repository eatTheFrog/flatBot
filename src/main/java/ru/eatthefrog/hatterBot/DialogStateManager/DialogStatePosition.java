package ru.eatthefrog.hatterBot.DialogStateManager;

import ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.CoreStates.DialogState;
import ru.eatthefrog.hatterBot.LoginManager.LoginInstance;
import ru.eatthefrog.hatterBot.LoginManager.LoginInstanceFactory;

public class DialogStatePosition {
    public LoginInstance loginInstance;
    public DialogState dialogState;
    public Boolean recentlyCreated = true;
    public Boolean isIterating = false;
    public int chatID;
    public long lastTimeTouched;

    public DialogStatePosition(DialogState dialogState, int chatID) {
        lastTimeTouched = System.currentTimeMillis();
        this.dialogState = dialogState;
        this.chatID = chatID;
        loginInstance = LoginInstanceFactory.getLoginInstance(null, null);
    }
}
