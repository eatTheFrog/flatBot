package ru.eatthefrog.hatterBot.Tools;

import ru.eatthefrog.hatterBot.TelegramMessage;

public class PersonolizedTelegramMessage extends TelegramMessage {
    public PersonolizedTelegramMessage(String messageText, int chatID) {
        super(messageText, chatID);
    }
}
