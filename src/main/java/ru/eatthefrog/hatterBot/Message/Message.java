package ru.eatthefrog.hatterBot.Message;

public interface Message {
    public String getMessageText();
    public int getChatId();
    public void setText(String text);
    public void setChatID(int chatID);
}
