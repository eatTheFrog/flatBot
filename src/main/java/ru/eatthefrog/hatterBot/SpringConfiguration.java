package ru.eatthefrog.hatterBot;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan("ru.eatthefrog.hatterBot")
public class SpringConfiguration {
    @Bean
    public Application appBean() {
        return new Application();
    }

    @Bean
    public Gson gsonBean() { return new Gson(); }
}
