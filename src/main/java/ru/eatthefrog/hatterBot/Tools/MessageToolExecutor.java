package ru.eatthefrog.hatterBot.Tools;

import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.TelegramMessage;

import java.util.HashMap;

@Component
public class MessageToolExecutor {
    private HashMap<String, Toolable> tools = new HashMap<>();

    private UnknownTool unknownTool = new UnknownTool();

    public MessageToolExecutor(){
        tools.put("/echo", new EchoTool());
        tools.put("/praise", new PraiseTool());
        tools.put("/help", new HelpTool());
        tools.put("/start", new GreetingsTool());
    }

    public TelegramMessage execute(TelegramMessage userMessage){
        String[] args = userMessage.messageText.split(" ");
        String botMessage = tools.getOrDefault(args[0], unknownTool).getExecuteOut(args, userMessage.longPollUpdateMessageChat);
        return new TelegramMessage(botMessage, userMessage.chatID);
    }
}
