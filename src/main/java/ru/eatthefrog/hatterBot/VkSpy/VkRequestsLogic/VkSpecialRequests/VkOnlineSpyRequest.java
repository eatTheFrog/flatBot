package ru.eatthefrog.hatterBot.VkSpy.VkRequestsLogic.VkSpecialRequests;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.Message.TelegramMessage;
import ru.eatthefrog.hatterBot.VkSpy.VkApi.TooManyRequestsException;
import ru.eatthefrog.hatterBot.VkSpy.VkProfileManager.VkProfileUnit;
import ru.eatthefrog.hatterBot.VkSpy.VkTokenManager.VkApiTokenInstance;

import javax.annotation.PostConstruct;

@Component
@Scope("prototype")
public class VkOnlineSpyRequest extends VkSpyRequestAbstract {

    @PostConstruct
    public void initBean() {
        this.checkFrequency = 10;
    }
    public String getSpyPrompt() {
        return "Вы следите за онлайном: " + this.vkNameProvider.getName(
                this.spyVkId,
                this.vkUserTokenManager.getToken(this.chatId)
        );
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
        System.out.println(isOnline);
        if (vkProfileUnit.getIsOnline() != isOnline) {
            String messageText = "";
            if (isOnline) {
                messageText = "The user " + vkNameProvider.getName(
                        this.spyVkId,
                        vkToken
                )
                        + " is ON now";
            }
            else {
                messageText = "The user " + vkNameProvider.getName(
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
