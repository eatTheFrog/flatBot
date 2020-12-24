package ru.eatthefrog.hatterBot.VkSpy.VkApi.VkWallGet;

import com.google.gson.annotations.SerializedName;
import ru.eatthefrog.hatterBot.VkSpy.VkProfileManager.WallPost;

public class VkWallGetResponseInstance {
    @SerializedName("count")
    public int count;

    @SerializedName("items")
    public WallPost[] posts;
}
