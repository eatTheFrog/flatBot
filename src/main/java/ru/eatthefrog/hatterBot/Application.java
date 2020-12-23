package ru.eatthefrog.hatterBot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.ExternalApiProvider.ApiProvider;
import ru.eatthefrog.hatterBot.ExternalApiProvider.BotTokenProvider;
import ru.eatthefrog.hatterBot.ExternalApiProvider.LongPollMessageGetter;
import ru.eatthefrog.hatterBot.Message.Message;
import ru.eatthefrog.hatterBot.VkSpy.VkRequestQueueHandlerThread;
import ru.eatthefrog.hatterBot.VkSpy.VkRequestQueuePutter;
import ru.eatthefrog.hatterBot.VkSpy.VkSpyResponsesKeeper.VkSpyRequestKeeper;


@Component
public class Application {
    @Autowired
    LongPollMessageGetter longPollMessageGetter;
    @Autowired
    BotTokenProvider botTokenProvider;
    @Autowired
    ApiProvider ApiProvider;
    @Autowired
    RequestHandler requestHandler;
    @Autowired
    MessageProcessor messageProcessor;
    @Autowired
    VkRequestQueuePutter vkRequestQueuePutter;
    @Autowired
    VkRequestQueueHandlerThread vkRequestQueueHandlerThread;
    @Autowired
    VkSpyRequestKeeper vkSpyRequestKeeper;

    public void run() {
        vkSpyRequestKeeper.loadResponsesMongoDatabase();

        ApiProvider.setToken(
                botTokenProvider.getToken()
        );

        vkRequestQueuePutter.startThread();
        vkRequestQueueHandlerThread.startThreadPool();

        for (int i = 0; i < 5; i++)
            new Thread(requestHandler).start();

        while (true)
            longPollIteration();
    }

    public void longPollIteration() {
            Message[] userMessages = longPollMessageGetter.getMessagesLongPoll();
            for (Message userMessage : userMessages) {
                requestHandler.addRequest(userMessage);
        }
    }
}
