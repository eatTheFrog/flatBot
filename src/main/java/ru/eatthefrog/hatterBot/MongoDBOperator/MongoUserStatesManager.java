package ru.eatthefrog.hatterBot.MongoDBOperator;

import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.UserDialogStatePosition;

@Component
public class MongoUserStatesManager {
    public UserDialogStatePosition getStatePosition(int chatID) {
        return null;
    }
    public void saveStatePosition(UserDialogStatePosition userDialogStatePosition) {

    }
}
