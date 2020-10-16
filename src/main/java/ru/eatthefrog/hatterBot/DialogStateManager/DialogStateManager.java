package ru.eatthefrog.hatterBot.DialogStateManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.RootDialogState;
import ru.eatthefrog.hatterBot.MongoDBOperator.MongoUserStatesManager;
import ru.eatthefrog.hatterBot.TelegramMessage;

import java.util.*;

@Component
public class DialogStateManager {
    @Autowired
    RootDialogState rootDialogState;

    @Autowired
    MongoUserStatesManager mongoUserStatesManager;

    public Dictionary<Integer, DialogStatePosition> getStatePositionDict() {
        return statePositionDict;
    }

    @Autowired
    Dictionary<Integer, DialogStatePosition> statePositionDict;


    public TelegramMessage processTelegramMessage(TelegramMessage tm) {
        DialogStatePosition dialogStatePosition = getUserDialogStatePosition(tm.chatID);
        String newMessageText = dialogStatePosition.updateState(tm.messageText);
        return new TelegramMessage(newMessageText, tm.chatID);
    }

    DialogStatePosition getUserDialogStatePosition(int chatID) {
        DialogStatePosition dialogStatePosition = statePositionDict.get(chatID);
        if (dialogStatePosition == null) {
            dialogStatePosition = mongoUserStatesManager.getStatePosition(chatID);
            if (dialogStatePosition == null) {
                dialogStatePosition = new DialogStatePosition(rootDialogState, chatID);
                statePositionDict.put(chatID, dialogStatePosition);
            }
        }
        return dialogStatePosition;
    }
}
