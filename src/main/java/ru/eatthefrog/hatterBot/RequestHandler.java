package ru.eatthefrog.hatterBot;

import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.Message.Message;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class RequestHandler implements Runnable {

    private MessageProcessor messageProcessor;

    static BlockingQueue<Message> requestList;

    public RequestHandler(MessageProcessor messageProcessor){
        this.messageProcessor = messageProcessor;
        this.requestList = new LinkedBlockingQueue<>();
    }

    public static void addRequest(Message messageOnGet) {
        System.out.println(messageOnGet.getMessageText());
        requestList.add(messageOnGet);
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Message userMessage = requestList.take();
                messageProcessor.processMessage(userMessage);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

