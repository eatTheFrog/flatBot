package ru.eatthefrog.hatterBot.VkSpy.VkSpyResponsesKeeper;

import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.VkSpy.VkProfileManager.VkProfileUnit;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class VkResponseRepresentationKeeper {
    AbstractMap<Integer, ArrayList<VkOnlineSpyRequest>> vkOnlineSpyRequestsByChatId = new ConcurrentHashMap<>();
    public void addOnlineSpyRequestRepresentation(int chatId, VkOnlineSpyRequest vkOnlineSpyRequest) {
        ArrayList<VkOnlineSpyRequest> vkOnlineSpyRequestsTemp = this.vkOnlineSpyRequestsByChatId.get(chatId);
        if (vkOnlineSpyRequestsTemp == null) {
            vkOnlineSpyRequestsTemp = new ArrayList<>();
            vkOnlineSpyRequestsByChatId.put(chatId, vkOnlineSpyRequestsTemp);
        }
        vkOnlineSpyRequestsTemp.add(vkOnlineSpyRequest);
    }

    public ArrayList<VkOnlineSpyRequest> getChatIdOnlineSpyRequests(int chatId) {
        return this.vkOnlineSpyRequestsByChatId.get(chatId);
    }
}
