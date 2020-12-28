package MongoTests;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;
import ru.eatthefrog.hatterBot.LoginManager.LoginInstance;
import ru.eatthefrog.hatterBot.LoginManager.LoginInstanceImpl;
import ru.eatthefrog.hatterBot.LoginManager.LoginValidChecker;
import ru.eatthefrog.hatterBot.MongoDBOperator.DataBaseLoginManager;
import ru.eatthefrog.hatterBot.SpringConfiguration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class LoginValidCheckerTests {

    @Rule
    public GenericContainer mongoDBContainer = new GenericContainer(DockerImageName.parse("mongo:4.4.2"))
            .withExposedPorts(27017)
            .withExtraHost("localhost", "127.0.0.1");

    @Autowired
    LoginValidChecker loginValidChecker;

    @Autowired
    DataBaseLoginManager dataBaseLoginManager;

    public static LoginInstance getLoginInstance(String login, String password) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                SpringConfiguration.class
        );
        LoginInstance loginInstance = context.getBean("loginInstanceBean", LoginInstance.class);
        context.close();
        loginInstance.setLogin(login);
        loginInstance.setPassword(password);
        return loginInstance;
    }

    @Test
    public void putGetLoginValidChechekTest() {
        // Суть теста состоит в том, что loginInstance имеет поле isValid,
        // на основании которого сервис принимает решение, верный логин или нет.
        // Обращение к базе данных происходит лишь в двух случаях:
        // 1) В момент залогинивания или регистрации.
        // 2) В момент устаревания валидного loginInstance.
        LoginInstance loginInstance1 = getLoginInstance("Vasya","Hahaha");
        loginValidChecker.rememberLoginInDB(loginInstance1);
        LoginInstance loginInstance2 = getLoginInstance("Vasya","Hahaha");
        assertThat(loginValidChecker.checkValidLogin(loginInstance1), equalTo(true));
        assertThat(loginValidChecker.checkValidLogin(loginInstance2), equalTo(false));
        System.out.println(dataBaseLoginManager.getCount());

    }

    @Test
    public void checkLoginInstanceInDB() {
        LoginInstance loginInstance1 = getLoginInstance("abc","de");
        LoginInstance loginInstance2 = getLoginInstance("abc","de");
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
        LoginInstance loginInstance1 = getLoginInstance("abc","de");
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
        LoginInstance loginInstance1 = getLoginInstance("abc","de");
        loginValidChecker.rememberLoginInDB(loginInstance1);
        assertThat(loginValidChecker.checkValidLogin(loginInstance1), equalTo(true));
        loginInstance1.makeUltraOld();
        dataBaseLoginManager.resetDatabase();
        assertThat(loginValidChecker.checkValidLogin(loginInstance1), equalTo(false));
    }

    @Test
    public void oldLoginInstanceInDB() {
        LoginInstance loginInstance1 = getLoginInstance("abc","de");
        loginValidChecker.rememberLoginInDB(loginInstance1);
        assertThat(loginValidChecker.checkValidLogin(loginInstance1), equalTo(true));
        loginInstance1.makeUltraOld();
        assertThat(loginValidChecker.checkValidLogin(loginInstance1), equalTo(true));
    }
}
