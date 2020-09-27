package ru.eatthefrog.flatBot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.eatthefrog.flatBot.simpleAES.AES;
import ru.eatthefrog.flatBot.telegramapi.TelegramLongPoller;

public class Main {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                SpringConfiguration.class
        );
        context.getBean("appBean", Application.class).run();
    }
}
