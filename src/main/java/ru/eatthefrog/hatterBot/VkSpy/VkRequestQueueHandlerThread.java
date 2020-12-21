package ru.eatthefrog.hatterBot.VkSpy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.VkSpy.VkSpyResponsesKeeper.VkSpyRequest;

@Component
public class VkRequestQueueHandlerThread implements Runnable {
    @Autowired
    VkRequestQueue vkRequestQueue;
    public void startThreadPool() {
        for (int i = 0; i < 4; i++) {
            new Thread(this).start();
        }
    }
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            VkSpyRequest vkSpyRequest = vkRequestQueue.getRequest();
            if (vkSpyRequest != null){
                vkSpyRequest.handle();
            }
        }
    }
}
