package ru.eatthefrog.hatterBot.VkSpy.VkTokenManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.MongoDBOperator.MongoVkTokenManager;
import ru.eatthefrog.hatterBot.PropertiesProvider;

import javax.annotation.PostConstruct;
import java.util.AbstractMap;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class VkUserTokenManager {
    AbstractMap<Integer, VkApiTokenInstance> vkTokens = new ConcurrentHashMap<>();
    @Autowired
    PropertiesProvider propertiesProvider;
    @Autowired
    MongoVkTokenManager mongoVkTokenManager;
    public void addToken(int chatId, String token) {
        this.vkTokens.put(chatId, new VkApiTokenInstance(token));
        this.mongoVkTokenManager.setToken(chatId, token);
    }
    public VkApiTokenInstance getToken(int chatId) {
        var token_getted = this.vkTokens.get(chatId);

        if (token_getted == null) {
            String token_mongo_string = mongoVkTokenManager.getToken(chatId);
            if (token_mongo_string == null) {
                throw new NullPointerException();
            }
            var tokenInstanceMongo = new VkApiTokenInstance(
                    token_mongo_string);
            this.vkTokens.put(chatId,
                    tokenInstanceMongo
                    );
            return tokenInstanceMongo;
        }
        else {
            return token_getted;
        }
    }
}
