package ru.eatthefrog.hatterBot.Tools;

import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.UserDialogStatePosition;
import ru.eatthefrog.hatterBot.LongPollResponse.LongPollUpdateMessageChat;

@Component
public class UnknownTool {
    public String getExecuteOut() {
        return "I don't know this command.";
    }
}
