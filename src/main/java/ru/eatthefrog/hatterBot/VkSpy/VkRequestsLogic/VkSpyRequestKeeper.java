package ru.eatthefrog.hatterBot.VkSpy.VkRequestsLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.MongoDBOperator.MongoSpyRequestsManager;
import ru.eatthefrog.hatterBot.SpringConfiguration;
import ru.eatthefrog.hatterBot.VkSpy.VkRequestsLogic.VkSpecialRequests.VkFriendsSpyRequest;
import ru.eatthefrog.hatterBot.VkSpy.VkRequestsLogic.VkSpecialRequests.VkOnlineSpyRequest;
import ru.eatthefrog.hatterBot.VkSpy.VkRequestsLogic.VkSpecialRequests.VkSpyRequestAbstract;

import java.util.ArrayList;

@Component
public class VkSpyRequestKeeper {
    @Autowired
    VkResponseRepresentationKeeper vkResponseRepresentationKeeper;
    @Autowired
    MongoSpyRequestsManager mongoSpyRequestsManager;
    ArrayList<VkSpyRequestAbstract> vkSpyRequestAbstractList = new ArrayList<VkSpyRequestAbstract>();
    public void addFriendsSpy(int userChatId, int userSpyToVkId) {
        var x = vkResponseRepresentationKeeper.getChatIdOnlineSpyRequests(
                userChatId
        );
        if (x != null) {
            for (VkSpyRequestAbstract i:
                    x) {
                if (i.getSpyVkId() == userSpyToVkId && i.getClass() == VkFriendsSpyRequest.class) {
                    return;
                }
            }
        }
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                SpringConfiguration.class
        );
        VkFriendsSpyRequest vkFriendsSpyRequest = context.getBean(
                VkFriendsSpyRequest.class
        );
        vkFriendsSpyRequest.setChatId(userChatId);
        vkFriendsSpyRequest.setSpyVkId(userSpyToVkId);
        mongoSpyRequestsManager.addFriendsSpyRequest(
                vkFriendsSpyRequest
        );
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
        var x = vkResponseRepresentationKeeper.getChatIdOnlineSpyRequests(
            userChatId
        );
        if (x != null) {
            for (VkSpyRequestAbstract i:
                    x) {
                if (i.getSpyVkId() == userSpyToVkId && i.getClass() == VkOnlineSpyRequest.class) {
                    return;
                }
            }
        }

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
    public void loadRequestsMongoDatabase() {
        loadOnlineRequestsMongoDatabase();
        loadFriendsRequestsMongoDatabase();
    }
    void loadOnlineRequestsMongoDatabase() {
        this.loadAbstractRequestsMongoDatabase(
                mongoSpyRequestsManager.getOnlineSpyRequests()
        );
    }
    void loadFriendsRequestsMongoDatabase() {
        this.loadAbstractRequestsMongoDatabase(
                mongoSpyRequestsManager.getFriendsSpyRequest()
        );
    }
    void loadAbstractRequestsMongoDatabase(ArrayList<VkSpyRequestAbstract> requests) {

        for (VkSpyRequestAbstract vkSpyRequestAbstract:
                requests) {
            this.vkSpyRequestAbstractList.add(vkSpyRequestAbstract);
            vkResponseRepresentationKeeper.addSpyRequestRepresentation(
                    vkSpyRequestAbstract.getChatId(),
                    vkSpyRequestAbstract
            );
        }
    }
}
