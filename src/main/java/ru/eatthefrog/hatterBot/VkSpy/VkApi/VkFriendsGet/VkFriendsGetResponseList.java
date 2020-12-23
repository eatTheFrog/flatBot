package ru.eatthefrog.hatterBot.VkSpy.VkApi.VkFriendsGet;

import com.google.gson.annotations.SerializedName;

public class VkFriendsGetResponseList {
    @SerializedName("count")
    public int count;
    @SerializedName("items")
    public Integer[] friendsIdsArray;
}
