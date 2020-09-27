package ru.eatthefrog.flatBot.telegramapi;

import org.springframework.stereotype.Component;

@Component
public class TelegramApiProvider {
    Token token;

    public void setToken(Token token) {
        this.token = token;
    }

    public void sendMessage(String chatId, String message) {

    }
}
