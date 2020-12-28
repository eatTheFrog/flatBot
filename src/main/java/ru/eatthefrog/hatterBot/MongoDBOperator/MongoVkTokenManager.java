package ru.eatthefrog.hatterBot.MongoDBOperator;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MongoVkTokenManager {
    @Autowired
    MongoDatabase mongoDatabase;
    MongoCollection<Document> collection;
    @PostConstruct
    void initCollection() {
        this.collection = this.mongoDatabase.getCollection("vkTokensCollection");
    }
    public String getToken(int chatId) {
        var x = this.collection.find(new Document("chatId", chatId));
        return x.first().getString("token");
    }
    public void setToken(int chatId, String tokenString) {
        var in_collection_instance = this.getToken(chatId);
        if (in_collection_instance == null) {
            this.collection.insertOne(new Document() {{
                append("chatId", chatId);
                append("token", tokenString);
            }});
        }
        else {
            if (!in_collection_instance.equals(tokenString)) {
                this.collection.deleteOne(new Document("chatId", chatId));
                this.collection.insertOne(new Document() {{
                    append("chatId", chatId);
                    append("token", tokenString);
                }});
            }
        }
    }
}
