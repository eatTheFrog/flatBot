package ru.eatthefrog.hatterBot.DialogStateManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.*;
import ru.eatthefrog.hatterBot.Message.Message;
import ru.eatthefrog.hatterBot.Message.MessageFactory;
import ru.eatthefrog.hatterBot.MongoDBOperator.MongoUserStatesManager;
import ru.eatthefrog.hatterBot.Message.TelegramMessage;

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


    public Message[] processTelegramMessage(Message tm) {
        DialogStatePosition dialogStatePosition = getUserDialogStatePosition(tm.getChatId());
        String[] newMessageTexts = updatePositionAndFetchResponses(tm.getMessageText(), dialogStatePosition);
        Message[] telegramMessages = new Message[newMessageTexts.length];
        for (var i = 0; i < newMessageTexts.length; i++)
            if (! (newMessageTexts[i] == null || newMessageTexts[i].equals("")))
                 telegramMessages[i] = MessageFactory.getNewMessage(newMessageTexts[i], tm.getChatId());

        return telegramMessages;
    }

    public String[] updatePositionAndFetchResponses(String userInput, DialogStatePosition dsp){
        dsp.previousDialogState = dsp
                .dialogState;
        dsp.dialogState = dsp
                .dialogState
                .getNextState(userInput, dsp);
        String[] responses = dsp.dialogState.getResponse(userInput, dsp);
        String[] fullResponses = new String[responses.length + 1];
        fullResponses[0] = dsp.previousDialogState.getOutPrompt(dsp);
        System.arraycopy(responses, 0, fullResponses, 1, responses.length);
        return fullResponses;
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