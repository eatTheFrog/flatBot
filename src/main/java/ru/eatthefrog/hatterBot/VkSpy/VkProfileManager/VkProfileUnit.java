package ru.eatthefrog.hatterBot.VkSpy.VkProfileManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.VkSpy.VkApi.TooManyRequestsException;
import ru.eatthefrog.hatterBot.VkSpy.VkApi.VkApiMethodsImplementator;
import ru.eatthefrog.hatterBot.VkSpy.VkTokenManager.VkApiTokenInstance;

import java.util.Arrays;
import java.util.Collections;

@Component
@Scope("prototype")
public class VkProfileUnit {

    boolean isOnline = false;
    Integer[] wallIds;
    Integer[] friends;
    Integer maxPostId;

    public Integer getMaxPostId() {
        return maxPostId;
    }

    @Autowired
    WallIndexesExtractor wallIndexesExtractor;
    @Autowired
    VkApiMethodsImplementator vkApiMethodsImplementator;
    public void setMaxPostId(Integer maxPostId) {
        this.maxPostId = maxPostId;
    }

    void buildOnline(int vkProfileId, VkApiTokenInstance token) {
        try {
            this.isOnline = vkApiMethodsImplementator.isOnline(vkProfileId, token);
        }
        catch (TooManyRequestsException e) {
            this.isOnline = false;
        }
    }
    void buildFriendsIdList(int vkProfileId, VkApiTokenInstance token) {
        try {
            this.friends = vkApiMethodsImplementator.friendsGet(vkProfileId, token).response.friendsIdsArray;
        }
        catch (TooManyRequestsException e) {
            this.friends = null;
        }
    }
    void buildWall(int vkProfileId, VkApiTokenInstance token) {
        try {
            var posts = vkApiMethodsImplementator.wallGet(
                    vkProfileId,
                    token
            ).response.posts;

            this.wallIds = wallIndexesExtractor.extractWallIndexes(posts);
            this.maxPostId = Collections.max(Arrays.asList(this.wallIds));
            System.out.println(Arrays.toString(this.wallIds));
        }
        catch (TooManyRequestsException e) {
            this.friends = null;
        }
    }
    public Integer[] getFriends() {
        return this.friends;
    }
    public Integer[] getWallIds() {
        return this.wallIds;
    }
    public void setWallIds(Integer[] wallIds) {
        this.wallIds = wallIds;
    }
    public void setFriends(Integer[] friends) {
        this.friends = friends;
    }
    public void buildPage(int vkProfileId, VkApiTokenInstance token) {
        this.buildOnline(vkProfileId, token);
        this.buildFriendsIdList(vkProfileId, token);
        this.buildWall(vkProfileId, token);
    }
    public boolean getIsOnline() {
        return this.isOnline;
    }
    public void setIsOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }
}
