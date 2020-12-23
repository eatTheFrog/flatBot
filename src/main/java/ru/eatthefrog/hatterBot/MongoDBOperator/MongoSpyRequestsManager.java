package ru.eatthefrog.hatterBot.MongoDBOperator;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.SpringConfiguration;
import ru.eatthefrog.hatterBot.VkSpy.VkRequestsLogic.VkSpecialRequests.VkFriendsSpyRequest;
import ru.eatthefrog.hatterBot.VkSpy.VkRequestsLogic.VkSpecialRequests.VkOnlineSpyRequest;
import ru.eatthefrog.hatterBot.VkSpy.VkRequestsLogic.VkSpecialRequests.VkSpyRequestAbstract;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Component
public class MongoSpyRequestsManager {
    MongoCollection<Document> onlineSpyCollection;
    MongoCollection<Document> friendsSpyCollection;

    @Autowired
    MongoDatabase mongoDatabase;

    @PostConstruct
    void initLoginCollectionField() {
        onlineSpyCollection = mongoDatabase.getCollection("spyOnlineCollection");
        friendsSpyCollection = mongoDatabase.getCollection("spyFriendsCollection");
    }
    void addSpyRequest(VkSpyRequestAbstract vkSpyRequestAbstract,
                       MongoCollection<Document> collection) {
        Document mongoVkOnlineSpyRequest = new Document() {{
            append("chatId", vkSpyRequestAbstract.getChatId());
            append("spyVkId", vkSpyRequestAbstract.getSpyVkId());
        }};
        collection.insertOne(mongoVkOnlineSpyRequest);
    }
    public void addOnlineSpyRequest(VkOnlineSpyRequest vkOnlineSpyRequest) {
        this.addSpyRequest(vkOnlineSpyRequest,
                onlineSpyCollection);
    }
    public void addFriendsSpyRequest(VkFriendsSpyRequest vkFriendsSpyRequest) {
        this.addSpyRequest(vkFriendsSpyRequest,
                friendsSpyCollection);
    }
    ArrayList<VkSpyRequestAbstract> getSpyRequests(MongoCollection<Document> collection,
                                                        Class vkSpyRequestAbstract) {
        FindIterable<Document> docs = collection.find(new Document());

        ArrayList<VkSpyRequestAbstract> temp = new ArrayList<VkSpyRequestAbstract>();

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                SpringConfiguration.class
        );
        for (Document doc:
                docs) {
            System.out.println(doc);

            var vkOnlineSpyRequest = (VkSpyRequestAbstract) context.getBean(vkSpyRequestAbstract);
            vkOnlineSpyRequest.setSpyVkId(doc.getInteger("spyVkId"));
            vkOnlineSpyRequest.setChatId(doc.getInteger("chatId"));
            temp.add(vkOnlineSpyRequest);

        }
        context.close();
        return temp;
    }
    public ArrayList<VkSpyRequestAbstract> getOnlineSpyRequests() {
        return getSpyRequests(this.onlineSpyCollection,
                VkOnlineSpyRequest.class);
    }
    public ArrayList<VkSpyRequestAbstract> getFriendsSpyRequest() {
        return getSpyRequests(this.friendsSpyCollection,
                VkFriendsSpyRequest.class);
    }

}
