package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;
import ru.eatthefrog.hatterBot.FinalStringProvider.FinalStringProvider;
import ru.eatthefrog.hatterBot.LoginManager.LoginInstance;
import ru.eatthefrog.hatterBot.LoginManager.LoginValidChecker;
import ru.eatthefrog.hatterBot.TelegramChatSTDOUT;
import ru.eatthefrog.hatterBot.Tools.UnknownTool;

@Component
public abstract class DialogState {
    String initString;

    String dialogStateIdentifier;

    @Autowired
    LoginValidChecker loginValidChecker;

    @Autowired
    TelegramChatSTDOUT telegramChatSTDOUT;

    @Autowired
    UnknownTool unknownTool;

    @Autowired
    LoggedMainMenuDialogState loggedMainMenuDialogState;

    @Autowired
    UnloggedMainMenuDialogState unloggedMainMenuDialogState;

    @Autowired
    FinalStringProvider finalStringProvider;

    public Boolean isLogged(LoginInstance loginInstance) {
        return loginValidChecker.checkValidLogin(loginInstance);
    }

    public abstract DialogState moveOtherState(String userInput, DialogStatePosition dialogStatePosition);

    public DialogState getNextMenuState(DialogStatePosition dialogStatePosition) {
        return (isLogged(dialogStatePosition.loginInstance))
                ? loggedMainMenuDialogState
                : unloggedMainMenuDialogState;
    }

    public void stdOutUnknownTool(int chatID) {
        telegramChatSTDOUT.printInChat(
                unknownTool.getExecuteOut(),
                 chatID
        );
    }

    public String getInitString() {
        return (initString == null)
                ? initString = finalStringProvider.getFinalString(dialogStateIdentifier + "InitString")
                : initString;
    }
}
