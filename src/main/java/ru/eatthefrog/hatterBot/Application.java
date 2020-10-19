package ru.eatthefrog.hatterBot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.LoginManager.LoginValidChecker;
import ru.eatthefrog.hatterBot.MongoDBOperator.MongoLoginManager;


@Component
public class Application {
    @Autowired
    LongPollMessageGetter longPollMessageGetter;
    @Autowired
    BotTokenProvider botTokenProvider;
    @Autowired
    TelegramAPIProvider telegramAPIProvider;
    @Autowired
    MessageProcessor messageProcessor;
    @Autowired
    MongoLoginManager mongoLoginManager;
    @Autowired
    LoginValidChecker loginValidChecker;

    public void run() {
        telegramAPIProvider.setToken(
                botTokenProvider.getToken()
        );
        while (true)
            this.longPollIteration();
    }

    public void longPollIteration() {
        TelegramMessage[] userMessages = longPollMessageGetter.getMessagesLongPoll();
        for (TelegramMessage userMessage :
                userMessages) {

            if (userMessage.messageText == null)
                continue;

            TelegramMessage[] botMessages = messageProcessor.processMessage(userMessage);
            for (TelegramMessage botMessage : botMessages) {
                if (botMessage == null)
                    continue;
                telegramAPIProvider.sendMessage(botMessage);
            }
        }
    }
}