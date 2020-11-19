package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;
import ru.eatthefrog.hatterBot.ExternalApiProvider.ApiProvider;
import ru.eatthefrog.hatterBot.FinalStringProvider.FinalStringProvider;
import ru.eatthefrog.hatterBot.LoginManager.LoginInstance;
import ru.eatthefrog.hatterBot.LoginManager.LoginValidChecker;
import ru.eatthefrog.hatterBot.Message.TelegramMessage;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@Component
public abstract class DialogState {

    @Autowired
    FinalStringProvider finalStringProvider;

    @Autowired
    UnknownDialogState unknownDialogState;

    @Autowired
    NotYetImplementedState notYetImplementedState;

    @Autowired
    LoggedMainMenu loggedMainMenu;

    @Autowired
    LoginValidChecker loginValidChecker;

    @Autowired
    UnloggedMainMenu unloggedMainMenu;

    HashMap<String, DialogState> nextStatesMap;

    @Autowired
    public ApiProvider apiProvider;

    @PostConstruct
    protected void initializeStatesMap(){
        nextStatesMap = new HashMap<String, DialogState>();
    }

    public boolean isMoveForward(){
        return false;
    }

    public MainMenuDialogState getMainMenu(DialogStatePosition dsp){
        MainMenuDialogState mainMenu = isLogged(dsp.loginInstance)
                ? loggedMainMenu
                : unloggedMainMenu;
        return (MainMenuDialogState)mainMenu.sendPromptAndYourself(dsp);
    }

    public void sendResponse(String response, DialogStatePosition dsp){
        apiProvider.sendMessage(new TelegramMessage(response, dsp.chatID));
    }

    public DialogState sendPromptAndYourself(DialogStatePosition dsp){
        this.sendPrompt(dsp);
        return this;
    }

    public abstract void fillStateMap();

    public Boolean isLogged(LoginInstance loginInstance) {
        return loginValidChecker.checkValidLogin(loginInstance);
    }

    public String getPrompt(){
        return finalStringProvider.getFinalString(this.getClass().getSimpleName());
    }

    public void sendPrompt(DialogStatePosition dsp){
        sendResponse(getPrompt(), dsp);
    }

    public DialogState getNextState(String userInput, DialogStatePosition dsp) {
        DialogState nextState = nextStatesMap.getOrDefault(userInput, unknownDialogState);
        return nextState.sendPromptAndYourself(dsp);
    }
}
