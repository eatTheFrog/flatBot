package ru.eatthefrog.hatterBot.VkSpy.VkApi.VkUsersGet;

import com.google.gson.annotations.SerializedName;

public class VkUsersGetResponseProfile {
    @SerializedName("online")
    public int online;
    @SerializedName("first_name")
    public String name;
    @SerializedName("last_name")
    public String surname;
}
