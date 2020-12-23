package ru.eatthefrog.hatterBot.VkSpy.VkSpyResponsesKeeper;

import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.VkSpy.VkProfileManager.VkProfileUnit;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class VkResponseRepresentationKeeper {
    AbstractMap<Integer, ArrayList<VkSpyRequestAbstract>> vkSpyRequestsByChatId = new ConcurrentHashMap<>();
    public void addSpyRequestRepresentation(int chatId, VkSpyRequestAbstract vkSpyRequest) {
        ArrayList<VkSpyRequestAbstract> vkOnlineSpyRequestsTemp = this.vkSpyRequestsByChatId.get(chatId);
        if (vkOnlineSpyRequestsTemp == null) {
            vkOnlineSpyRequestsTemp = new ArrayList<>();
            vkSpyRequestsByChatId.put(chatId, vkOnlineSpyRequestsTemp);
        }
        vkOnlineSpyRequestsTemp.add(vkSpyRequest);
    }

    public ArrayList<VkSpyRequestAbstract> getChatIdOnlineSpyRequests(int chatId) {
        return this.vkSpyRequestsByChatId.get(chatId);
    }
}
