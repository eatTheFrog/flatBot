package ru.eatthefrog.hatterBot.telegramapi;

import org.springframework.stereotype.Component;

@Component
public class LongPoller {
    String offset;

    public void updateOffset(String newOffset){
        offset = newOffset;
    }

    public LongPollResponse getLongPollResponce() {
        return new LongPollResponse();
    }
}
