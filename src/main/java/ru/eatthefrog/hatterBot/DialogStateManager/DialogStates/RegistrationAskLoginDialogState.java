package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;

@Component
public class RegistrationAskLoginDialogState extends DialogState {

    @Autowired
    RegistrationAskPasswordDialogState registrationAskPasswordDialogState;

    public DialogState getNextState(String userInput, DialogStatePosition dialogStatePosition)  {
        if (loginValidChecker.checkIfLoginIsFree(userInput)) {
            //Сразу запоминаем свободный логин, чтобы никто не занял.
            //Позже запихнём в БД.
            loginValidChecker.addLoginToCache(userInput);
            dialogStatePosition.loginInstance.setLogin(userInput);
            return registrationAskPasswordDialogState;
        }
        return this;
    }

    @Override
    public void fillStateMap() {
    }

    @Override
    public String getOutPrompt(DialogStatePosition dialogStatePosition) {
        return dialogStatePosition.previousDialogState instanceof RegistrationAskLoginDialogState
                ? "This login is taken. Try another."
                : null;
    }

    @Override
    public String[] getResponse(String userInput, DialogStatePosition dialogStatePosition) {
        return new String[]{getInPrompt(dialogStatePosition)};
    }
}