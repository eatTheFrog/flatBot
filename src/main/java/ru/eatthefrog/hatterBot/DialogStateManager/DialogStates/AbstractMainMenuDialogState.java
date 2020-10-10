package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.UserDialogStatePosition;
import ru.eatthefrog.hatterBot.Tools.InfoTool;
import ru.eatthefrog.hatterBot.Tools.PraiseTool;

@Component
public class AbstractMainMenuDialogState extends DialogState {
    @Autowired
    InfoTool infoTool;

    @Autowired
    PraiseTool praiseTool;

    @Autowired
    LoginDialogState loginDialogState;

    @Autowired
    EchoModState echoModState;

    void stdOutInfoTool(int chatID) {
        telegramChatSTDOUT.printInChat(
                infoTool.getExecuteOut(),
                chatID
        );
    }

    @Override
    public DialogState moveOtherState(String arg, UserDialogStatePosition userDialogStatePosition) {
        int chatID = userDialogStatePosition.chatID;
        switch (arg) {
            case "1":
                if (dialogStateIdentifier.equals("loggedMainMenuDialogState")) {
                    return this;
                }
                else {
                    return loginDialogState;
                }
            case "2":
                stdOutInfoTool(chatID);
                return getNextMenuState(userDialogStatePosition);
            case "3":
                return echoModState;
            default:
                stdOutUnknownTool(chatID);
                return getNextMenuState(userDialogStatePosition);
        }
    }
}
