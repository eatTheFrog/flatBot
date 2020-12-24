package ru.eatthefrog.hatterBot.VkSpy.VkRequestsLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.VkSpy.VkRequestsLogic.VkSpecialRequests.VkSpyRequestAbstract;

import java.util.ArrayList;

@Component
public class VkRequestQueuePutter implements Runnable {
    @Autowired
    VkRequestQueuesHashmapProvider vkRequestQueuesHashmapProvider;
    @Autowired
    VkSpyRequestKeeper vkSpyRequestKeeper;
    void putOnQueue() {
        ArrayList<VkSpyRequestAbstract> requests = vkSpyRequestKeeper.getRequests();
        for (VkSpyRequestAbstract vkSpyRequest:
                requests) {

            if (vkSpyRequest.shouldUpdate()) {

                vkRequestQueuesHashmapProvider.addRequest(

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
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.putOnQueue();
        }
    }
}
