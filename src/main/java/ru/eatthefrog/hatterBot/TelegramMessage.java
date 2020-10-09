package ru.eatthefrog.hatterBot;

public class TelegramMessage {
    public String messageText;

    public int chatID;

    public TelegramMessage(String messageText, int chatID) {
        this.messageText = messageText;
        this.chatID = chatID;
    }
}
