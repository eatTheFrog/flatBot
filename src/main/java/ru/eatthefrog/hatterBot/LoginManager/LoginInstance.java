package ru.eatthefrog.hatterBot.LoginManager;

public interface LoginInstance {
    public void setLogin(String login);
    public void setPassword(String password);
    public Boolean getIsValid();
    public Boolean isItTimeToVerify();
    public String getPasswordHash();
    public void setNotValid();
    public void setValid();
    public String getLogin();
    public void makeUltraOld();
}
