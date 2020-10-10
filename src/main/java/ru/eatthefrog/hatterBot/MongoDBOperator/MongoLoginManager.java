package ru.eatthefrog.hatterBot.MongoDBOperator;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.LoginManager.LoginInstance;

import javax.annotation.PostConstruct;

@Component
public class MongoLoginManager {
    MongoCollection<Document> loginCollection;

    @Autowired
    MongoDatabase mongoDatabase;

    @PostConstruct
    void initLoginCollectionField() {
        loginCollection = mongoDatabase.getCollection("loginCollection");
    }

    public void putLoginInstance(LoginInstance loginInstance) {
        Document mongoLoginInstance = new Document() {{
           append("login", loginInstance.login);
           append("password", loginInstance.password);
        }};
        loginCollection.insertOne(mongoLoginInstance);
    }
    public Document getLoginInstanceDocument(String login) {
        if (login == null)
            return null;
        return loginCollection.find(new Document("login", login)).first();
    }


}
