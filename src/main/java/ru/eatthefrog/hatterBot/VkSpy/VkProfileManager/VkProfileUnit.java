package ru.eatthefrog.hatterBot.VkSpy.VkProfileManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.VkSpy.VkApi.TooManyRequestsException;
import ru.eatthefrog.hatterBot.VkSpy.VkApi.VkApiMethodsImplementator;
import ru.eatthefrog.hatterBot.VkSpy.VkTokenManager.VkApiTokenInstance;

@Component
@Scope("prototype")
public class VkProfileUnit {

    boolean isOnline = false;
    Integer[] friends;
    @Autowired
    VkApiMethodsImplementator vkApiMethodsImplementator;
    public void buildOnline(int vkProfileId, VkApiTokenInstance token) {
        try {
            this.isOnline = vkApiMethodsImplementator.isOnline(vkProfileId, token);
        }
        catch (TooManyRequestsException e) {
            this.isOnline = false;
        }
    }
    public void buildFriendsIdList(int vkProfileId, VkApiTokenInstance token) {
        try {
            this.friends = vkApiMethodsImplementator.friendsGet(vkProfileId, token).response.friendsIdsArray;
        }
        catch (TooManyRequestsException e) {
            this.friends = null;
        }
    }
    public Integer[] getFriends() {
        return this.friends;
    }
    public void setFriends(Integer[] friends) {
        this.friends = friends;
    }
    public void buildPage(int vkProfileId, VkApiTokenInstance token) {
        this.buildOnline(vkProfileId, token);
        this.buildFriendsIdList(vkProfileId, token);
    }
    public boolean getIsOnline() {
        return this.isOnline;
    }
    public void setIsOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }
}
