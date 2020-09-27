package ru.eatthefrog.flatBot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.eatthefrog.flatBot.simpleAES.AES;

@Configuration
@ComponentScan("ru.eatthefrog.flatBot")
public class SpringConfiguration {
    @Bean
    public Application appBean() {
        return new Application();
    }
}
