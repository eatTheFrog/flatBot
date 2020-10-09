package ru.eatthefrog.hatterBot;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.LongPollResponse.LongPollResponse;
import ru.eatthefrog.hatterBot.LongPollResponse.LongPollUpdate;

import java.util.ArrayList;
import java.util.List;

@Component
public class LongPollMessageGetter {
    int offest = 0;

    @Autowired
    Gson gson;

    @Autowired
    TelegramAPIProvider telegramAPIProvider;

    private void updateOffset(LongPollResponse longPollResponse){
        int lengthUpdates = longPollResponse.longPollUpdates.length;
        if (lengthUpdates > 0) {
            offest = longPollResponse.longPollUpdates[lengthUpdates - 1].update_id + 1;
        }
    }

    private TelegramMessage[] generateMessagess(LongPollResponse longPollResponse){
        List<TelegramMessage> telegramMessages = new ArrayList<>();
        for (LongPollUpdate longPollUpdate :
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

    TelegramMessage[] getMessagesLongPoll() {
        String response = telegramAPIProvider.getUpdates(offest);
        System.out.println(response);
        LongPollResponse longPollResponse = gson.fromJson(response, LongPollResponse.class);
        updateOffset(longPollResponse);
        return generateMessagess(longPollResponse);
    }
}