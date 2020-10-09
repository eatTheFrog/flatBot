package ru.eatthefrog.hatterBot.LongPollResponse;

import com.google.gson.annotations.SerializedName;

public class LongPollUpdateMessageChat {
    @SerializedName("id")
    int id;

    @SerializedName("first_name")
    String first_name;

    @SerializedName("last_name")
    String last_name;

    @SerializedName("type")
    String type;
}
