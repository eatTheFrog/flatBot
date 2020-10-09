package ru.eatthefrog.hatterBot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TelegramBotTokenProvider {
    @Autowired
    PropertiesProvider propertiesProvider;

    String getToken() {
        String token = propertiesProvider.getProperty(
                "bot.properties",
                "token"
        );
        System.out.println(token);
        return token;
    }
}
