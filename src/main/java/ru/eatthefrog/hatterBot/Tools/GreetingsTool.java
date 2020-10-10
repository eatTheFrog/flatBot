package ru.eatthefrog.hatterBot.Tools;

import ru.eatthefrog.hatterBot.DialogStateManager.UserDialogStatePosition;
import ru.eatthefrog.hatterBot.LongPollResponse.LongPollUpdateMessageChat;

public class GreetingsTool {
    public String getExecuteOut(String[] args, UserDialogStatePosition userDialogStatePosition) {
        return "Hats off to you, %s %s! I'm here to serve and help you with an array of handy network tools. If you want to know more, don't be shy to ask for /help !";
    }
}
