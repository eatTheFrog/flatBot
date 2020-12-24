package ru.eatthefrog.hatterBot.VkSpy.VkRequestsLogic.VkSpecialRequests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.ArrayChangesComparator.ArrayChangesComparator;
import ru.eatthefrog.hatterBot.Message.TelegramMessage;
import ru.eatthefrog.hatterBot.VkSpy.VkApi.TooManyRequestsException;
import ru.eatthefrog.hatterBot.VkSpy.VkProfileManager.VkProfileUnit;
import ru.eatthefrog.hatterBot.VkSpy.VkProfileManager.WallIndexesExtractor;
import ru.eatthefrog.hatterBot.VkSpy.VkTokenManager.VkApiTokenInstance;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
@Scope("prototype")
public class VkWallSpyRequest extends VkSpyRequestAbstract {
    @Autowired
    ArrayChangesComparator arrayChangesComparator;
    @Autowired
    WallIndexesExtractor wallIndexesExtractor;
    @PostConstruct
    public void initBean() {
        this.checkFrequency = 60;
    }
    @Override
    public void handle() {
        apiProvider.setToken(
                botTokenProvider.getToken()
        );
        VkApiTokenInstance vkToken = vkUserTokenManager.getToken(this.chatId);
        VkProfileUnit vkProfileUnit = vkProfileUnitManager.getVkProfileState(
                this.spyVkId,
                vkToken);
        Integer[] currentPostsIds;
        try {
            currentPostsIds = this.wallIndexesExtractor.extractWallIndexes(
                    this.vkApiMethodsImplementator.wallGet(this.spyVkId,
                            this.vkUserTokenManager.getToken(
                                    this.chatId
                            )).response.posts
            );
        }
        catch (TooManyRequestsException e) {
            return;
        }
        var compRes = this.arrayChangesComparator.getCompareResult(
                vkProfileUnit.getWallIds(),
                currentPostsIds);
        if (compRes.isChanged()) {
            Integer[] newFriends = compRes.getNewFriends();
            if (newFriends.length == 0)
                return;
            vkProfileUnit.setWallIds(currentPostsIds);
            for (Integer i:
                    newFriends) {
                try {
                    this.vkApiMethodsImplementator.wallGetById(this.spyVkId,
                            vkToken,
                            i);
                }
                catch (TooManyRequestsException e) {
                    e.printStackTrace();
                }
                if (i <= vkProfileUnit.getMaxPostId()) {
                    continue;
                }
                vkProfileUnit.setMaxPostId(i);
                String messageText = (
                        vkNameProvider.getName(this.spyVkId,
                                vkToken
                        ) + " добавил запись:\n"+
                        String.format(
                                "https://vk.com/id%s?w=wall%s_%s",
                        this.spyVkId,
                        this.spyVkId,
                        String.valueOf(i))
                );
                apiProvider.sendMessage(
                        new TelegramMessage(
                                messageText,
                                this.chatId
                        )
                );
            }
        }
    }

    @Override
    public String getSpyPrompt() {
        return "Вы следите за стеной: " + this.vkNameProvider.getName(
                this.spyVkId,
                this.vkUserTokenManager.getToken(this.chatId)
        );
    }
}
