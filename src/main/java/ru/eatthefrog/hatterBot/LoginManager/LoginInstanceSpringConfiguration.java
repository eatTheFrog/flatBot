package ru.eatthefrog.hatterBot.LoginManager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.eatthefrog.hatterBot.MD5StringHasher.MD5StringHasher;

@Configuration
public class LoginInstanceSpringConfiguration {
    @Bean
    @Scope("prototype")
    LoginInstance loginInstanceBean() {
        return new LoginInstanceImpl();
    }
    @Bean
    MD5StringHasher md5StringHasherBean() {
        return new MD5StringHasher();
    }
}
