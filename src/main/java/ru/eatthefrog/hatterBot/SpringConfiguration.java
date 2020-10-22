package ru.eatthefrog.hatterBot;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import org.springframework.context.annotation.*;
import ru.eatthefrog.hatterBot.DBOperator.MongoImplementation.MongoEnableConfiguration;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.DialogState;
import ru.eatthefrog.hatterBot.ExternalApiProvider.TelegramAPI.TelegramApiEnableConfiguration;
import ru.eatthefrog.hatterBot.HTTPGetter.HTTPGetter2;
import ru.eatthefrog.hatterBot.HTTPGetter.HTTPGetterable;
import ru.eatthefrog.hatterBot.DBOperator.DataBaseLoginManager;
import ru.eatthefrog.hatterBot.DBOperator.MongoImplementation.MongoLoginManager;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Random;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("ru.eatthefrog.hatterBot")
@Import({MongoEnableConfiguration.class,
        TelegramApiEnableConfiguration.class})
@PropertySource("classpath:bot.properties")

public class SpringConfiguration {

    @Bean
    public Dictionary<Integer, DialogStatePosition> dictionaryBean() {
        return new Hashtable<>();
    }

    @Bean
    public Random randomBean() {
        return new Random();
    }

    @Bean
    public HTTPGetterable httpBean() { return new HTTPGetter2(); }

    @Bean
    public HashMap<String, DialogState> nextStatesMap(){
        return new HashMap<>();
    }


}
