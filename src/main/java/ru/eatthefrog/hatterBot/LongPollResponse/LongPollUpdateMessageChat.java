package ru.eatthefrog.hatterBot.LongPollResponse;

import com.google.gson.annotations.SerializedName;

public class LongPollUpdateMessageChat {
    @SerializedName("id")
    public int id;

    @SerializedName("first_name")
    public String first_name;

    @SerializedName("last_name")
    public String last_name;

    @SerializedName("type")
    String type;

    @SerializedName("username")
    public String username;
}
