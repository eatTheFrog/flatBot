package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.VkSpyStates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.CoreStates.DialogState;
import ru.eatthefrog.hatterBot.VkSpy.VkRequestsLogic.VkResponseRepresentationKeeper;
import ru.eatthefrog.hatterBot.VkSpy.VkRequestsLogic.VkSpecialRequests.VkSpyRequestAbstract;

@Component
public class GetSpyListState extends DialogState {
    @Override
    public void fillStateMap() {
    }
    @Autowired
    SpyVkState spyVkState;
    @Autowired
    VkResponseRepresentationKeeper vkResponseRepresentationKeeper;
    @Override
    public boolean isMoveForward() {
        return true;
    }

    @Override
    public DialogState getNextState(String userInput, DialogStatePosition dsp) {
        var x = vkResponseRepresentationKeeper.getChatIdOnlineSpyRequests(dsp.chatID);
        if (x == null) {
            sendResponse("You are not spying online now.", dsp);
            return this.spyVkState.sendPromptAndYourself(dsp);

        }
        String response = "";
        for (VkSpyRequestAbstract i:
                x) {
            response += i.getSpyPrompt() + "\n";
        }

        sendResponse(response, dsp);
        return this.spyVkState.sendPromptAndYourself(dsp);
    }




}
