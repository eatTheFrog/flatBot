package ru.eatthefrog.hatterBot.LoginManager;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.MD5StringHasher.MD5StringHasher;
import ru.eatthefrog.hatterBot.MongoDBOperator.MongoLoginManager;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class LoginValidChecker {
    List<String> loginCache = new ArrayList<String>();
    @Autowired
    MD5StringHasher md5StringHasher;

    @Autowired
    MongoLoginManager mongoLoginManager;

    public Boolean checkValidLogin(LoginInstance loginInstance)
    {
        if (loginInstance.isItTimeToVerify())
            return checkValidLoginInMongoAndUpdateVerification(loginInstance);
        return loginInstance.isValid;
    }

    public Boolean checkValidLoginInMongoAndUpdateVerification(LoginInstance loginInstance) {
        System.out.println("HELLO WORLD");
        Document loginInstanceDocument = mongoLoginManager.getLoginInstanceDocument(loginInstance.login);

        if (loginInstanceDocument == null ||
                !loginInstanceDocument.get("password").equals(
                md5StringHasher.getHash(loginInstance.password)
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

}
