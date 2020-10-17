package TestDBOperator;

import ru.eatthefrog.hatterBot.MongoDBOperator.DataBaseLoginManager;

import javax.annotation.PostConstruct;
import java.util.Dictionary;
import java.util.Hashtable;

public class TestDataBaseLoginManager implements DataBaseLoginManager {
    Dictionary<String, String> dictionaryLoginHashPass;
    @PostConstruct
    void initDictionary() {
        dictionaryLoginHashPass = new Hashtable<>();
    }
    @Override
    public void putLoginPasswordHash(String login, String hashPass) {
        dictionaryLoginHashPass.put(login, hashPass);
    }

    @Override
    public String getHashPasswordForLogin(String login) {
        return dictionaryLoginHashPass.get(login);
    }

    @Override
    public void resetDatabase() {
        dictionaryLoginHashPass = new Hashtable<>();
    }

    @Override
    public int getCount() {
        return dictionaryLoginHashPass.size();
    }
}
