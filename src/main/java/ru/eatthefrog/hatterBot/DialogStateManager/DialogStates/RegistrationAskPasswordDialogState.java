package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;

@Component
public class RegistrationAskPasswordDialogState extends DialogState {
//    @PostConstruct
//    void initIdentifier() {
//        dialogStateIdentifier = "registrationAskPasswordDialogState";
//    }
    public DialogState moveToOtherState(String userInput, DialogStatePosition dialogStatePosition) {
        dialogStatePosition.loginInstance.password = userInput;
        loginValidChecker.rememberLoginInDB(dialogStatePosition.loginInstance);
        loginValidChecker.checkValidLoginInMongoAndUpdateVerification(dialogStatePosition.loginInstance);
        telegramChatSTDOUT.printInChat(
                "You have made registration.",
                dialogStatePosition.chatID
        );
        return getMenuState(dialogStatePosition);
    }


    @Override
    public void fillStateMap() {

    }

    @Override
    public String getResponse(String userInput, DialogState previousDialogState) {
        return null;
    }
}
