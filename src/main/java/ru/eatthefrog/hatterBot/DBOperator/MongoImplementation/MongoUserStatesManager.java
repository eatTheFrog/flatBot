package ru.eatthefrog.hatterBot.DBOperator.MongoImplementation;

import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;

@Component
public class MongoUserStatesManager {
    public DialogStatePosition getStatePosition(int chatID) {
        return null;
    }
    public void saveStatePosition(DialogStatePosition dialogStatePosition) {

    }
}
