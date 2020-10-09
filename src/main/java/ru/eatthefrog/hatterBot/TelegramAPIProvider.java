package ru.eatthefrog.hatterBot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;


@Component
public class TelegramAPIProvider {
    @Autowired
    HTTPGetter httpGetter;

    String Token;

    final String API_PREFIX = "https://api.telegram.org/bot";

    public void setToken(String token) {
        Token = token;
    }

    //TELEGRAM API METHODS: args -> String
    String getUpdates(int offset) {
        String methodName = "getUpdates";
        return httpGetter.doRequest(
                API_PREFIX + Token + "/"
                        + methodName
                        + "?offset=" + offset
                        + "&timeout=3600"
        );
    }

    String sendMessage(String text, int chatID) {
        String uriText = URLEncoder.encode(text);
        String methodName = "sendMessage";
        return httpGetter.doRequest(
                API_PREFIX + Token + "/"
                        + methodName
                        + "?chat_id=" + chatID
                        + "&text=" + uriText
        );
    }

    String sendMessage(TelegramMessage telegramMessage){
        return sendMessage(
                telegramMessage.messageText,
                telegramMessage.chatID);
    }
}
