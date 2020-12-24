package ru.eatthefrog.hatterBot.VkSpy.VkRequestsLogic;

import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.VkSpy.VkRequestsLogic.VkSpecialRequests.VkSpyRequestAbstract;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class VkResponseRepresentationKeeper {
    AbstractMap<Integer, ArrayList<VkSpyRequestAbstract>> vkSpyRequestsByChatId = new ConcurrentHashMap<>();
    public void addSpyRequestRepresentation(int chatId, VkSpyRequestAbstract vkSpyRequest) {
        ArrayList<VkSpyRequestAbstract> vkOnlineSpyRequestsTemp = this.vkSpyRequestsByChatId.get(chatId);
        if (vkOnlineSpyRequestsTemp == null) {
            this.initEmptyArray(chatId);
            vkOnlineSpyRequestsTemp = this.vkSpyRequestsByChatId.get(chatId);
        }
        vkOnlineSpyRequestsTemp.add(vkSpyRequest);
    }
    public ArrayList<VkSpyRequestAbstract> initEmptyArray(int chatId) {
        var x = new ArrayList<VkSpyRequestAbstract>();
        vkSpyRequestsByChatId.put(chatId, x);
        return x;
    }

    public ArrayList<VkSpyRequestAbstract> getChatIdAbstractSpyRequests(int chatId) {
        var to_return =  this.vkSpyRequestsByChatId.get(chatId);
        if (to_return == null) {
            return this.initEmptyArray(chatId);
        }
        return to_return;
    }
}
