package ru.eatthefrog.hatterBot.DBOperator;

public interface DataBaseLoginManager {
    public void putLoginPasswordHash(String login, String hashPass);
    public String getHashPasswordForLogin(String login);
    public void resetDatabase();
    public int getCount();
}
