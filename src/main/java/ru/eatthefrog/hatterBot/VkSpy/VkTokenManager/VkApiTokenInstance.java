package ru.eatthefrog.hatterBot.VkSpy.VkTokenManager;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class VkApiTokenInstance {
    String token_value;
    long lastTimeUsed = 0;
    Lock tokenLock = new ReentrantLock();
    public VkApiTokenInstance(String token) {
        this.token_value = token;
    }
    public Lock getLock() {
        return this.tokenLock;
    }
    public String getValue() {
        return this.token_value;
    }
    public boolean isReady() {
        return System.currentTimeMillis() - this.lastTimeUsed > 3000;
    }
    public void makeCooldown() {
        this.lastTimeUsed = System.currentTimeMillis();
    }


}
