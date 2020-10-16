package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;
import ru.eatthefrog.hatterBot.TelegramChatSTDOUT;

import javax.annotation.PostConstruct;

@Component
public class EchoModState extends DialogState {
    @Autowired
    TelegramChatSTDOUT telegramChatSTDOUT;

    @PostConstruct
    void initIdentifier() {
        dialogStateIdentifier = "echoModDialogState";
    }

    public DialogState moveOtherState(String userInput, DialogStatePosition dialogStatePosition) {
        switch (userInput) {
            case "putin":
                return getNextMenuState(dialogStatePosition);
            default:
                telegramChatSTDOUT.printInChat(userInput, dialogStatePosition.chatID);
                return this;
        }
    }
}
