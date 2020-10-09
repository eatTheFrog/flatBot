package ru.eatthefrog.hatterBot.LongPollResponse;

import com.google.gson.annotations.SerializedName;

public class LongPollUpdateMessageFrom {
    @SerializedName("id")
    String id;

    @SerializedName("is_bot")
    Boolean is_bot;

    @SerializedName("first_name")
    String first_name;

    @SerializedName("last_name")
    String last_name;

    @SerializedName("language_code")
    String language_code;
}
