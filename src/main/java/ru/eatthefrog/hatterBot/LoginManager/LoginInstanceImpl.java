package ru.eatthefrog.hatterBot.LoginManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.MD5StringHasher.MD5StringHasher;

public class LoginInstanceImpl implements LoginInstance {
    @Autowired
    MD5StringHasher md5StringHasher;

    public String login;
    public String password;
    public long lastTimeVerified;
    public Boolean isValid = false;
    public int verificationFrequency = 3600000;
    public LoginInstanceImpl(){};
    public LoginInstanceImpl(String loginTemp, String passwordTemp) {
        login = loginTemp;
        password = passwordTemp;
    }
    public void setValid() {
        isValid = true;
        lastTimeVerified = System.currentTimeMillis();
    }

    @Override
    public String getLogin() {
        return this.login;
    }

    public void makeUltraOld() {
        lastTimeVerified = 0;
    }
    public void setNotValid() {
        isValid = false;
        lastTimeVerified = 0;
    }
    public Boolean isItTimeToVerify() {
        return System.currentTimeMillis() - lastTimeVerified > verificationFrequency;
    }

    @Override
    public String getPasswordHash() {
        System.out.println(md5StringHasher);
        String ab = md5StringHasher.getHash(this.password);
        System.out.println(ab);
        return ab;
    }

    @Override
    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Boolean getIsValid() {
        return isValid;
    }
}
