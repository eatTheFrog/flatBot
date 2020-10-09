package ru.eatthefrog.hatterBot.Tools;

import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.LongPollResponse.LongPollUpdateMessageChat;

@Component
public class PraiseTool implements Toolable {
    @Override
    public String getExecuteOut(String[] args, LongPollUpdateMessageChat personalization) {
        return "Praised be Bashkarov and Chasovitin!";
    }
}
