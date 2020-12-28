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
        System.out.println(requests.size());
        System.out.println("requests up");
        for (VkSpyRequestAbstract vkSpyRequest:
                requests) {
            if (vkSpyRequest.shouldUpdate()) {
                System.out.println("added request!");
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
            System.out.println("queue putter work");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.putOnQueue();
        }
    }
}
