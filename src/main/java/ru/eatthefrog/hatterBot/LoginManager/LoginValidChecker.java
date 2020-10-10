package ru.eatthefrog.hatterBot.LoginManager;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.MD5StringHasher.MD5StringHasher;
import ru.eatthefrog.hatterBot.MongoDBOperator.MongoLoginManager;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

@Component
public class LoginValidChecker {
    @Autowired
    LoginValidCheckerKeyUpdateKeeper loginValidCheckerKeyUpdateKeeper;
    @Autowired
    LoginVerifyKey currentLoginVerifyKey;
    List<String> loginCache = new ArrayList<String>();
    @Autowired
    MD5StringHasher md5StringHasher;

    @Autowired
    MongoLoginManager mongoLoginManager;
    public Boolean checkValidLogin(LoginInstance loginInstance)
    {
        if(loginInstance.loginVerifyKey.obviouslyWrong())
            return false;
        if (loginInstance.loginVerifyKey.compareWithOtherKey(currentLoginVerifyKey))
            return true;
        return checkValidLoginInDB(loginInstance);
    }

    public Boolean checkValidLoginInDB(LoginInstance loginInstance) {
        Document loginInstanceDocument = mongoLoginManager.getLoginInstanceDocument(loginInstance.login);
        if (loginInstanceDocument == null) {
            loginInstance.loginVerifyKey.setObviouslyWrong();
            return false;
        }
        if (!loginInstanceDocument.get("password").equals(
                md5StringHasher.getHash(loginInstance.password)
        ))
        {
            loginInstance.loginVerifyKey.setObviouslyWrong();
            return false;
        }
        loginInstance.loginVerifyKey.setSameKey(currentLoginVerifyKey);
        return true;
    }

    public Boolean checkIfLoginIsFree(String login) {
        if (loginCache.contains(login))
            return false;
        if (mongoLoginManager.getLoginInstanceDocument(login) != null)
            return false;
        return true;
    }
    public void addLoginToCache(String login) {
        loginCache.add(login);
    }
    public void rememberLoginInDB(LoginInstance loginInstance) {
        mongoLoginManager.putLoginInstance(loginInstance);
        loginCache.remove(loginInstance.login);
    }
    public void updateVerifyKeyRandom() {
        currentLoginVerifyKey.setRandomKey();
    }
    public Boolean isItTimeToUpdateVerifyKey() {
        return loginValidCheckerKeyUpdateKeeper.isItTimeToUpdate();
    }
}
