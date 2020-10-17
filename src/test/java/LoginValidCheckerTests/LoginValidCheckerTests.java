package LoginValidCheckerTests;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.eatthefrog.hatterBot.LoginManager.LoginInstance;
import ru.eatthefrog.hatterBot.LoginManager.LoginInstanceFactory;
import ru.eatthefrog.hatterBot.LoginManager.LoginInstanceImpl;
import ru.eatthefrog.hatterBot.LoginManager.LoginValidChecker;
import ru.eatthefrog.hatterBot.MongoDBOperator.DataBaseLoginManager;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = LoginValidCheckerTestsConfiguration.class)
public class LoginValidCheckerTests {
    @Autowired
    LoginValidChecker loginValidChecker;

    @Autowired
    DataBaseLoginManager dataBaseLoginManager;

    @Before
    public void resetDatabase() {
        dataBaseLoginManager.resetDatabase();
    }

    @Test
    public void putGetLoginValidChechekTest() {
        // Суть теста состоит в том, что loginInstance имеет поле isValid,
        // на основании которого сервис принимает решение, верный логин или нет.
        // Обращение к базе данных происходит лишь в двух случаях:
        // 1) В момент залогинивания или регистрации.
        // 2) В момент устаревания валидного loginInstance.
        LoginInstance loginInstance1 = TestLoginInstanceFactory.getLoginInstance("abc","de");
        loginValidChecker.rememberLoginInDB(loginInstance1);
        LoginInstance loginInstance2 = TestLoginInstanceFactory.getLoginInstance("abc","de");
        assertThat(loginValidChecker.checkValidLogin(loginInstance1), equalTo(true));
        assertThat(loginValidChecker.checkValidLogin(loginInstance2), equalTo(false));
        System.out.println(dataBaseLoginManager.getCount());

    }
    @Test
    public void checkLoginInstanceInDB() {
        LoginInstance loginInstance1 = TestLoginInstanceFactory.getLoginInstance("abc","de");
        LoginInstance loginInstance2 = TestLoginInstanceFactory.getLoginInstance("abc","de");
        loginValidChecker.rememberLoginInDB(loginInstance1);
        assertThat(loginValidChecker.checkValidLogin(loginInstance1), equalTo(true));
        assertThat(loginValidChecker.checkValidLogin(loginInstance2), equalTo(false));

        Boolean a = loginValidChecker.checkValidLoginInMongoAndUpdateVerification(loginInstance1);
        Boolean b = loginValidChecker.checkValidLoginInMongoAndUpdateVerification(loginInstance2);
        assertThat(a, equalTo(true));
        assertThat(b, equalTo(true));
    }

    @Test
    public void notValidWithoutDB() {
        LoginInstance loginInstance1 = TestLoginInstanceFactory.getLoginInstance("abc","de");
        loginValidChecker.rememberLoginInDB(loginInstance1);
        loginInstance1.setNotValid();
        assertThat(loginValidChecker.checkValidLogin(loginInstance1), equalTo(false));
    }

    @Test
    public void oldLoginInstanceNotInDB() {
        // Суть теста состоит в том, что мы создаём
        // loginInstance, кладём его в БД. После чего от устаревает,
        // в то время база данных очистилась, мы верифицируем его.
        // Он оказывается неверным.
        LoginInstance loginInstance1 = TestLoginInstanceFactory.getLoginInstance("abc","de");
        loginValidChecker.rememberLoginInDB(loginInstance1);
        assertThat(loginValidChecker.checkValidLogin(loginInstance1), equalTo(true));
        loginInstance1.makeUltraOld();
        dataBaseLoginManager.resetDatabase();
        assertThat(loginValidChecker.checkValidLogin(loginInstance1), equalTo(false));
    }

    @Test
    public void oldLoginInstanceInDB() {
        LoginInstance loginInstance1 = TestLoginInstanceFactory.getLoginInstance("abc","de");
        loginValidChecker.rememberLoginInDB(loginInstance1);
        assertThat(loginValidChecker.checkValidLogin(loginInstance1), equalTo(true));
        loginInstance1.makeUltraOld();
        assertThat(loginValidChecker.checkValidLogin(loginInstance1), equalTo(true));
    }
}
