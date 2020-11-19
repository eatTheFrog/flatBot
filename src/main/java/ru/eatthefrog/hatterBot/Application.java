package ru.eatthefrog.hatterBot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.ExternalApiProvider.ApiProvider;
import ru.eatthefrog.hatterBot.ExternalApiProvider.BotTokenProvider;
import ru.eatthefrog.hatterBot.ExternalApiProvider.LongPollMessageGetter;
import ru.eatthefrog.hatterBot.Message.Message;


@Component
public class Application {
    @Autowired
    LongPollMessageGetter longPollMessageGetter;
    @Autowired
    BotTokenProvider botTokenProvider;
    @Autowired
    ApiProvider ApiProvider;
    @Autowired
    MultithreadRequestKeepHandler multithreadRequestKeepHandler;

    public void run() {
        ApiProvider.setToken(
                botTokenProvider.getToken()
        );
        Thread thread = new Thread(multithreadRequestKeepHandler);
        thread.start();
        while (true)
            longPollIteration();
    }

    public void longPollIteration() {
        Message[] userMessages = longPollMessageGetter.getMessagesLongPoll();
        for (Message userMessage :
                userMessages) {
            multithreadRequestKeepHandler.addRequest(userMessage);
        }
    }
}
