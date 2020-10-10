package ru.eatthefrog.hatterBot.DialogStateManager.DialogStates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.UserDialogStatePosition;
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


    public DialogState moveOtherState(String arg, UserDialogStatePosition userDialogStatePosition)  {
        if (loginValidChecker.checkIfLoginIsFree(arg)) {
            //Сразу запоминаем свободный логин, чтобы никто не занял.
            //Позже запихнём в БД.
            loginValidChecker.addLoginToCache(arg);
            userDialogStatePosition.loginInstance.login = arg;
            return registrationAskPasswordDialogState;
        }
        telegramChatSTDOUT.printInChat("This login is busy",
                userDialogStatePosition.chatID);
        return this;




    }


}
