package ru.eatthefrog.hatterBot.MongoDBOperator;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.LoginManager.LoginInstance;
import ru.eatthefrog.hatterBot.MD5StringHasher.MD5StringHasher;

import javax.annotation.PostConstruct;


public class MongoLoginManager implements DataBaseLoginManager {
    MongoCollection<Document> loginCollection;

    @Autowired
    MD5StringHasher md5StringHasher;

    @Autowired
    MongoDatabase mongoDatabase;

    @PostConstruct
    void initLoginCollectionField() {
        loginCollection = mongoDatabase.getCollection("loginCollection");
    }

    public void putLoginPasswordHash(String login, String hashPass) {
        Document mongoLoginInstance = new Document() {{
           append("login", login);
           append("password", hashPass);
        }};
        loginCollection.insertOne(mongoLoginInstance);
    }

    public String getHashPasswordForLogin(String login) {
        if (login == null)
            return null;
        Document doc = loginCollection.find(new Document("login", login)).first();
        if (doc == null)
            return null;
        return (String) doc.get("password");
    }

    public void resetDatabase() {
        loginCollection.deleteMany(new Document());
    }

    @Override
    public int getCount() {
        return 0;
    }
}
