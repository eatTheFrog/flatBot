package ru.eatthefrog.hatterBot.VkSpy.VkApi.VkFriendsGet;

import com.google.gson.annotations.SerializedName;
import ru.eatthefrog.hatterBot.VkSpy.VkApi.VkUsersGet.VkUsersGetResponseProfile;

public class VkFriendsGetResponse {
    @SerializedName("response")
    public VkFriendsGetResponseList response;
}
