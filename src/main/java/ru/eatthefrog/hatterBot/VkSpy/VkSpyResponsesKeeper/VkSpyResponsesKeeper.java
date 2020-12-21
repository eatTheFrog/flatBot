package ru.eatthefrog.hatterBot.VkSpy.VkSpyResponsesKeeper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.MongoDBOperator.MongoSpyRequestsManager;
import ru.eatthefrog.hatterBot.SpringConfiguration;

import java.util.ArrayList;

@Component
public class VkSpyResponsesKeeper {
    @Autowired
    VkResponseRepresentationKeeper vkResponseRepresentationKeeper;
    @Autowired
    MongoSpyRequestsManager mongoSpyRequestsManager;
    ArrayList<VkSpyRequest> vkOnlineSpyRequests = new ArrayList<VkSpyRequest>();

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
        this.vkOnlineSpyRequests.add(
                vkOnlineSpyRequest
        );
        this.vkResponseRepresentationKeeper.addOnlineSpyRequestRepresentation(
                userChatId,
                vkOnlineSpyRequest
        );

        context.close();
    }
    public ArrayList<VkSpyRequest> getRequests() {
        return this.vkOnlineSpyRequests;
    }
    public void loadResponsesMongoDatabase() {
        loadOnlineResponsesMongoDatabase();
    }
    public void loadOnlineResponsesMongoDatabase() {

        ArrayList<VkOnlineSpyRequest> vkOnlineSpyRequests = mongoSpyRequestsManager.getOnlineSpyRequests();

        for (VkOnlineSpyRequest vkOnlineSpyRequest:
             vkOnlineSpyRequests) {
            this.vkOnlineSpyRequests.add(vkOnlineSpyRequest);
            vkResponseRepresentationKeeper.addOnlineSpyRequestRepresentation(
                    vkOnlineSpyRequest.chatId,
                    vkOnlineSpyRequest
            );
        }
        System.out.println(this.vkOnlineSpyRequests);
    }
}
