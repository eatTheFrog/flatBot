package ru.eatthefrog.hatterBot.LoginManager;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.Application;
import ru.eatthefrog.hatterBot.SpringConfiguration;


public class LoginInstanceFactory {
    public static LoginInstance getLoginInstance(String login, String password) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                LoginInstanceSpringConfiguration.class
        );
        LoginInstance loginInstance = context.getBean("loginInstanceBean", LoginInstance.class);
        context.close();
        return loginInstance;
    }
}
