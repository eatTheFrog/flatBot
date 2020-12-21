package ru.eatthefrog.hatterBot.VkSpy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.VkSpy.VkSpyResponsesKeeper.VkSpyRequest;
import ru.eatthefrog.hatterBot.VkSpy.VkSpyResponsesKeeper.VkSpyResponsesKeeper;

import java.util.ArrayList;

@Component
public class VkRequestQueuePutter implements Runnable {
    @Autowired
    VkRequestQueue vkRequestQueue;
    @Autowired
    VkSpyResponsesKeeper vkSpyResponsesKeeper;
    void putOnQueue() {
        ArrayList<VkSpyRequest> requests = vkSpyResponsesKeeper.getRequests();
        for (VkSpyRequest vkSpyRequest:
                requests) {
            if (vkSpyRequest.shouldUpdate()) {
                vkRequestQueue.addRequest(
                        vkSpyRequest
                );
            }
        }
    }
    public void startThread() {
        new Thread(this).start();
    }
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {

            this.putOnQueue();
        }
    }
}
