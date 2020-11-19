package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;

@Component
public class RegistrationAskLoginDialogState extends DialogState {

    @Autowired
    RegistrationAskPasswordDialogState registrationAskPasswordDialogState;

    public DialogState getNextState(String userInput, DialogStatePosition dsp)  {
        if (loginValidChecker.checkIfLoginIsFree(userInput)) {
            //Сразу запоминаем свободный логин, чтобы никто не занял.
            //Позже запихнём в БД.
            loginValidChecker.addLoginToCache(userInput);
            dsp.loginInstance.setLogin(userInput);
            return registrationAskPasswordDialogState.sendPromptAndYourself(dsp);
        }
        sendResponse("This Login is already taken. Try another one!", dsp);
        return getMainMenu(dsp);
    }

    @Override
    public void fillStateMap() {
    }
}
