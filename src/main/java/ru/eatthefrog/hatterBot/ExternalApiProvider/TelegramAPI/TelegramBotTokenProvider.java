package ru.eatthefrog.hatterBot.ExternalApiProvider.TelegramAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.ExternalApiProvider.BotTokenProvider;
import ru.eatthefrog.hatterBot.PropertiesProvider;


public class TelegramBotTokenProvider implements BotTokenProvider {

    @Value("${bot.tokenValue}")
    String token;

    @Autowired
    PropertiesProvider propertiesProvider;

    public String getToken() {
        if (token.equals("default"))
            token = propertiesProvider.getProperty(
                    "secret.bot.properties",
                    "telegram_token");
        return token;
    }
}
