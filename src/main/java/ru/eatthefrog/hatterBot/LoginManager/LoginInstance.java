package ru.eatthefrog.hatterBot.LoginManager;

public class LoginInstance {
    public String login;
    public String password;
    public LoginVerifyKey loginVerifyKey;
    public LoginInstance() {
        loginVerifyKey = new LoginVerifyKey();
    }
}
