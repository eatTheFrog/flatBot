package ru.eatthefrog.hatterBot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.Tools.MessageToToolCompiler;
import ru.eatthefrog.hatterBot.Tools.Toolable;


@Component
public class Application {
    @Autowired
    LongPollMessageGetter longPollMessageGetter;

    @Autowired
    TelegramBotTokenProvider telegramBotTokenProvider;

    @Autowired
    MessageToToolCompiler messageToToolCompiler;

    @Autowired
    TelegramAPIProvider telegramAPIProvider;

    public void run() {
        telegramAPIProvider.setToken(
                telegramBotTokenProvider.getToken()
        );

        while (true) {
            TelegramMessage[] telegramMessages = longPollMessageGetter.getMessagesLongPoll();
            for (TelegramMessage telegramMessage :
                    telegramMessages) {
                if (telegramMessage.messageText == null)
                    continue;
                Toolable tool = messageToToolCompiler.getTool(telegramMessage.messageText);
                String outString = tool.getExecuteOut(telegramMessage.messageText);
                telegramAPIProvider.sendMessage(
                        outString,
                        telegramMessage.chatID
                );
            }
        }
    }
}
