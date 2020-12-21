package ru.eatthefrog.hatterBot.VkSpy.VkApi;

import com.google.gson.annotations.SerializedName;

public class VkUsersGetResponse {
    @SerializedName("response")
    VkUsersGetResponseProfile[] response;
}
