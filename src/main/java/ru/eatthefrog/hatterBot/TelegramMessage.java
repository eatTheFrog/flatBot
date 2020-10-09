package ru.eatthefrog.hatterBot;

import ru.eatthefrog.hatterBot.LongPollResponse.LongPollUpdateMessageChat;

public class TelegramMessage {
    public String messageText;

    public int chatID;

    public LongPollUpdateMessageChat longPollUpdateMessageChat;

    public TelegramMessage(String messageText, int chatID) {
        this.messageText = messageText;
        this.chatID = chatID;
    }

    public TelegramMessage(String messageText, int chatID, LongPollUpdateMessageChat longPollUpdateMessageChat){
        this.messageText = messageText;
        this.chatID = chatID;
        this.longPollUpdateMessageChat = longPollUpdateMessageChat;
    }
}
