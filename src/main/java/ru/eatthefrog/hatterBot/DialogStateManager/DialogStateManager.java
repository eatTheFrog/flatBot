package ru.eatthefrog.hatterBot.DialogStateManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.*;
import ru.eatthefrog.hatterBot.MongoDBOperator.MongoUserStatesManager;
import ru.eatthefrog.hatterBot.TelegramMessage;

import java.util.*;

@Component
public class DialogStateManager {
    @Autowired
    StartingState startingState;

    @Autowired
    MongoUserStatesManager mongoUserStatesManager;

    @Autowired
    LoggedMainMenu loggedMainMenu;

    @Autowired
    UnloggedMainMenu unloggedMainMenu;

    public Dictionary<Integer, DialogStatePosition> getStatePositionDict() {
        return statePositionDict;
    }

    @Autowired
    Dictionary<Integer, DialogStatePosition> statePositionDict;


    public TelegramMessage[] processTelegramMessage(TelegramMessage tm) {
        DialogStatePosition dialogStatePosition = getUserDialogStatePosition(tm.chatID);
        String[] newMessageTexts = updatePositionAndFetchResponses(tm.messageText, dialogStatePosition);
        TelegramMessage[] telegramMessages = new TelegramMessage[newMessageTexts.length];
        for (var i = 0; i < newMessageTexts.length; i++)
            if (! (newMessageTexts[i] == null || newMessageTexts[i] == ""))
                telegramMessages[i] = new TelegramMessage(newMessageTexts[i], tm.chatID);
        return telegramMessages;
    }

    public String[] updatePositionAndFetchResponses(String userInput, DialogStatePosition dsp){
        dsp.previousDialogState = dsp.dialogState;
        dsp.dialogState = dsp
                .dialogState
                .getNextState(userInput);
        if (dsp.dialogState instanceof MainMenuDialogState){
            dsp.dialogState = dsp.loginInstance.getIsValid()
                    ? loggedMainMenu
                    : unloggedMainMenu;
        }
        return new String[]{
                dsp.previousDialogState.getOutPrompt(),
                dsp.dialogState.getInPrompt()
        };
    }

    DialogStatePosition getUserDialogStatePosition(int chatID) {
        DialogStatePosition dsp = statePositionDict.get(chatID);
        if (dsp == null) {
            dsp = mongoUserStatesManager.getStatePosition(chatID);
            if (dsp == null) {
                dsp = new DialogStatePosition(startingState, chatID);
                statePositionDict.put(chatID, dsp);
            }
        }
        return dsp;
    }
}