package ru.eatthefrog.hatterBot.VkSpy.VkProfileManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.VkSpy.VkApi.TooManyRequestsException;
import ru.eatthefrog.hatterBot.VkSpy.VkApi.VkApiMethods;
import ru.eatthefrog.hatterBot.VkSpy.VkUserStatesManager.VkApiTokenInstance;

import java.util.ArrayList;

@Component
@Scope("prototype")
public class VkProfileUnit {

    boolean isOnline = false;
    Integer[] friends;
    @Autowired
    VkApiMethods vkApiMethods;
    public void buildOnline(int vkProfileId, VkApiTokenInstance token) {
        try {
            this.isOnline = vkApiMethods.isOnline(vkProfileId, token);
        }
        catch (TooManyRequestsException e) {
            this.isOnline = false;
        }
    }
    public void buildFriendsIdList(int vkProfileId, VkApiTokenInstance token) {
        try {
            this.friends = vkApiMethods.friendsGet(vkProfileId, token).response.friendsIdsArray;
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
