package ru.eatthefrog.hatterBot;

import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.requesthandling.UseRequest;
import ru.eatthefrog.hatterBot.telegramapi.LongPollResponse;

@Component
public class LongPollResponseHandler {
    public UseRequest[] handleMessage(LongPollResponse longPollResponse) {
        return new UseRequest[]{};
    }
}
