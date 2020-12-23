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
import ru.eatthefrog.hatterBot.VkSpy.VkApi.TooManyRequestsException;
import ru.eatthefrog.hatterBot.VkSpy.VkApi.VkApiMethods;
import ru.eatthefrog.hatterBot.VkSpy.VkProfileManager.VkProfileUnit;
import ru.eatthefrog.hatterBot.VkSpy.VkProfileManager.VkProfileUnitManager;
import ru.eatthefrog.hatterBot.VkSpy.VkUserStatesManager.VkUserTokenManager;

import javax.annotation.PostConstruct;

@Component
@Scope("prototype")
public class VkOnlineSpyRequest extends VkSpyRequestAbstract{
    int checkFrequency = 60;


    public void handle() {
        apiProvider.setToken(
                botTokenProvider.getToken()
        );
        VkProfileUnit vkProfileUnit = vkProfileUnitManager.getVkProfileState(
                this.spyVkId,
                vkUserTokenManager.getToken(this.chatId));
        boolean isOnline;
        try {
            isOnline = vkApiMethods.isOnline(this.spyVkId,
                    vkUserTokenManager.getToken(this.chatId));
        }
        catch (TooManyRequestsException e) {
            return;
        }
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
