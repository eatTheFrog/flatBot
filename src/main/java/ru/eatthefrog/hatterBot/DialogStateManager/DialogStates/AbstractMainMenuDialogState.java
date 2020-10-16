package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;
import ru.eatthefrog.hatterBot.Tools.InfoTool;
import ru.eatthefrog.hatterBot.Tools.PraiseTool;

@Component
public abstract class AbstractMainMenuDialogState extends DialogState {
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
    public DialogState moveOtherState(String userInput, DialogStatePosition dialogStatePosition) {
        int chatID = dialogStatePosition.chatID;
        switch (userInput) {
            case "1":
                if (dialogStateIdentifier.equals("loggedMainMenuDialogState")) {
                    return this;
                }
                else {
                    return loginDialogState;
                }
            case "2":
                stdOutInfoTool(chatID);
                return getNextMenuState(dialogStatePosition);
            case "3":
                return echoModState;
            default:
                stdOutUnknownTool(chatID);
                return getNextMenuState(dialogStatePosition);
        }
    }
}
