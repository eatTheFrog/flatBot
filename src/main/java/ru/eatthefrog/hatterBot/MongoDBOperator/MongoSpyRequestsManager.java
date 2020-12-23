package ru.eatthefrog.hatterBot.MongoDBOperator;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.SpringConfiguration;
import ru.eatthefrog.hatterBot.VkSpy.VkSpyResponsesKeeper.VkOnlineSpyRequest;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Component
public class MongoSpyRequestsManager {
    MongoCollection<Document> onlineCollection;

    @Autowired
    MongoDatabase mongoDatabase;

    @PostConstruct
    void initLoginCollectionField() {
        onlineCollection = mongoDatabase.getCollection("spyOnlineCollection");
    }

    public void addOnlineSpyRequest(VkOnlineSpyRequest vkOnlineSpyRequest) {
        Document mongoVkOnlineSpyRequest = new Document() {{
            append("chatId", vkOnlineSpyRequest.getChatId());
            append("spyVkId", vkOnlineSpyRequest.getSpyVkId());
        }};
        onlineCollection.insertOne(mongoVkOnlineSpyRequest);
    }
    public ArrayList<VkOnlineSpyRequest> getOnlineSpyRequests() {
        FindIterable<Document> docs = this.onlineCollection.find(new Document());

        ArrayList<VkOnlineSpyRequest> temp = new ArrayList<VkOnlineSpyRequest>();

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                SpringConfiguration.class
        );
        for (Document doc:
             docs) {
            System.out.println(doc);

            var vkOnlineSpyRequest = context.getBean(VkOnlineSpyRequest.class);
            vkOnlineSpyRequest.setSpyVkId(doc.getInteger("spyVkId"));
            vkOnlineSpyRequest.setChatId(doc.getInteger("chatId"));
            temp.add(vkOnlineSpyRequest);

        }
        context.close();
        return temp;
    }
}
