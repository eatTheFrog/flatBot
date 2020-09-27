package ru.eatthefrog.flatBot.telegramapi;

import org.springframework.stereotype.Component;

@Component
public class TelegramLongPoller {
    public LongPollResponse getForLongPollMessage() {
        return new LongPollResponse();
    }
}
