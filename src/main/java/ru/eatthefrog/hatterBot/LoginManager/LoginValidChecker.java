package ru.eatthefrog.hatterBot.LoginManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DBOperator.DataBaseLoginManager;

import java.util.ArrayList;
import java.util.List;

@Component
public class LoginValidChecker {
    List<String> loginCache = new ArrayList<String>();

    @Autowired
    DataBaseLoginManager dataBaseLoginManager;

    public Boolean checkValidLogin(LoginInstance loginInstance)
    {
        if (!loginInstance.getIsValid())
            return false;
        if (loginInstance.isItTimeToVerify())
            return checkValidLoginInMongoAndUpdateVerification(loginInstance);
        return true;
    }

    public Boolean checkValidLoginInMongoAndUpdateVerification(LoginInstance loginInstance) {
        String hashPass = dataBaseLoginManager.getHashPasswordForLogin(loginInstance.getLogin());
        if (hashPass == null ||
                !hashPass.equals(
                        loginInstance.getPasswordHash()
        ))
        {
            loginInstance.setNotValid();
            return false;
        }
        loginInstance.setValid();
        return true;
    }

    public Boolean checkIfLoginIsFree(String login) {
        if (loginCache.contains(login))
            return false;
        if (dataBaseLoginManager.getHashPasswordForLogin(login) != null)
            return false;
        return true;
    }
    public void addLoginToCache(String login) {
        loginCache.add(login);
    }
    public void rememberLoginInDB(LoginInstance loginInstance) {
        dataBaseLoginManager.putLoginPasswordHash(
                loginInstance.getLogin(),
                loginInstance.getPasswordHash()
        );
        loginCache.remove(loginInstance.getLogin());
        loginInstance.setValid();
    }

}
