package ru.eatthefrog.hatterBot.DBOperator.MongoImplementation;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.eatthefrog.hatterBot.DBOperator.DataBaseLoginManager;


public class MongoEnableConfiguration {

    final String dataBaseName = "FlatHatBotDatabase";
    final String mongoUri = "mongodb://127.0.0.1:27017";

    @Bean
    public DataBaseLoginManager dataBaseLoginManagerBean() {
        return new MongoLoginManager();
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
