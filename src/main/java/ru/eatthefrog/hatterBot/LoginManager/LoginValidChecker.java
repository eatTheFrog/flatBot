package ru.eatthefrog.hatterBot.LoginManager;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.MongoDBOperator.MongoLoginManager;

import java.util.ArrayList;
import java.util.List;

@Component
public class LoginValidChecker {
    List<String> loginCache = new ArrayList<String>();
    @Autowired
    MongoLoginManager mongoLoginManager;
    public Boolean checkValidLogin(LoginInstance loginInstance)
    {
        Document loginInstanceDocument = mongoLoginManager.getLoginInstanceDocument(loginInstance.login);
        if (loginInstanceDocument == null)
            return false;
        if (!loginInstanceDocument.get("password").equals(loginInstance.password))
            return false;
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
