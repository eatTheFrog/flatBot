package ru.eatthefrog.hatterBot.Tools;

import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;
import ru.eatthefrog.hatterBot.ExternalApiProvider.ApiProvider;
import ru.eatthefrog.hatterBot.Tools.Validator.InputValidator;

@Component
public class Pingomator extends RootTool{
    public static void pingInBurst(DialogStatePosition dsp, ApiProvider api, String address){
        executeCarefully(dsp, api, new String[]{"ping", "-c 10", address}, address, InputValidator::checkPingBurstInput);
    }

    public static void pingSpecific(DialogStatePosition dsp, ApiProvider api, String userInput){
        String[] args = userInput.split(" ");
        executeCarefully(dsp, api, new String[]{"ping", args[0], "-c", args[1]}, userInput, InputValidator::checkPingSpecificInput);
    }
}
