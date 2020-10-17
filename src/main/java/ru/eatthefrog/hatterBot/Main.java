package ru.eatthefrog.hatterBot;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                SpringConfiguration.class
        );
        Application app = context.getBean("application", Application.class);
        context.close();
        app.run();
    }
}