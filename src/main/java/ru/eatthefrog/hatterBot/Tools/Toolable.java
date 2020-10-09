package ru.eatthefrog.hatterBot.Tools;

import ru.eatthefrog.hatterBot.LongPollResponse.LongPollUpdateMessageChat;

public interface Toolable {
    String getExecuteOut(String[] args, LongPollUpdateMessageChat personalization);
}
