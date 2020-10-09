package ru.eatthefrog.hatterBot.Tools;

import ru.eatthefrog.hatterBot.LongPollResponse.LongPollUpdateMessageChat;

public class GreetingsTool implements Toolable {
    @Override
    public String getExecuteOut(String[] args, LongPollUpdateMessageChat personalization) {
        return String.format("Hats off to you, %s %s! I'm here to serve and help you with an array of handy network tools. If you want to know more, don't be shy to ask for /help !",
                personalization.first_name,
                personalization.last_name);
    }
}
