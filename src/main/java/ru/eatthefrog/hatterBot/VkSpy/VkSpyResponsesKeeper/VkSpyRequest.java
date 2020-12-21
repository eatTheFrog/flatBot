package ru.eatthefrog.hatterBot.VkSpy.VkSpyResponsesKeeper;

public interface VkSpyRequest {
    public boolean shouldUpdate();
    public void handle();
    public int getChatId();
    public int getSpyVkId();
}
