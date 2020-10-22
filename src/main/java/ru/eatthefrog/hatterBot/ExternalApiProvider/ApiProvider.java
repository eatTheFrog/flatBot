package ru.eatthefrog.hatterBot.ExternalApiProvider;

import ru.eatthefrog.hatterBot.Message.Message;

public interface ApiProvider {
    public String sendMessage(Message message);
    public void setToken(String token);
}
