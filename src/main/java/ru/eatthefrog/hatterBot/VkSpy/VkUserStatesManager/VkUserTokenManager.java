package ru.eatthefrog.hatterBot.VkSpy.VkUserStatesManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.PropertiesProvider;
import ru.eatthefrog.hatterBot.VkSpy.VkProfileManager.VkProfileUnit;

import javax.annotation.PostConstruct;
import java.util.AbstractMap;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class VkUserTokenManager {
    AbstractMap<Integer, VkApiTokenInstance> vkTokens = new ConcurrentHashMap<>();
    @Autowired
    PropertiesProvider propertiesProvider;

    @PostConstruct
    public void putMyToken() {
        this.vkTokens.put(
                875574469,
                new VkApiTokenInstance(propertiesProvider.getProperty(
                        "secret.bot.properties",
                        "vk_token"
                ))
        );
    }
    public void addToken(int chatId, String token) {
        this.vkTokens.put(chatId, new VkApiTokenInstance(token));
    }
    public VkApiTokenInstance getToken(int chatId) {
        return this.vkTokens.get(chatId);
    }
}
