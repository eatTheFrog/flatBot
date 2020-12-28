package ru.eatthefrog.hatterBot.MongoUtils;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class MongoUtils {
    private final String TOKEN_COLLECTION = "tokenCollection";

    private boolean collectionExists(MongoDatabase mongoDatabase, String collectionName){
        for (final String name : mongoDatabase.listCollectionNames()) {
            if (name.equalsIgnoreCase(collectionName)) {
                return true;
            }
        }
        return false;
    }

    private String getToken(MongoCollection<Document> collection, String platform){
        Document doc = collection.find(new Document("platform", platform)).first();
        if (doc == null)

            return null;
        return doc.getString("token");
    }

    private String fetchToken(MongoDatabase mongoDatabase, String platform){
        if (! tokeCollectionExists(mongoDatabase))
            return null;

        return getToken(mongoDatabase.getCollection(TOKEN_COLLECTION), platform);
    }

    public String fetchTelegramToken(MongoDatabase mongoDatabase){
        return fetchToken(mongoDatabase, "telegram");
    }

    private boolean tokeCollectionExists(MongoDatabase mongoDatabase){
        return collectionExists(mongoDatabase, TOKEN_COLLECTION);
    }
}
