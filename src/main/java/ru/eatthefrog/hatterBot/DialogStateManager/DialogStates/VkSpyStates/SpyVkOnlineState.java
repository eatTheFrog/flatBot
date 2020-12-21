package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.VkSpyStates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.CoreStates.DialogState;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.CoreStates.InfoState;

import javax.annotation.PostConstruct;

@Component
public class SpyVkOnlineState extends DialogState {
    @Autowired
    GetSpyListState getSpyListState;
    @Autowired
    AddToSpyState addToSpyState;
    @Autowired
    InfoState infoState;
    @Autowired
    RemoveFromSpyState removeFromSpyState;
    @PostConstruct
    public void fillStateMap() {
        nextStatesMap.put("/get_spy_list", getSpyListState);
        nextStatesMap.put("/add_to_spy", addToSpyState);
        nextStatesMap.put("/remove_from_spy", removeFromSpyState);
    }
}
