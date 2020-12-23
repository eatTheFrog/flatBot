package ru.eatthefrog.hatterBot.VkSpy.VkRequestsLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.VkSpy.VkRequestsLogic.VkSpecialRequests.VkSpyRequestAbstract;

import java.util.ArrayList;

@Component
public class VkRequestQueuePutter implements Runnable {
    @Autowired
    VkRequestQueue vkRequestQueue;
    @Autowired
    VkSpyRequestKeeper vkSpyRequestKeeper;
    void putOnQueue() {
        ArrayList<VkSpyRequestAbstract> requests = vkSpyRequestKeeper.getRequests();
        for (VkSpyRequestAbstract vkSpyRequest:
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
