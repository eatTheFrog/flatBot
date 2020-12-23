package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.VkSpyStates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.CoreStates.DialogState;
import ru.eatthefrog.hatterBot.VkSpy.VkRequestsLogic.VkSpyRequestKeeper;

@Component
public class SpyVkAddSpyFriendsState extends DialogState {
    @Override
    public void fillStateMap() {

    }
    @Autowired
    SpyVkState spyVkState;
    @Autowired
    VkSpyRequestKeeper vkSpyRequestKeeper;


    public DialogState getNextState(String userInput, DialogStatePosition dsp) {
        try {
            vkSpyRequestKeeper.addFriendsSpy(
                    dsp.chatID,
                    Integer.parseInt(userInput)
            );
            this.sendResponse("Пользователь добавлен.", dsp);

        }
        catch (NumberFormatException e) {
            this.sendResponse("Набранный вами ID был не верный. Это должно быть натуральное число.", dsp);
        }

        return spyVkState.sendPromptAndYourself(dsp);
    }

}
