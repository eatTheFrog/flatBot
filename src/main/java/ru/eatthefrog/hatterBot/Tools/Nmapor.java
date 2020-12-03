package ru.eatthefrog.hatterBot.Tools;

import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;
import ru.eatthefrog.hatterBot.ExternalApiProvider.ApiProvider;
import ru.eatthefrog.hatterBot.Tools.Validator.InputValidator;

@Component
public class Nmapor extends RootTool {
    public static void nmapDefault(DialogStatePosition dsp, ApiProvider api, String address){
        executeCarefully(dsp, api, new String[]{"nmap", address}, address, InputValidator::checkNmapDefaultInput);
    }

    public static void nmapPort(DialogStatePosition dsp, ApiProvider api, String input){
        String[] components = input.split(" ");
        executeCarefully(dsp, api, new String[]{"nmap", "-p", components[1], components[0]}, input, InputValidator::checkNmapPort);
    }

    public static void nmapAll(DialogStatePosition dsp, ApiProvider api, String address){
        executeCarefully(dsp, api, new String[]{"nmap", "-p-", address}, address, InputValidator::checkNmapAll);
    }
}
