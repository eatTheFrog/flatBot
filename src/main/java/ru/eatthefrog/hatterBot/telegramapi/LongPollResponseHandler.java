package ru.eatthefrog.hatterBot.telegramapi;

import org.springframework.stereotype.Component;

@Component
public class LongPollResponseHandler {
    public BatchRequest handleResponce(LongPollResponse longPollResponse) {
        return new BatchRequest();
    }
}
