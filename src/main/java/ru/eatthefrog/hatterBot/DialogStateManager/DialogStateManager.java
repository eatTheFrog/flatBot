package ru.eatthefrog.hatterBot.DialogStateManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.RootDialogState;
import ru.eatthefrog.hatterBot.TelegramMessage;

import java.util.*;

@Component
public class DialogStateManager {
    @Autowired
    RootDialogState rootDialogState;
    Dictionary<Integer, UserDialogStatePosition> statePositionDict = new Hashtable<>();

    public TelegramMessage statefulMessageProcess(TelegramMessage telegramMessage) {
        int chatID = telegramMessage.chatID;
        UserDialogStatePosition userStatePosition = getUserDialogStatePosition(chatID);
        String newMessageText = userStatePosition.makeStep(telegramMessage.messageText);

        return new TelegramMessage(newMessageText, chatID);
    }

    UserDialogStatePosition getUserDialogStatePosition(int chatID) {
        UserDialogStatePosition userDialogStatePosition = statePositionDict.get(chatID);
        if (userDialogStatePosition == null) {
            userDialogStatePosition = new UserDialogStatePosition(rootDialogState, chatID);
            statePositionDict.put(chatID, userDialogStatePosition);
        }
        return userDialogStatePosition;
    }
}
