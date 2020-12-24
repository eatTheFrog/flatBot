package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.VkSpyStates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.CoreStates.DialogState;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.CoreStates.LoggedMainMenu;
import ru.eatthefrog.hatterBot.VkSpy.VkTokenManager.VkUserTokenManager;

@Component
public class VkTokenAskState extends DialogState {
    @Autowired
    VkUserTokenManager vkUserTokenManager;
    @Autowired
    LoggedMainMenu loggedMainMenu;

    @Override
    public void fillStateMap() {
    }

    @Override
    public DialogState getNextState(String userInput, DialogStatePosition dsp) {
        int chatId = dsp.chatID;
        vkUserTokenManager.addToken(dsp.chatID, userInput);
        this.sendResponse("Vk Token was added.", dsp);
        return this.getMainMenu(dsp);
    }
}
