package ru.eatthefrog.hatterBot;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import org.springframework.context.annotation.*;
import ru.eatthefrog.hatterBot.DialogStateManager.UserDialogStatePosition;
import ru.eatthefrog.hatterBot.HTTPGetter.HTTPGetter1;
import ru.eatthefrog.hatterBot.HTTPGetter.HTTPGetter2;
import ru.eatthefrog.hatterBot.HTTPGetter.HTTPGetterable;
import ru.eatthefrog.hatterBot.MongoDBOperator.DataBaseLoginManager;
import ru.eatthefrog.hatterBot.MongoDBOperator.MongoLoginManager;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Random;

@Configuration
@EnableAspectJAutoProxy
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
    public Dictionary<Integer, UserDialogStatePosition> dictionaryBean() {
        return new Hashtable<>();
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
    public MongoDatabase mongoDatabaseBean() {
        MongoClientURI clientURI = new MongoClientURI(mongoUri);
        MongoClient mongoClient = new MongoClient(clientURI);
        return mongoClient.getDatabase(
                dataBaseName
        );
    }
}
