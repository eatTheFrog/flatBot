package ru.eatthefrog.hatterBot.VkSpy.VkRequestsLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.VkSpy.VkRequestsLogic.VkSpecialRequests.VkSpyNullRequest;
import ru.eatthefrog.hatterBot.VkSpy.VkRequestsLogic.VkSpecialRequests.VkSpyRequestAbstract;

import java.util.AbstractMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class VkRequestQueuesHashmapProvider {
    @Autowired
    VkSpyNullRequest vkSpyNullRequest;

    AbstractMap<Integer, BlockingQueue<VkSpyRequestAbstract>> blockingQueues = new ConcurrentHashMap<>();
    public void addRequest(VkSpyRequestAbstract vkSpyRequest) {
        int chatId = vkSpyRequest.getChatId();
        if (!blockingQueues.containsKey(chatId)) {
            blockingQueues.put(chatId,
                    new LinkedBlockingQueue<>());
        }
        if (!vkSpyRequest.isQueued()) {
            vkSpyRequest.setQueued();
            this.blockingQueues.get(chatId).add(vkSpyRequest);
        }
    }
    public VkSpyRequestAbstract getRequest() {
        for (var chatId:
             blockingQueues.keySet()) {
            var queue = blockingQueues.get(chatId);
            var element = queue.peek();
            if (element != null && element.isTokenReady()) {
                try {
                    element = queue.take();
                    return element;
                }
                catch (InterruptedException e) {
                    return null;
                }

            }
        }
        return null;

    }
}
