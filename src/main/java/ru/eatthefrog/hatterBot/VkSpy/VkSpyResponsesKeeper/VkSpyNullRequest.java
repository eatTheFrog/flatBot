package ru.eatthefrog.hatterBot.VkSpy.VkSpyResponsesKeeper;

import org.springframework.stereotype.Component;

@Component
public class VkSpyNullRequest implements VkSpyRequest{
    @Override
    public boolean shouldUpdate() {
        return false;
    }

    @Override
    public void handle() {

    }

    @Override
    public int getChatId() {
        return 0;
    }

    @Override
    public int getSpyVkId() {
        return 0;
    }
}
