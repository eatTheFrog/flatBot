package ru.eatthefrog.hatterBot.VkSpy.VkSpyResponsesKeeper;

import org.springframework.stereotype.Component;

@Component
public class VkSpyNullRequest extends VkSpyRequestAbstract {
    @Override
    public void handle() {}
}
