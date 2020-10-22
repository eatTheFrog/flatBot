package ru.eatthefrog.hatterBot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.ExternalApiProvider.ApiProvider;
import ru.eatthefrog.hatterBot.Message.Message;

import java.util.*;

@Component
public class MultithreadRequestKeepHandler implements Runnable {
    @Autowired
    MessageProcessor messageProcessor;

    @Autowired
    ApiProvider apiProvider;
    Queue<Message> requestList = new LinkedList<Message>();
    public void addRequest(Message messageOnGet) {
        this.requestList.add(messageOnGet);
    }
    public void startHandling() {
        Thread thread = new Thread(this);
        thread.start();
    }
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            if (requestList.isEmpty())
                continue;
            Message userMessage = requestList.poll();
            if (userMessage.getMessageText() == null)
                continue;

            Message[] botMessages = messageProcessor.processMessage(userMessage);
            for (Message botMessage : botMessages) {
                if (botMessage == null)
                    continue;
                apiProvider.sendMessage(botMessage);
            }

        }
    }
}
