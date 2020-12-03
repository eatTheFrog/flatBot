package DialogStatesTest;

import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStates.DialogState;
import ru.eatthefrog.hatterBot.LoginManager.LoginInstance;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Utils {
    public static DialogStatePosition getLoggedDSP() {
        DialogStatePosition mockedDSP = mock(DialogStatePosition.class);
        LoginInstance mockedLI = mock(LoginInstance.class);
        when(mockedLI.getIsValid()).thenReturn(true);
        when(mockedLI.isItTimeToVerify()).thenReturn(false);
        mockedDSP.loginInstance = mockedLI;

        return mockedDSP;
    }

    public static DialogStatePosition getUnloggedDSP(){
        DialogStatePosition mockedDSP = mock(DialogStatePosition.class);
        LoginInstance mockedLI = mock(LoginInstance.class);
        when(mockedLI.getIsValid()).thenReturn(false);
        mockedDSP.loginInstance = mockedLI;

        return mockedDSP;
    }

    public static boolean isNextState(DialogState currentState, String command,
                                      Class desiredState, DialogStatePosition dsp){
        DialogState state = currentState.getNextState(command, dsp);
        return desiredState.isInstance(state);
    }
}

