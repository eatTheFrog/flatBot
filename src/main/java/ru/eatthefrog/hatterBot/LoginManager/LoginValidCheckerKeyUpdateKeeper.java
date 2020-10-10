package ru.eatthefrog.hatterBot.LoginManager;

import org.springframework.stereotype.Component;

@Component
public class LoginValidCheckerKeyUpdateKeeper {
    long lastTimeUpdate = 0;
    public Boolean isItTimeToUpdate() {
        if (System.currentTimeMillis() - lastTimeUpdate > 3600000) {
            lastTimeUpdate = System.currentTimeMillis();
            return true;
        }
        return false;
    }
}
