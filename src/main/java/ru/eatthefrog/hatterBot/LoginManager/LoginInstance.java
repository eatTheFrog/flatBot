package ru.eatthefrog.hatterBot.LoginManager;

public class LoginInstance {
    public String login;
    public String password;
    public long lastTimeVerified;
    public Boolean isValid = false;
    public int verificationFrequency = 3600000;
    public void setValid() {
        isValid = true;
        lastTimeVerified = System.currentTimeMillis();
    }
    public void setNotValid() {
        isValid = false;
        lastTimeVerified = 0;
    }
    public Boolean isItTimeToVerify() {
        return System.currentTimeMillis() - lastTimeVerified > verificationFrequency;
    }
}
