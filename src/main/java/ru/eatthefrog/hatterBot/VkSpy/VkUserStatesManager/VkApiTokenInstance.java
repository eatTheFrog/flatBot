package ru.eatthefrog.hatterBot.VkSpy.VkUserStatesManager;

public class VkApiTokenInstance {
    String token_value;
    long lastTimeUsed = 0;
    public VkApiTokenInstance(String token) {
        this.token_value = token;
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
