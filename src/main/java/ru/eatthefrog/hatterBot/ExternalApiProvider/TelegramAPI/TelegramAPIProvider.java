package ru.eatthefrog.hatterBot.ExternalApiProvider.TelegramAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.ExternalApiProvider.ApiProvider;
import ru.eatthefrog.hatterBot.HTTPGetter.HTTPGetterable;
import ru.eatthefrog.hatterBot.Message.Message;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


public class TelegramAPIProvider implements ApiProvider {
    @Autowired
    HTTPGetterable httpGetter;

    String Token;

    final String API_PREFIX = "https://api.telegram.org/bot";

    public void setToken(String token) {
        this.Token = token;
    }
    public String getToken() {
        return this.Token;
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

    public String sendMessage(Message message){
        String request_text = String.format("%s%s/sendMessage?chat_id=%s&text=%s",
                API_PREFIX,
                this.getToken(),
                message.getChatId(),
                URLEncoder.encode(message.getMessageText(), StandardCharsets.UTF_8));

        return httpGetter.doRequest(
                request_text
        );
    }
}
