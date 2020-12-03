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
public class StartingStateTests {
    @Autowired
    StartingState startingState;

    @Test
    public void testMenuWhenUnlogged(){
        DialogState state = startingState.getMainMenu(Utils.getUnloggedDSP());
        Assert.assertTrue(state instanceof UnloggedMainMenu);
    }

    @Test
    public void testMenuWhenLogged(){
        DialogState state = startingState.getMainMenu(Utils.getLoggedDSP());
        Assert.assertTrue(state instanceof LoggedMainMenu);
    }

    @Test
    public void testNextIsMenu(){
        DialogState state = startingState.getNextState("anything, really", Utils.getLoggedDSP());
        Assert.assertTrue(state instanceof MainMenuDialogState);
    }
}
