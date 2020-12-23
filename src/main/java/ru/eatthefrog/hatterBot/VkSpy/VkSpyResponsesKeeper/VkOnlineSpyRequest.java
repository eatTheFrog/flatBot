package ru.eatthefrog.hatterBot.VkSpy.VkSpyResponsesKeeper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.Message.TelegramMessage;
import ru.eatthefrog.hatterBot.VkSpy.VkApi.TooManyRequestsException;
import ru.eatthefrog.hatterBot.VkSpy.VkApi.VkApiNameProvider;
import ru.eatthefrog.hatterBot.VkSpy.VkProfileManager.VkProfileUnit;
import ru.eatthefrog.hatterBot.VkSpy.VkUserStatesManager.VkApiTokenInstance;

import javax.annotation.PostConstruct;

@Component
@Scope("prototype")
public class VkOnlineSpyRequest extends VkSpyRequestAbstract{

    @PostConstruct
    public void initBean() {
        this.checkFrequency = 10;
    }
    public void handle() {
        apiProvider.setToken(
                botTokenProvider.getToken()
        );
        VkApiTokenInstance vkToken = vkUserTokenManager.getToken(
                this.chatId
        );
        VkProfileUnit vkProfileUnit = vkProfileUnitManager.getVkProfileState(
                this.spyVkId,
                vkUserTokenManager.getToken(this.chatId));
        boolean isOnline;
        try {
            isOnline = vkApiMethodsImplementator.isOnline(this.spyVkId,
                    vkUserTokenManager.getToken(this.chatId));
        }
        catch (TooManyRequestsException e) {
            return;
        }
        if (vkProfileUnit.getIsOnline() != isOnline) {
            String messageText = "";
            if (isOnline) {
                messageText = "The user " + vkApiNameProvider.getName(
                        this.spyVkId,
                        vkToken
                )
                        + " is ON now";
            }
            else {
                messageText = "The user " + vkApiNameProvider.getName(
                        this.spyVkId,
                        vkToken
                )
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
