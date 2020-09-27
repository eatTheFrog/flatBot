package ru.eatthefrog.flatBot.telegramapi;

import org.springframework.stereotype.Component;

@Component
public class TelegramLongPoller {
    public String getForLongPollMessage() {
        return "a";
    }
}
