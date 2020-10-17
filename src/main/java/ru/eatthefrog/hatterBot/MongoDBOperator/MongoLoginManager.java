package ru.eatthefrog.hatterBot.MongoDBOperator;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import ru.eatthefrog.hatterBot.DebugPrinter;
import ru.eatthefrog.hatterBot.MD5StringHasher.MD5StringHasher;

import javax.annotation.PostConstruct;


public class MongoLoginManager implements DataBaseLoginManager {
    MongoCollection<Document> loginCollection;
    @Autowired
    DebugPrinter debugPrinter;


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
        debugPrinter.print("DB was touched", this);
        if (login == null)
            return null;
        Document doc = loginCollection.find(new Document("login", login)).first();
        if (doc == null)
            return null;
        return (String) doc.get("password");
    }

    public void resetDatabase() {

    }

    @Override
    public int getCount() {
        return 0;
    }
}
