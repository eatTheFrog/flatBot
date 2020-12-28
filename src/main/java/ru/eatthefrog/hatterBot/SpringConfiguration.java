package ru.eatthefrog.hatterBot;

import com.google.gson.Gson;
import com.mongodb.*;
import com.mongodb.client.MongoClients;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

@Configuration
@EnableAspectJAutoProxy
@Import(TelegramApiEnableConfiguration.class)
@ComponentScan("ru.eatthefrog.hatterBot")
@PropertySource("bot.properties")
public class SpringConfiguration {

    private String getMongoEnv(String env){
        var a =  System.getenv("flatHatBot_mongo_" + env);
        return a;
    }

    final String MONGO_USER = getMongoEnv("user");
    final String MONGO_PWD = getMongoEnv("pwd");
    final String MONGO_HOST = getMongoEnv("host");
    final String MONGO_PORT = getMongoEnv("port");
    final String MONGO_DB = getMongoEnv("db");

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
        Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
        mongoLogger.setLevel(Level.SEVERE);

        return MongoClients
                .create(String.format("mongodb://%s:%s@%s:%s",
                        MONGO_USER,
                        MONGO_PWD,
                        MONGO_HOST,
                        MONGO_PORT))
                .getDatabase(MONGO_DB);
    }
}
