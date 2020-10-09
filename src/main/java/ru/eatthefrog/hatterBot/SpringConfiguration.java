package ru.eatthefrog.hatterBot;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.eatthefrog.hatterBot.HTTPGetter.HTTPGetter1;
import ru.eatthefrog.hatterBot.HTTPGetter.HTTPGetter2;
import ru.eatthefrog.hatterBot.HTTPGetter.HTTPGetterable;

@Configuration
@ComponentScan("ru.eatthefrog.hatterBot")
@PropertySource("bot.properties")
public class SpringConfiguration {
    @Bean
    public Gson gsonBean() {
        return new Gson();
    }

    @Bean
    public HTTPGetterable httpBean() { return new HTTPGetter2(); }
}
