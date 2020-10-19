package ru.eatthefrog.hatterBot.DialogStateManager;

import ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.DialogState;
import ru.eatthefrog.hatterBot.LoginManager.LoginInstance;

public class DialogStatePosition {
    public LoginInstance loginInstance;
    public DialogState dialogState;
    public DialogState previousDialogState;
    public Boolean recentlyCreated = true;
    public int chatID;
    public long lastTimeTouched;

    public DialogStatePosition(DialogState dialogState, int chatID) {
        lastTimeTouched = System.currentTimeMillis();
        this.dialogState = dialogState;
        this.chatID = chatID;
        loginInstance = new LoginInstance();
    }
}
