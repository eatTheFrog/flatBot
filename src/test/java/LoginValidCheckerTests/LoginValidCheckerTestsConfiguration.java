package LoginValidCheckerTests;

import TestDBOperator.TestDataBaseLoginManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import ru.eatthefrog.hatterBot.LoginManager.LoginInstance;
import ru.eatthefrog.hatterBot.LoginManager.LoginInstanceImpl;
import ru.eatthefrog.hatterBot.LoginManager.LoginValidChecker;
import ru.eatthefrog.hatterBot.MD5StringHasher.MD5StringHasher;
import ru.eatthefrog.hatterBot.MongoDBOperator.DataBaseLoginManager;

public class LoginValidCheckerTestsConfiguration {

    @Bean
    @Scope("prototype")
    LoginInstance loginInstanceBean() {
        return new LoginInstanceImpl();
    }
    @Bean
    LoginValidChecker loginValidCheckerBean() {
        return new LoginValidChecker();
    }
    @Bean
    MD5StringHasher md5StringHasherBean() {
        return new MD5StringHasher();
    }
    @Bean
    DataBaseLoginManager dataBaseLoginManagerBean() {
        return new TestDataBaseLoginManager();
    }
}
