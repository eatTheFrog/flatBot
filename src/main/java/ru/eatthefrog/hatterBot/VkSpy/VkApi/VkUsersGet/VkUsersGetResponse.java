package ru.eatthefrog.hatterBot.VkSpy.VkApi.VkUsersGet;

import com.google.gson.annotations.SerializedName;

public class VkUsersGetResponse {
    @SerializedName("response")
    public VkUsersGetResponseProfile[] response;
}
