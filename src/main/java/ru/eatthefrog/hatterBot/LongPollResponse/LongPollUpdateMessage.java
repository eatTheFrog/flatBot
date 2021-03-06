package ru.eatthefrog.hatterBot.LongPollResponse;

import com.google.gson.annotations.SerializedName;
import ru.eatthefrog.hatterBot.Message.TelegramMessage;

public class LongPollUpdateMessage {
    @SerializedName("message_id")
    int message_id;

    @SerializedName("date")
    int date;

    @SerializedName("text")
    String messageText;

    @SerializedName("chat")
    LongPollUpdateMessageChat longPollUpdateMessageChat;

    public TelegramMessage toTelegramMessage() {
        return new TelegramMessage(messageText, longPollUpdateMessageChat.id, longPollUpdateMessageChat);
    }
}
