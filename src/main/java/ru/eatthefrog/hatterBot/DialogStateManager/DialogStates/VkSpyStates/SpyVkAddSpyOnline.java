package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.VkSpyStates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.CoreStates.DialogState;
import ru.eatthefrog.hatterBot.VkSpy.VkSpyResponsesKeeper.VkSpyRequestKeeper;

@Component
public class SpyVkAddSpyOnline extends DialogState {
    @Override
    public void fillStateMap() {

    }
    @Autowired
    SpyVkState spyVkState;
    @Autowired
    VkSpyRequestKeeper vkSpyRequestKeeper;


    public DialogState getNextState(String userInput, DialogStatePosition dsp) {
        vkSpyRequestKeeper.addOnlineSpy(
                dsp.chatID,
                Integer.parseInt(userInput)
        );

        return spyVkState.sendPromptAndYourself(dsp);
    }

}
