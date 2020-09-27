package ru.eatthefrog.flatBot;

import org.springframework.stereotype.Component;
import ru.eatthefrog.flatBot.telegramapi.LongPollResponse;

@Component
public class LongPollResponseHandler {
    public FlatRequest[] handleResponse(LongPollResponse longPollResponse) {
        return new FlatRequest[]{};
    }
}
