package ru.eatthefrog.hatterBot.DialogStateManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.CoreStates.DialogState;
import ru.eatthefrog.hatterBot.LoginManager.LoginInstance;
import ru.eatthefrog.hatterBot.LoginManager.LoginInstanceFactory;

import javax.annotation.PostConstruct;

@Component
@Scope("prototype")
public class DialogStatePosition {
    @Autowired
    LoginInstanceFactory loginInstanceFactory;
    public LoginInstance loginInstance;
    public DialogState dialogState;
    public Boolean recentlyCreated = true;
    public Boolean isIterating = false;
    public int chatID;
    public long lastTimeTouched;

    public void setDialogState(DialogState dialogState) {
        this.dialogState = dialogState;
    }

    public void setChatID(int chatID) {
        this.chatID = chatID;
    }
    @PostConstruct
    public void postMethod() {
        lastTimeTouched = System.currentTimeMillis();

        loginInstance = loginInstanceFactory.getLoginInstance();
    }
}
