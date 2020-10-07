package ru.eatthefrog.hatterBot.telegramapi;

import org.springframework.stereotype.Component;

@Component
public class TelegramLongPoller {
    public LongPollResponse getForLongPollMessage() {
        return new LongPollResponse();
    }
}
