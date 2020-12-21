package DialogStatesTest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.*;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.CoreStates.EchoState;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.CoreStates.InfoState;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.CoreStates.LoggedMainMenu;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.CoreStates.LogoutState;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.SpecificStates.NotYetImplementedState;
import ru.eatthefrog.hatterBot.SpringConfiguration;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class LoggedMainMenuTests {
    @Autowired
    LoggedMainMenu loggedMainMenu;

    @Test
    public void testNetworking(){
        Assert.assertTrue(
                Utils.isNextState(loggedMainMenu,
                "/networking",
                NetworkingToolsMenuState.class,
                Utils.getLoggedDSP()));
    }

    @Test
    public void testInfo(){
        Assert.assertTrue(
                Utils.isNextState(loggedMainMenu,
                "/info",
                InfoState.class,
                Utils.getLoggedDSP()));
    }

    @Test
    public void testEcho(){
        Assert.assertTrue(
                Utils.isNextState(loggedMainMenu,
                "/echo",
                EchoState.class,
                Utils.getLoggedDSP()));
    }

    @Test
    public void testSettings(){
        Assert.assertTrue(
                Utils.isNextState(loggedMainMenu,
                "/settings",
                NotYetImplementedState.class,
                Utils.getLoggedDSP()));
    }

    @Test
    public void testLogout(){
        Assert.assertTrue(
                Utils.isNextState(loggedMainMenu,
                "/logout",
                LogoutState.class,
                Utils.getLoggedDSP()));
    }
}
