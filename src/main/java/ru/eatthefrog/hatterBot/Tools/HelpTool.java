package ru.eatthefrog.hatterBot.Tools;

import ru.eatthefrog.hatterBot.DialogStateManager.UserDialogStatePosition;
import ru.eatthefrog.hatterBot.LongPollResponse.LongPollUpdateMessageChat;

public class HelpTool {
    public String getExecuteOut(String[] args, UserDialogStatePosition userDialogStatePosition) {
        return "In future I'll be able to ping and nmap for you, but right now I can only /echo your words /praise my creators.";
    }
}
