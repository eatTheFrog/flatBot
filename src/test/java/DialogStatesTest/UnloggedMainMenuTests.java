package DialogStatesTest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.*;
import ru.eatthefrog.hatterBot.SpringConfiguration;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class UnloggedMainMenuTests {

    @Autowired
    UnloggedMainMenu unloggedMainMenu;

    @Test
    public void testNextLogin(){
        Assert.assertTrue(
                Utils.isNextState(unloggedMainMenu,
                        "/login",
                        LoginAskLoginDialogState.class,
                        Utils.getUnloggedDSP()));
    }

    @Test
    public void testNextRegister(){
        Assert.assertTrue(
                Utils.isNextState(unloggedMainMenu,
                        "/register",
                        RegistrationAskLoginDialogState.class,
                        Utils.getUnloggedDSP()));
    }

    @Test
    public void testNextInfo(){
        Assert.assertTrue(
                Utils.isNextState(unloggedMainMenu,
                        "/info",
                        InfoState.class,
                        Utils.getUnloggedDSP()));
    }

    @Test
    public void testNextEcho(){
        Assert.assertTrue(
                Utils.isNextState(unloggedMainMenu,
                        "/echo",
                        EchoState.class,
                        Utils.getUnloggedDSP()));
    }

    @Test
    public void testWrongCommand(){
        Assert.assertTrue(
                Utils.isNextState(unloggedMainMenu,
                        "/asfdasd",
                        UnknownDialogState.class,
                        Utils.getUnloggedDSP()));
    }
}
