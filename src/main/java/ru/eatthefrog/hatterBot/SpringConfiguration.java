package ru.eatthefrog.hatterBot;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.eatthefrog.hatterBot.HTTPGetter.HTTPGetter1;
import ru.eatthefrog.hatterBot.HTTPGetter.HTTPGetter2;
import ru.eatthefrog.hatterBot.HTTPGetter.HTTPGetterable;

import java.util.Random;

@Configuration
@ComponentScan("ru.eatthefrog.hatterBot")
@PropertySource("bot.properties")
public class SpringConfiguration {
    final String dataBaseName = "FlatHatBotDatabase";
    final String mongoUri = "mongodb://127.0.0.1:27017";

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
