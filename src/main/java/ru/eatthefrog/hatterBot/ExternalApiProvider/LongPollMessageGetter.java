package ru.eatthefrog.hatterBot.ExternalApiProvider;

import ru.eatthefrog.hatterBot.Message.Message;

public interface LongPollMessageGetter {
    public Message[] getMessagesLongPoll();
}
