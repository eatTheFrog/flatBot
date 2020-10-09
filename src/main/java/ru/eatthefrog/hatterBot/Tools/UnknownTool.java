package ru.eatthefrog.hatterBot.Tools;

import ru.eatthefrog.hatterBot.LongPollResponse.LongPollUpdateMessageChat;

public class UnknownTool implements Toolable {
    public String getExecuteOut(String[] args, LongPollUpdateMessageChat personalization) {
        return "I don't know this command. Try /help!";
    }
}
