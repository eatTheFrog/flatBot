package ru.eatthefrog.hatterBot.LoginManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DebugPrinter;

import java.util.Random;

@Component
@Scope("prototype")
public class LoginVerifyKey {
    @Autowired
    DebugPrinter debugPrinter;
    @Autowired
    Random random;

    public int innerKey = 0;
    public Boolean obviouslyWrong() {
        return innerKey == 0;
    }
    public void setObviouslyWrong() {
        innerKey = 0;
    }
    public void setRandomKey() {
        innerKey = random.nextInt(Integer.MAX_VALUE - 2) + 1;
        debugPrinter.print(String.valueOf(innerKey), this);
    }
    public void setSameKey(LoginVerifyKey loginVerifyKeyable) {
        innerKey = loginVerifyKeyable.innerKey;
    }

    public Boolean compareWithOtherKey(LoginVerifyKey loginVerifyKey) {
        return loginVerifyKey.innerKey == innerKey;
    }

}
