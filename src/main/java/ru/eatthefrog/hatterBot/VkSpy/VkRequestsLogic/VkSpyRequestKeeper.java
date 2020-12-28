package ru.eatthefrog.hatterBot.VkSpy.VkRequestsLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.MongoDBOperator.MongoSpyRequestsManager;
import ru.eatthefrog.hatterBot.SpringConfiguration;
import ru.eatthefrog.hatterBot.VkSpy.VkRequestsLogic.VkSpecialRequests.VkFriendsSpyRequest;
import ru.eatthefrog.hatterBot.VkSpy.VkRequestsLogic.VkSpecialRequests.VkOnlineSpyRequest;
import ru.eatthefrog.hatterBot.VkSpy.VkRequestsLogic.VkSpecialRequests.VkSpyRequestAbstract;
import ru.eatthefrog.hatterBot.VkSpy.VkRequestsLogic.VkSpecialRequests.VkWallSpyRequest;

import java.util.ArrayList;

@Component
public class VkSpyRequestKeeper {
    @Autowired
    AnnotationConfigApplicationContext context;
    @Autowired
    VkResponseRepresentationKeeper vkResponseRepresentationKeeper;
    @Autowired
    MongoSpyRequestsManager mongoSpyRequestsManager;
    ArrayList<VkSpyRequestAbstract> vkSpyRequestAbstractList = new ArrayList<VkSpyRequestAbstract>();

    public void addFriendsSpy(int userChatId, int userSpyToVkId) {
        this.addAbstractSpyRequest(userChatId,
                userSpyToVkId,
                VkFriendsSpyRequest.class);
    }

    public void addOnlineSpy(int userChatId, int userSpyToVkId) {
        this.addAbstractSpyRequest(userChatId,
                userSpyToVkId,
                VkOnlineSpyRequest.class);
    }

    public void addWallSpy(int userChatId, int userSpyToVkId) {
        this.addAbstractSpyRequest(userChatId,
                userSpyToVkId,
                VkWallSpyRequest.class);
    }

    public ArrayList<VkSpyRequestAbstract> getRequests() {
        return this.vkSpyRequestAbstractList;
    }
    public void loadRequestsMongoDatabase() {
        loadOnlineRequestsMongoDatabase();
        loadFriendsRequestsMongoDatabase();
        loadWallRequestMongoDatabase();
    }
    void loadOnlineRequestsMongoDatabase() {
        this.loadAbstractRequestsMongoDatabase(
                mongoSpyRequestsManager.getOnlineSpyRequests()
        );
    }
    void loadWallRequestMongoDatabase() {
        this.loadAbstractRequestsMongoDatabase(
                mongoSpyRequestsManager.getWallSpyRequests()
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
    void addAbstractSpyRequest(int userChatId,
                               int userSpyToVkId,
                               Class vkSpyRequestAbstractClass) {
        var x = vkResponseRepresentationKeeper.getChatIdAbstractSpyRequests(
                userChatId
        );
        if (x != null) {
            for (VkSpyRequestAbstract i:
                    x) {
                if (i.getSpyVkId() == userSpyToVkId && i.getClass() == vkSpyRequestAbstractClass) {
                    return;
                }
            }
        }

        VkSpyRequestAbstract vkSpyRequestAbstract = (VkSpyRequestAbstract) context.getBean(
                vkSpyRequestAbstractClass
        );
        vkSpyRequestAbstract.setChatId(userChatId);
        vkSpyRequestAbstract.setSpyVkId(userSpyToVkId);
        mongoSpyRequestsManager.addSpyRequest(
                vkSpyRequestAbstract
        );
        this.vkSpyRequestAbstractList.add(
                vkSpyRequestAbstract
        );
        this.vkResponseRepresentationKeeper.addSpyRequestRepresentation(
                userChatId,
                vkSpyRequestAbstract
        );
    }
    public void removeSpyStates(int chatId, int vkId) {
        this.vkSpyRequestAbstractList.removeIf(req -> req.getChatId() == chatId && req.getSpyVkId() == vkId);
        this.vkResponseRepresentationKeeper.remove(chatId, vkId);
    }
}
