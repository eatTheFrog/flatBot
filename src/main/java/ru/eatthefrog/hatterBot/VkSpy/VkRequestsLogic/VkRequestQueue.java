package ru.eatthefrog.hatterBot.VkSpy.VkRequestsLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.VkSpy.VkRequestsLogic.VkSpecialRequests.VkSpyNullRequest;
import ru.eatthefrog.hatterBot.VkSpy.VkRequestsLogic.VkSpecialRequests.VkSpyRequestAbstract;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class VkRequestQueue {
    @Autowired
    VkSpyNullRequest vkSpyNullRequest;

    BlockingQueue<VkSpyRequestAbstract> blockingQueue = new LinkedBlockingQueue<>();
    public void addRequest(VkSpyRequestAbstract vkSpyRequest) {
        if (!vkSpyRequest.isQueued()) {
            vkSpyRequest.setQueued();
            this.blockingQueue.add(vkSpyRequest);
        }
    }
    public VkSpyRequestAbstract getRequest() {
        if (blockingQueue.isEmpty())
            return null;
        try {
            return this.blockingQueue.take();
        }
        catch (InterruptedException e) {
            return vkSpyNullRequest;
        }

    }
    public void addQueuedRequestSafely(VkSpyRequestAbstract vkSpyRequestAbstract) {
        this.blockingQueue.add(vkSpyRequestAbstract);
    }
}
