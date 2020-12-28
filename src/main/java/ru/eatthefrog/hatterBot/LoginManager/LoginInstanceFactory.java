package ru.eatthefrog.hatterBot.LoginManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.Application;
import ru.eatthefrog.hatterBot.SpringConfiguration;


@Component
public class LoginInstanceFactory {
    @Autowired
    AnnotationConfigApplicationContext context;
    public LoginInstance getLoginInstance() {
        LoginInstance loginInstance = context.getBean("loginInstanceBean", LoginInstance.class);
        return loginInstance;
    }
}
