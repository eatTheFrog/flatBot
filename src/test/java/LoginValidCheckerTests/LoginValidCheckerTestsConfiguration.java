package LoginValidCheckerTests;

import TestDBOperator.TestDataBaseLoginManager;
import org.springframework.context.annotation.Bean;
import ru.eatthefrog.hatterBot.LoginManager.LoginValidChecker;
import ru.eatthefrog.hatterBot.MD5StringHasher.MD5StringHasher;
import ru.eatthefrog.hatterBot.MongoDBOperator.DataBaseLoginManager;

public class LoginValidCheckerTestsConfiguration {

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
