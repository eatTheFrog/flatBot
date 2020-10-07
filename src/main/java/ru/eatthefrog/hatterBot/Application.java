package ru.eatthefrog.hatterBot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.requesthandling.Response;
import ru.eatthefrog.hatterBot.requesthandling.UseRequest;
import ru.eatthefrog.hatterBot.requesthandling.RequestHandler;
import ru.eatthefrog.hatterBot.telegramapi.LongPollResponse;
import ru.eatthefrog.hatterBot.telegramapi.TelegramApiProvider;
import ru.eatthefrog.hatterBot.telegramapi.Token;

@Component()
public class Application {
    @Autowired
    RequestHandler requestHandler;

    @Autowired
    TelegramApiProvider telegramApiProvider;

    @Autowired
    LongPollResponseHandler longPollHandler;

    @Value("${bot.tokenValue}")
    String tokenValue;

    public void run() {
        telegramApiProvider.setToken(
                new Token(tokenValue, true)
        );

        while (true) {
            UseRequest[] useRequests = longPollHandler.handleMessage(
                    telegramApiProvider.getLongPollMessage()
            );

            for (UseRequest useRequest :
                    useRequests) {
                Response response = requestHandler.handleUseRequest(useRequest);
                telegramApiProvider.sendMessage(
                        useRequest.chatId,
                        response.getHumanFriendlyMessage()
                );
            }
        }
    }
}