package ru.eatthefrog.hatterBot.VkSpy.VkSpyResponsesKeeper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.MongoDBOperator.MongoSpyRequestsManager;
import ru.eatthefrog.hatterBot.SpringConfiguration;

import java.util.ArrayList;

@Component
public class VkSpyRequestKeeper {
    @Autowired
    VkResponseRepresentationKeeper vkResponseRepresentationKeeper;
    @Autowired
    MongoSpyRequestsManager mongoSpyRequestsManager;
    ArrayList<VkSpyRequestAbstract> vkSpyRequestAbstractList = new ArrayList<VkSpyRequestAbstract>();
    public void addFriendsSpy(int userChatId, int userSpyToVkId) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                SpringConfiguration.class
        );
        VkFriendsSpyRequest vkFriendsSpyRequest = context.getBean(
                VkFriendsSpyRequest.class
        );
        vkFriendsSpyRequest.setChatId(userChatId);
        vkFriendsSpyRequest.setSpyVkId(userSpyToVkId);
        /*mongoSpyRequestsManager.addOnlineSpyRequest(
                vkFriendsSpyRequest
        );*/
        this.vkSpyRequestAbstractList.add(
                vkFriendsSpyRequest
        );
        this.vkResponseRepresentationKeeper.addSpyRequestRepresentation(
                userChatId,
                vkFriendsSpyRequest
        );

        context.close();
    }
    public void addOnlineSpy(int userChatId, int userSpyToVkId) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                SpringConfiguration.class
        );
        VkOnlineSpyRequest vkOnlineSpyRequest = context.getBean(
                VkOnlineSpyRequest.class
        );
        vkOnlineSpyRequest.setChatId(userChatId);
        vkOnlineSpyRequest.setSpyVkId(userSpyToVkId);
        mongoSpyRequestsManager.addOnlineSpyRequest(
            vkOnlineSpyRequest
        );
        this.vkSpyRequestAbstractList.add(
                vkOnlineSpyRequest
        );
        this.vkResponseRepresentationKeeper.addSpyRequestRepresentation(
                userChatId,
                vkOnlineSpyRequest
        );

        context.close();
    }
    public ArrayList<VkSpyRequestAbstract> getRequests() {
        return this.vkSpyRequestAbstractList;
    }
    public void loadResponsesMongoDatabase() {
        loadOnlineResponsesMongoDatabase();
    }
    public void loadOnlineResponsesMongoDatabase() {

        ArrayList<VkOnlineSpyRequest> vkOnlineSpyRequests = mongoSpyRequestsManager.getOnlineSpyRequests();

        for (VkOnlineSpyRequest vkOnlineSpyRequest:
             vkOnlineSpyRequests) {
            this.vkSpyRequestAbstractList.add(vkOnlineSpyRequest);
            vkResponseRepresentationKeeper.addSpyRequestRepresentation(
                    vkOnlineSpyRequest.chatId,
                    vkOnlineSpyRequest
            );
        }
        System.out.println(this.vkSpyRequestAbstractList);
    }
}
