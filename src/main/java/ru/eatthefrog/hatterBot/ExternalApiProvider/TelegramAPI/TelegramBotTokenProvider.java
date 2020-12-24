package ru.eatthefrog.hatterBot.ExternalApiProvider.TelegramAPI;

import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.ExternalApiProvider.BotTokenProvider;
import ru.eatthefrog.hatterBot.MongoUtils.MongoUtils;
import ru.eatthefrog.hatterBot.PropertiesProvider;


public class TelegramBotTokenProvider implements BotTokenProvider {

    @Value("${bot.tokenValue}")
    String token;

    @Autowired
    PropertiesProvider propertiesProvider;

    @Autowired
    MongoDatabase mongoDatabase;

    @Autowired
    MongoUtils mongoUtils;

    public String getToken() {
        var mongoToken = mongoUtils.fetchTelegramToken(mongoDatabase);
        if (mongoToken == null){
            return propertiesProvider.getProperty(
                    "secret.bot.properties",
                    "telegram_token");
        }
        return mongoToken;
    }
}
