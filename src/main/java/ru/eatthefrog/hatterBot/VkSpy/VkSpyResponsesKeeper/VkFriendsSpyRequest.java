package ru.eatthefrog.hatterBot.VkSpy.VkSpyResponsesKeeper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.FriendsChangesComparator.FriendsChangesComparator;
import ru.eatthefrog.hatterBot.Message.TelegramMessage;
import ru.eatthefrog.hatterBot.VkSpy.VkApi.TooManyRequestsException;
import ru.eatthefrog.hatterBot.VkSpy.VkApi.VkApiNameProvider;
import ru.eatthefrog.hatterBot.VkSpy.VkProfileManager.VkProfileUnit;
import ru.eatthefrog.hatterBot.VkSpy.VkUserStatesManager.VkApiTokenInstance;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
@Scope("prototype")
public class VkFriendsSpyRequest extends VkSpyRequestAbstract {

    @Autowired
    FriendsChangesComparator friendsChangesComparator;
    @PostConstruct
    public void initBean() {
        this.checkFrequency = 60;
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
        var compRes = this.friendsChangesComparator.getCompareResult(vkProfileUnit.getFriends(),
                currentFriends);
        if (compRes.isChanged()) {
            Integer[] newFriends = compRes.getNewFriends();
            Integer[] deletedFriends = compRes.getDeletedFriends();
            vkProfileUnit.setFriends(currentFriends);
            for (Integer i:
                 newFriends) {
                String messageText = (
                        vkApiNameProvider.getName(this.spyVkId,
                                vkToken
                                ) +
                                " added " +
                                vkApiNameProvider.getName(i,
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
                        vkApiNameProvider.getName(this.spyVkId,
                                vkToken
                        ) +
                                " removed " +
                                vkApiNameProvider.getName(i,
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
