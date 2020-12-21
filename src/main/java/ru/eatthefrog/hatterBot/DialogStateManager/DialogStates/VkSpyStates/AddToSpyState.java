package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.VkSpyStates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.CoreStates.DialogState;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.CoreStates.LoginAskPasswordDialogState;
import ru.eatthefrog.hatterBot.MongoDBOperator.MongoSpyRequestsManager;
import ru.eatthefrog.hatterBot.VkSpy.VkSpyResponsesKeeper.VkSpyResponsesKeeper;

@Component
public class AddToSpyState extends DialogState {
    @Override
    public void fillStateMap() {

    }
    @Autowired
    SpyVkOnlineState spyVkOnlineState;
    @Autowired
    VkSpyResponsesKeeper vkSpyResponsesKeeper;


    public DialogState getNextState(String userInput, DialogStatePosition dsp) {
        vkSpyResponsesKeeper.addOnlineSpy(
                dsp.chatID,
                Integer.parseInt(userInput)
        );

        return spyVkOnlineState.sendPromptAndYourself(dsp);
    }

}
