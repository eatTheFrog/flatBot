package ru.eatthefrog.hatterBot.VkSpy.VkSpyResponsesKeeper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.FriendsChangesComparator.FriendsChangesComparator;
import ru.eatthefrog.hatterBot.Message.TelegramMessage;
import ru.eatthefrog.hatterBot.VkSpy.VkApi.TooManyRequestsException;
import ru.eatthefrog.hatterBot.VkSpy.VkProfileManager.VkProfileUnit;

import java.util.Arrays;

@Component
@Scope("prototype")
public class VkFriendsSpyRequest extends VkSpyRequestAbstract {
    @Autowired
    FriendsChangesComparator friendsChangesComparator;
    int checkFrequency = 60;
    public void handle() {
        apiProvider.setToken(
                botTokenProvider.getToken()
        );
        System.out.println("token_setted");
        VkProfileUnit vkProfileUnit = vkProfileUnitManager.getVkProfileState(
                this.spyVkId,
                vkUserTokenManager.getToken(this.chatId));
        System.out.println("profile_getted");
        Integer[] currentFriends;
        try {
            currentFriends = this.vkApiMethods.friendsGet(this.spyVkId,
                    this.vkUserTokenManager.getToken(
                            this.chatId
                    )).response.friendsIdsArray;
        }
        catch (TooManyRequestsException e) {
            return;
        }
        var compRes = this.friendsChangesComparator.getCompareResult(vkProfileUnit.getFriends(),
                currentFriends);
        System.out.println(Arrays.toString(compRes.getNewFriends()));
        System.out.println(Arrays.toString(compRes.getDeletedFriends()));
        if (compRes.isChanged()) {
            Integer[] newFriends = compRes.getNewFriends();
            Integer[] deletedFriends = compRes.getDeletedFriends();
            vkProfileUnit.setFriends(currentFriends);
            for (Integer i:
                 newFriends) {
                String messageText = (
                        String.valueOf(this.spyVkId)+
                                " added " +
                                String.valueOf(i));
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
                        String.valueOf(this.spyVkId)+
                                " removed " +
                                String.valueOf(i));
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
