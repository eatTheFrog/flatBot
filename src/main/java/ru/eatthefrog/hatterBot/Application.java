package ru.eatthefrog.hatterBot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.Tools.MessageToolExecutor;


@Component
public class Application {
    @Autowired
    LongPollMessageGetter longPollMessageGetter;

    @Autowired
    BotTokenProvider botTokenProvider;

    @Autowired
    MessageToolExecutor messageToolExecutor;

    @Autowired
    TelegramAPIProvider telegramAPIProvider;

    public void run() {
        telegramAPIProvider.setToken(
                botTokenProvider.getToken()
        );

        while (true) {
            TelegramMessage[] userMessages = longPollMessageGetter.getMessagesLongPoll();
            for (TelegramMessage userMessage :
                    userMessages) {
                if (userMessage.messageText == null)
                    continue;
                TelegramMessage botMessage = messageToolExecutor.execute(userMessage);
                telegramAPIProvider.sendMessage(botMessage);
            }
        }
    }
}