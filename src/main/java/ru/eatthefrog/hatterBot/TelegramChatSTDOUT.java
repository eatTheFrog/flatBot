package ru.eatthefrog.hatterBot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TelegramChatSTDOUT {
    @Autowired
    TelegramAPIProvider telegramAPIProvider;
    public void printInChat(String string, int chatID) {
        telegramAPIProvider.sendMessage(new TelegramMessage(
            string, chatID
        ));
    }
}
