package ru.eatthefrog.hatterBot.LongPollResponse;

import com.google.gson.annotations.SerializedName;

public class LongPollUpdate {
    @SerializedName("update_id")
    public int update_id;

    @SerializedName("message")
    public LongPollUpdateMessage longPollUpdateMessage;
}
