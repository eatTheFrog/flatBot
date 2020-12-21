package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.VkSpyStates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.CoreStates.DialogState;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.VkSpyStates.SpyVkOnlineState;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.VkSpyStates.VkTokenAskState;

import javax.annotation.PostConstruct;

@Component
public class VkToolsMenuSelectState extends DialogState {
    @Autowired
    VkTokenAskState vkTokenAskState;
    @Autowired
    SpyVkOnlineState spyVkOnlineState;
    @PostConstruct
    public void fillStateMap() {
        nextStatesMap.put("/set_vk_token", vkTokenAskState);
        nextStatesMap.put("/spy_vk_online", spyVkOnlineState);
    }
}
