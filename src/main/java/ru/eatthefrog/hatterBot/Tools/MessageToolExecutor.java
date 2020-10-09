package ru.eatthefrog.hatterBot.Tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.TelegramMessage;

import java.util.HashMap;

@Component
public class MessageToolExecutor {
    @Autowired
    PraiseTool praiseTool;

    private HashMap<String, Toolable> tools;

    public MessageToolExecutor(){
        tools.put("/echo", new EchoTool());
        tools.put("/unknown", new UnknownTool());
    }

    public Toolable getTool(String messageText) {
        String[] messageArgs = messageText.split(" ");
        switch (messageArgs[0]) {
            default:
                return praiseTool;
        }
    }

    public TelegramMessage execute(TelegramMessage userMessage){
        String[] args = userMessage.messageText.split(" ");
        var tool = tools.getOrDefault(args[0], new UnknownTool());
        return new TelegramMessage(userMessage.messageText, userMessage.chatID);
    }
}
