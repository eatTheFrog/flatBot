package ru.eatthefrog.hatterBot.telegramapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.requesthandling.Request;

@Component
public class TelegramApiProvider {

    Token botToken;

    @Autowired
    LongPollResponseHandler longPollResponseHandler;

    @Autowired
    LongPoller longPoller;

    @Autowired
    MessageSender messageSender;

    public void setToken(Token botToken) {
        this.botToken = botToken;
    }

    public void sendChatMessage(String chatId, String message) {
        messageSender.sendChatMessage(chatId, message);
    }

    public Request[] getAndPreProcessMessages(){
        LongPollResponse longPollResponse = longPoller.getLongPollResponce();
        BatchRequest batchRequest = longPollResponseHandler.handleResponce(longPollResponse);
        longPoller.updateOffset(batchRequest.newOffset);
        return batchRequest.requests;
    }
}
