package ru.eatthefrog.hatterBot.LongPollResponse;

import com.google.gson.annotations.SerializedName;

public class LongPollResponse {
    @SerializedName("ok")
    Boolean ok;

    @SerializedName("result")
    public LongPollUpdate[] longPollUpdates;
}
