package ru.eatthefrog.hatterBot.MongoDBOperator;

import org.bson.Document;
import ru.eatthefrog.hatterBot.LoginManager.LoginInstance;

public interface DataBaseLoginManager {
    public void putLoginPasswordHash(String login, String hashPass);
    public String getHashPasswordForLogin(String login);
    public void resetDatabase();
    public int getCount();
}
