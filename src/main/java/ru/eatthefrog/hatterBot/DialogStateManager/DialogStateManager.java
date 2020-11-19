package ru.eatthefrog.hatterBot.DialogStateManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.LoggedMainMenu;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.StartingState;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.UnloggedMainMenu;
import ru.eatthefrog.hatterBot.Message.Message;
import ru.eatthefrog.hatterBot.MongoDBOperator.MongoUserStatesManager;

import java.util.AbstractMap;

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

    public AbstractMap<Integer, DialogStatePosition> getStatePositionDict() {
        return statePositionDict;
    }

    @Autowired
    AbstractMap<Integer, DialogStatePosition> statePositionDict;


    public void processTelegramMessage(Message tm) {
        if (tm.getMessageText() == null)
            return;
        DialogStatePosition dsp = getUserDialogStatePosition(tm.getChatId());
        updatePositionAndSendResponse(tm.getMessageText(), dsp);
    }

    public void updatePositionAndSendResponse(String userInput, DialogStatePosition dsp){
        dsp.dialogState = dsp
                .dialogState
                .getNextState(userInput, dsp);
        if (dsp.dialogState.isMoveForward()){
            updatePositionAndSendResponse(null, dsp);
        }
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