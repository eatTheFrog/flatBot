package ru.eatthefrog.hatterBot;

import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.telegramapi.LongPollResponse;

@Component
public class LongPollResponseHandler {
    public FlatRequest[] handleResponse(LongPollResponse longPollResponse) {
        return new FlatRequest[]{};
    }
}
