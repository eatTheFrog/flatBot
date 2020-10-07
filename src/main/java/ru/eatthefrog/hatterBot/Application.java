package ru.eatthefrog.hatterBot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.requesthandling.Response;
import ru.eatthefrog.hatterBot.requesthandling.Request;
import ru.eatthefrog.hatterBot.requesthandling.RequestHandler;
import ru.eatthefrog.hatterBot.telegramapi.TelegramApiProvider;
import ru.eatthefrog.hatterBot.telegramapi.Token;

@Component()
public class Application {
    @Autowired
    RequestHandler requestHandler;

    @Autowired
    TelegramApiProvider telegramApiProvider;

    @Value("${bot.tokenValue}")
    String tokenValue;

    public void run() {
        telegramApiProvider.setToken(
                new Token(tokenValue, true)
        );

        while (true) {
            Request[] requests = telegramApiProvider.getAndPreProcessMessages();

            for (Request request : requests) {
                Response response = requestHandler.handleUseRequest(request);
                telegramApiProvider.sendChatMessage(
                        request.chatId,
                        response.getHumanFriendlyMessage()
                );
            }
        }
    }
}