package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class NmapState extends DialogState{
    @Autowired
    NetworkingToolsMenuState networkingToolsMenuState;

    @Autowired
    NmapAllState nmapAllState;

    @Autowired
    NmapDefaultState nmapDefaultState;

    @Autowired
    NmapPortState nmapPortState;

    @PostConstruct
    public void fillStateMap() {
        nextStatesMap.put("/all", nmapAllState);
        nextStatesMap.put("/port", nmapPortState);
        nextStatesMap.put("/default", nmapDefaultState);
        nextStatesMap.put("/back", networkingToolsMenuState);
    }
}
