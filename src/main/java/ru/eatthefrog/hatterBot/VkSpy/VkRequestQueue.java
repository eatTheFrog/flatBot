package ru.eatthefrog.hatterBot.VkSpy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.VkSpy.VkSpyResponsesKeeper.VkOnlineSpyRequest;
import ru.eatthefrog.hatterBot.VkSpy.VkSpyResponsesKeeper.VkSpyNullRequest;
import ru.eatthefrog.hatterBot.VkSpy.VkSpyResponsesKeeper.VkSpyRequest;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class VkRequestQueue {
    @Autowired
    VkSpyNullRequest vkSpyNullRequest;

    BlockingQueue<VkSpyRequest> blockingQueue = new LinkedBlockingQueue<>();
    public void addRequest(VkSpyRequest vkSpyRequest) {
        this.blockingQueue.add(vkSpyRequest);
    }
    public VkSpyRequest getRequest() {
        if (blockingQueue.isEmpty())
            return null;
        try {
            return this.blockingQueue.take();
        }
        catch (InterruptedException e) {
            return vkSpyNullRequest;
        }

    }
}
