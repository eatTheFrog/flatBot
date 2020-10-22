package ru.eatthefrog.hatterBot.Message;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.eatthefrog.hatterBot.Application;
import ru.eatthefrog.hatterBot.SpringConfiguration;

public class MessageFactory {
    public static Message getNewMessage(String text, int chatID) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                SpringConfiguration.class
        );
        Message msg = context.getBean("messageBean", Message.class);
        msg.setText(text);
        msg.setChatID(chatID);
        return msg;
    }
}
