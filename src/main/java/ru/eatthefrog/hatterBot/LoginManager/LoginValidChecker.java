package ru.eatthefrog.hatterBot.LoginManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.MD5StringHasher.MD5StringHasher;
import ru.eatthefrog.hatterBot.MongoDBOperator.DataBaseLoginManager;

import java.util.ArrayList;
import java.util.List;

@Component
public class LoginValidChecker {
    List<String> loginCache = new ArrayList<String>();
    @Autowired
    MD5StringHasher md5StringHasher;

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
        System.out.println("HELLO WORLD");
        String hashPass = dataBaseLoginManager.getHashPasswordForLogin(loginInstance.login);

        if (hashPass == null ||
                !hashPass.equals(
                md5StringHasher.getHash(loginInstance.password)
        ))
        {
            loginInstance.setNotValid();
            return false;
        }
        loginInstance.setIsValid();
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
                loginInstance.login,
                md5StringHasher.getHash(loginInstance.password)
        );
        loginCache.remove(loginInstance.login);
        loginInstance.setIsValid();
    }
}
