package ru.eatthefrog.hatterBot;
import org.springframework.beans.factory.annotation.Autowired;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStateManager;
import ru.eatthefrog.hatterBot.TelegramMessage;
import org.springframework.stereotype.Component;
@Component
public class MessageProcessor {
    @Autowired
    DialogStateManager dialogStateManager;

    public TelegramMessage processMessage(TelegramMessage telegramMessage) {
        return dialogStateManager.statefulMessageProcess(telegramMessage);
    }
}
