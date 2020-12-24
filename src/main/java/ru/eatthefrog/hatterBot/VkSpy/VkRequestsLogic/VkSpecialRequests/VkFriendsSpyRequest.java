package ru.eatthefrog.hatterBot.VkSpy.VkRequestsLogic.VkSpecialRequests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.ArrayChangesComparator.ArrayChangesComparator;
import ru.eatthefrog.hatterBot.Message.TelegramMessage;
import ru.eatthefrog.hatterBot.VkSpy.VkApi.TooManyRequestsException;
import ru.eatthefrog.hatterBot.VkSpy.VkProfileManager.VkProfileUnit;
import ru.eatthefrog.hatterBot.VkSpy.VkTokenManager.VkApiTokenInstance;

import javax.annotation.PostConstruct;

@Component
@Scope("prototype")
public class VkFriendsSpyRequest extends VkSpyRequestAbstract {

    @Autowired
    ArrayChangesComparator arrayChangesComparator;
    @PostConstruct
    public void initBean() {
        this.checkFrequency = 60;
    }
    public String getSpyPrompt() {
        return "Вы следите за друзьями: " + this.vkNameProvider.getName(
                this.spyVkId,
                this.vkUserTokenManager.getToken(this.chatId)
        );
    }
    public void handle() {
        apiProvider.setToken(
                botTokenProvider.getToken()
        );
        VkApiTokenInstance vkToken = vkUserTokenManager.getToken(this.chatId);
        System.out.println("token_setted");
        VkProfileUnit vkProfileUnit = vkProfileUnitManager.getVkProfileState(
                this.spyVkId,
                vkToken);
        System.out.println("profile_getted");
        Integer[] currentFriends;
        try {
            currentFriends = this.vkApiMethodsImplementator.friendsGet(this.spyVkId,
                    vkToken).response.friendsIdsArray;
        }
        catch (TooManyRequestsException e) {
            return;
        }
        var compRes = this.arrayChangesComparator.getCompareResult(vkProfileUnit.getFriends(),
                currentFriends);
        if (compRes.isChanged()) {
            Integer[] newFriends = compRes.getNewFriends();
            Integer[] deletedFriends = compRes.getDeletedFriends();
            vkProfileUnit.setFriends(currentFriends);
            for (Integer i:
                 newFriends) {
                String messageText = (
                        vkNameProvider.getName(this.spyVkId,
                                vkToken
                                ) +
                                " added " +
                                vkNameProvider.getName(i,
                                        vkToken
                                ));
                apiProvider.sendMessage(
                        new TelegramMessage(
                                messageText,
                                this.chatId
                        )
                );
            }
            for (Integer i:
                    deletedFriends) {
                String messageText = (
                        vkNameProvider.getName(this.spyVkId,
                                vkToken
                        ) +
                                " removed " +
                                vkNameProvider.getName(i,
                                        vkToken
                                ));
                apiProvider.sendMessage(
                        new TelegramMessage(
                                messageText,
                                this.chatId
                        )
                );
            }
        }

    }
}
