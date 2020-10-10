package ru.eatthefrog.hatterBot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BotTokenProvider {
    @Autowired
    DebugPrinter DEBUG_PRINTER;
    @Value("${bot.tokenValue}")
    String token;

    @Autowired
    PropertiesProvider propertiesProvider;

    String getToken() {
        if (token.equals("default"))
            token = propertiesProvider.getProperty(
                    "secret.bot.properties",
                    "token");
        DEBUG_PRINTER.print(token, this);
        return token;
    }
}
