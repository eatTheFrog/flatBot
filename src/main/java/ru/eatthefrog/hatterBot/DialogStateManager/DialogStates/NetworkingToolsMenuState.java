package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class NetworkingToolsMenuState extends DialogState {

    @Autowired
    PingState pingState;

    @Autowired
    NmapState nmapState;

    @PostConstruct
    public void fillStateMap() {
        nextStatesMap.put("/ping", pingState);
        nextStatesMap.put("/nmap", nmapState);
        nextStatesMap.put("/back", loggedMainMenu);
    }
}
