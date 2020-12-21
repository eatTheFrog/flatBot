package ru.eatthefrog.hatterBot;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import org.springframework.context.annotation.*;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.CoreStates.DialogState;
import ru.eatthefrog.hatterBot.ExternalApiProvider.TelegramAPI.TelegramAPIProvider;
import ru.eatthefrog.hatterBot.ExternalApiProvider.TelegramAPI.TelegramApiEnableConfiguration;
import ru.eatthefrog.hatterBot.ExternalApiProvider.TelegramAPI.TelegramBotTokenProvider;
import ru.eatthefrog.hatterBot.ExternalApiProvider.TelegramAPI.TelegramLongPollMessageGetter;
import ru.eatthefrog.hatterBot.HTTPGetter.HTTPGetter2;
import ru.eatthefrog.hatterBot.HTTPGetter.HTTPGetterable;
import ru.eatthefrog.hatterBot.Message.Message;
import ru.eatthefrog.hatterBot.Message.TelegramMessage;
import ru.eatthefrog.hatterBot.MongoDBOperator.DataBaseLoginManager;
import ru.eatthefrog.hatterBot.MongoDBOperator.MongoLoginManager;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
@EnableAspectJAutoProxy
@Import(TelegramApiEnableConfiguration.class)
@ComponentScan("ru.eatthefrog.hatterBot")
@PropertySource("bot.properties")
public class SpringConfiguration {

    final String dataBaseName = "FlatHatBotDatabase";

    final String mongoUri = "mongodb://127.0.0.1:27017";

    @Bean
    public DataBaseLoginManager dataBaseLoginManagerBean() {
        return new MongoLoginManager();
    }

    @Bean
    public AbstractMap<Integer, DialogStatePosition> dictionaryBean() {
        return new ConcurrentHashMap<>();
    }

    @Bean
    public Random randomBean() {
        return new Random();
    }

    @Bean
    public Gson gsonBean() {
        return new Gson();
    }

    @Bean
    public HTTPGetterable httpBean() { return new HTTPGetter2(); }

    @Bean
    public HashMap<String, DialogState> nextStatesMap(){
        return new HashMap<>();
    }

    @Bean
    public MongoDatabase mongoDatabaseBean() {
        MongoClientURI clientURI = new MongoClientURI(mongoUri);
        MongoClient mongoClient = new MongoClient(clientURI);
        return mongoClient.getDatabase(
                dataBaseName
        );
    }
}
