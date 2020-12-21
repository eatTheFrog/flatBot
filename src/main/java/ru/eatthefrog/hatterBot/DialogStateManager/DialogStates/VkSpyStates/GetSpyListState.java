package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.VkSpyStates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.CoreStates.DialogState;
import ru.eatthefrog.hatterBot.VkSpy.VkSpyResponsesKeeper.VkOnlineSpyRequest;
import ru.eatthefrog.hatterBot.VkSpy.VkSpyResponsesKeeper.VkResponseRepresentationKeeper;

@Component
public class GetSpyListState extends DialogState {
    @Override
    public void fillStateMap() {
    }
    @Autowired
    SpyVkOnlineState spyVkOnlineState;
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
            return this.spyVkOnlineState.sendPromptAndYourself(dsp);

        }
        String response = "";
        for (VkOnlineSpyRequest i:
                x) {
            response += String.valueOf(i.getSpyVkId()) + "\n";
        }

        sendResponse(response, dsp);
        return this.spyVkOnlineState.sendPromptAndYourself(dsp);
    }




}
