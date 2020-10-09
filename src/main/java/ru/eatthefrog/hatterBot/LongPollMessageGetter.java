package ru.eatthefrog.hatterBot;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.LongPollResponse.LongPollResponse;
import ru.eatthefrog.hatterBot.LongPollResponse.LongPollUpdate;
import ru.eatthefrog.hatterBot.LongPollResponse.LongPollUpdateMessage;

import java.util.ArrayList;
import java.util.List;

@Component
public class LongPollMessageGetter {
    int offest = 0;

    @Autowired
    Gson gson;

    @Autowired
    TelegramAPIProvider telegramAPIProvider;

    TelegramMessage[] getMessagesLongPoll() {
        String response = telegramAPIProvider.getUpdates(offest);
        System.out.println(
                response
        );
        LongPollResponse longPollResponse = gson.fromJson(response, LongPollResponse.class);


        //OFFSET UPDATE:
        int lengthUpdates = longPollResponse.longPollUpdates.length;
        if (lengthUpdates > 0) {
            offest = longPollResponse.longPollUpdates[lengthUpdates-1].update_id + 1;

        }

        //GENERATE TELEGRAM MESSAGE OBJECTS FROM LONG POLL UPDATES
        List<TelegramMessage> telegramMessages = new ArrayList<TelegramMessage>();
        for (LongPollUpdate longPollUpdate:
             longPollResponse.longPollUpdates) {
            if (longPollUpdate.longPollUpdateMessage == null)
                continue;

            telegramMessages.add(
                longPollUpdate.longPollUpdateMessage.toTelegramMessage()
            );
        }

        TelegramMessage[] telegramMessagesArray = new TelegramMessage[telegramMessages.size()];
        return telegramMessages.toArray(telegramMessagesArray);
    }
}
