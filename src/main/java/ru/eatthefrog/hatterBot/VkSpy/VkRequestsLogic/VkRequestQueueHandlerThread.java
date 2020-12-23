package ru.eatthefrog.hatterBot.VkSpy.VkRequestsLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.VkSpy.VkRequestsLogic.VkSpecialRequests.VkSpyRequestAbstract;

@Component
public class VkRequestQueueHandlerThread implements Runnable {
    @Autowired
    VkRequestQueue vkRequestQueue;
    public void startThreadPool() {
        for (int i = 0; i < 1; i++) {
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

            VkSpyRequestAbstract vkSpyRequest = vkRequestQueue.getRequest();
            if (vkSpyRequest != null){
                if (vkSpyRequest.isTokenReady()) {
                    vkSpyRequest.handle();
                    vkSpyRequest.setUnQueued();
                }
                else {
                    this.vkRequestQueue.addQueuedRequestSafely(vkSpyRequest);
                }

            }
        }
    }
}
