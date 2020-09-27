package ru.eatthefrog.flatBot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ru.eatthefrog.flatBot.telegramapi.TelegramApiProvider;
import ru.eatthefrog.flatBot.telegramapi.TelegramLongPoller;
import ru.eatthefrog.flatBot.telegramapi.TokenProvider;

import java.io.IOException;

@Component
public class Application {

    @Autowired
    TelegramApiProvider telegramApiProvider;

    @Autowired
    TelegramLongPoller telegramLongPoller;

    @Autowired
    TokenProvider tokenProvider;
    public void run() {
        telegramApiProvider.setToken(
                tokenProvider.getToken()
        );

        while(true) {
            telegramLongPoller.getForLongPollMessage();
        }
    }
}
