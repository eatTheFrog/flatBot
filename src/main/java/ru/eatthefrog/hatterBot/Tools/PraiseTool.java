package ru.eatthefrog.hatterBot.Tools;

import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.UserDialogStatePosition;
import ru.eatthefrog.hatterBot.LongPollResponse.LongPollUpdateMessageChat;

@Component
public class PraiseTool {
    public String getExecuteOut() {
        return "Praised be Bashkarov and Chasovitin!";
    }
}
