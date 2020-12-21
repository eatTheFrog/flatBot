package ru.eatthefrog.hatterBot.VkSpy.VkSpyResponsesKeeper;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.ExternalApiProvider.ApiProvider;
import ru.eatthefrog.hatterBot.ExternalApiProvider.BotTokenProvider;
import ru.eatthefrog.hatterBot.ExternalApiProvider.TelegramAPI.TelegramBotTokenProvider;
import ru.eatthefrog.hatterBot.Message.TelegramMessage;
import ru.eatthefrog.hatterBot.SpringConfiguration;
import ru.eatthefrog.hatterBot.VkSpy.VkApi.VkApiMethods;
import ru.eatthefrog.hatterBot.VkSpy.VkProfileManager.VkProfileUnit;
import ru.eatthefrog.hatterBot.VkSpy.VkProfileManager.VkProfileUnitManager;
import ru.eatthefrog.hatterBot.VkSpy.VkUserStatesManager.VkUserTokenManager;

import javax.annotation.PostConstruct;

@Component
@Scope("prototype")
public class VkOnlineSpyRequest implements VkSpyRequest{
    long lastTimeChecked;
    int spyVkId;
    int chatId;

    @Autowired
    VkProfileUnitManager vkProfileUnitManager;
    @Autowired
    VkApiMethods vkApiMethods;
    @Autowired
    VkUserTokenManager vkUserTokenManager;
    @Autowired
    ApiProvider apiProvider;
    @Autowired
    BotTokenProvider botTokenProvider;

    @PostConstruct
    public void updateTime() {
        this.lastTimeChecked = System.currentTimeMillis();
    }

    public void setSpyVkId(int spyVkId) {
        this.spyVkId = spyVkId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }
    public int getChatId() {
        return this.chatId;
    }
    public int getSpyVkId() {
        return this.spyVkId;
    }

    @Override
    public boolean shouldUpdate() {
        boolean toReturn =  System.currentTimeMillis() - lastTimeChecked > 1000;
        if (toReturn) {
            this.lastTimeChecked = System.currentTimeMillis();

        }
        return toReturn;
    }

    @Override
    public void handle() {
        apiProvider.setToken(
                botTokenProvider.getToken()
        );
        VkProfileUnit vkProfileUnit = vkProfileUnitManager.getVkProfileState(
                this.spyVkId,
                vkUserTokenManager.getToken(this.chatId));

        boolean isOnline = vkApiMethods.isOnline(this.spyVkId,
                vkUserTokenManager.getToken(this.chatId));

        if (vkProfileUnit.getIsOnline() != isOnline) {
            String messageText = "";
            if (isOnline) {
                messageText = "The user " + String.valueOf(this.spyVkId)
                        + " is ON now";
            }
            else {
                messageText = "The user " + String.valueOf(this.spyVkId)
                        + " is OFF now";
            }
            apiProvider.sendMessage(
                    new TelegramMessage(
                            messageText,
                            this.chatId
                    )
            );
            vkProfileUnit.setIsOnline(isOnline);
        }
    }
}
