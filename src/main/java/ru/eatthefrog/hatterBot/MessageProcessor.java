package ru.eatthefrog.hatterBot;
import org.springframework.beans.factory.annotation.Autowired;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStateManager;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.Message.Message;
@Component
public class MessageProcessor {
    @Autowired
    DialogStateManager dialogStateManager;

    public void processMessage(Message telegramMessage) {
        dialogStateManager.processTelegramMessage(telegramMessage);
    }
}
