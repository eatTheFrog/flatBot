package ru.eatthefrog.hatterBot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.Tools.MessageToToolCompiler;
import ru.eatthefrog.hatterBot.Tools.Toolable;
import ru.eatthefrog.hatterBot.Tools.Toolable;


@Component
public class Application {
    @Autowired
    LongPollMessageGetter longPollerMessageGetter;

    @Autowired
    TelegramBotTokenProvider telegramBotTokenProvider;

    @Autowired
    MessageToToolCompiler messageToToolCompiler;

    @Autowired
    TelegramAPIProvider telegramAPIProvider;
    public void run() {
        telegramAPIProvider.Token = telegramBotTokenProvider.getToken();

        while (true) {
            TelegramMessage[] telegramMessages = longPollerMessageGetter.getMessagesLongPoll();
            for (TelegramMessage telegramMessage:
                    telegramMessages) {
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
