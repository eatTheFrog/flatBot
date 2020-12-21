package DialogStatesTest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.CoreStates.DialogState;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.CoreStates.LoggedMainMenu;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.PingStates.PingState;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.CoreStates.UnloggedMainMenu;
import ru.eatthefrog.hatterBot.SpringConfiguration;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class DialogStateTests {

    @Autowired
    PingState pingState;

    @Test
    public void testMenuWhenUnlogged(){
        DialogState state = pingState.getMainMenu(Utils.getUnloggedDSP());
        Assert.assertTrue(state instanceof UnloggedMainMenu);
    }

    @Test
    public void testMenuWhenLogged(){
        DialogState state = pingState.getMainMenu(Utils.getLoggedDSP());
        Assert.assertTrue(state instanceof LoggedMainMenu);
    }
}
