package ru.eatthefrog.hatterBot.Message;

import ru.eatthefrog.hatterBot.LongPollResponse.LongPollUpdateMessageChat;
import ru.eatthefrog.hatterBot.Message.Message;

public class TelegramMessage implements Message {
    public String messageText;

    public int chatID;

    public LongPollUpdateMessageChat longPollUpdateMessageChat;

    public TelegramMessage() {};

    public TelegramMessage(String messageText, int chatID) {
        this.messageText = messageText;
        this.chatID = chatID;
    }

    public TelegramMessage(String messageText, int chatID, LongPollUpdateMessageChat longPollUpdateMessageChat){
        this.messageText = messageText;
        this.chatID = chatID;
        this.longPollUpdateMessageChat = longPollUpdateMessageChat;
    }

    @Override
    public String getMessageText() {
        return this.messageText;
    }

    @Override
    public int getChatId() {
        return this.chatID;
    }

    @Override
    public void setText(String text) {
        this.messageText = text;
    }

    @Override
    public void setChatID(int chatID) {
        this.chatID = chatID;
    }
}
