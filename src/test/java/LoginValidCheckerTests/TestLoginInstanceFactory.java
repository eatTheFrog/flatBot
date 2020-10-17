package LoginValidCheckerTests;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.eatthefrog.hatterBot.LoginManager.LoginInstance;
import ru.eatthefrog.hatterBot.LoginManager.LoginInstanceSpringConfiguration;

public class TestLoginInstanceFactory {
    public static LoginInstance getLoginInstance(String login, String password) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                LoginValidCheckerTestsConfiguration.class
        );
        LoginInstance loginInstance = context.getBean("loginInstanceBean", LoginInstance.class);
        context.close();
        loginInstance.setLogin(login);
        loginInstance.setPassword(password);
        return loginInstance;
    }
}
