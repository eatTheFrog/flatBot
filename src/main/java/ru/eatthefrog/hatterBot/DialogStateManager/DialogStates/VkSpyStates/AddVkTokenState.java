package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.VkSpyStates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.CoreStates.DialogState;
import ru.eatthefrog.hatterBot.VkSpy.VkRequestsLogic.VkSpyRequestKeeper;
import ru.eatthefrog.hatterBot.VkSpy.VkTokenManager.VkUserTokenManager;

@Component
public class AddVkTokenState extends DialogState {
    @Override
    public void fillStateMap() {

    }
    @Autowired
    SpyVkState spyVkState;
    @Autowired
    VkUserTokenManager vkUserTokenManager;


    public DialogState getNextState(String userInput, DialogStatePosition dsp) {
        this.vkUserTokenManager.addToken(dsp.chatID, userInput);
        this.sendResponse("Token VK added!", dsp);
        return spyVkState.sendPromptAndYourself(dsp);
    }

}
