package ru.eatthefrog.hatterBot.VkSpy.VkRequestsLogic.VkSpecialRequests;

import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.VkSpy.VkRequestsLogic.VkSpecialRequests.VkSpyRequestAbstract;

@Component
public class VkSpyNullRequest extends VkSpyRequestAbstract {
    @Override
    public void handle() {}

    @Override
    public String getSpyPrompt() {
        return null;
    }
}
