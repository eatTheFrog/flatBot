package ru.eatthefrog.hatterBot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.HTTPGetter.HTTPGetter1;
import ru.eatthefrog.hatterBot.HTTPGetter.HTTPGetter2;
import ru.eatthefrog.hatterBot.HTTPGetter.HTTPGetterable;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


@Component
public class TelegramAPIProvider {
    @Autowired
    HTTPGetterable httpGetter;

    String Token;

    final String API_PREFIX = "https://api.telegram.org/bot";

    public void setToken(String token) {
        Token = token;
    }

    //TELEGRAM API METHODS: args -> String
    String getUpdates(int offset) {
        return httpGetter.doRequest(
                String.format("%s%s/getUpdates?offset=%s&timeout=3600",
                        API_PREFIX,
                        Token,
                        offset)
        );
    }

    String sendMessage(TelegramMessage telegramMessage){
        return httpGetter.doRequest(
                String.format("%s%s/sendMessage?chat_id=%s&text=%s",
                        API_PREFIX,
                        Token,
                        telegramMessage.chatID,
                        URLEncoder.encode(telegramMessage.messageText, StandardCharsets.UTF_8))
        );
    }
}
