package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;
import ru.eatthefrog.hatterBot.LoginManager.LoginValidChecker;
import ru.eatthefrog.hatterBot.TelegramChatSTDOUT;

import javax.annotation.PostConstruct;

@Component
public class RegistrationAskLoginDialogState extends DialogState {
    @PostConstruct
    void initIdentifier() {
        dialogStateIdentifier = "registrationAskLoginDialogState";
    }
    @Autowired
    LoginValidChecker loginValidChecker;

    @Autowired
    RegistrationAskPasswordDialogState registrationAskPasswordDialogState;
    @Autowired
    TelegramChatSTDOUT telegramChatSTDOUT;


    public DialogState moveOtherState(String userInput, DialogStatePosition dialogStatePosition)  {
        if (loginValidChecker.checkIfLoginIsFree(userInput)) {
            //Сразу запоминаем свободный логин, чтобы никто не занял.
            //Позже запихнём в БД.
            loginValidChecker.addLoginToCache(userInput);
            dialogStatePosition.loginInstance.login = userInput;
            return registrationAskPasswordDialogState;
        }
        telegramChatSTDOUT.printInChat("This login is busy",
                dialogStatePosition.chatID);
        return this;




    }


}
