package ru.eatthefrog.hatterBot.Tools;

import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;
import ru.eatthefrog.hatterBot.ExternalApiProvider.ApiProvider;
import ru.eatthefrog.hatterBot.Message.TelegramMessage;
import ru.eatthefrog.hatterBot.Tools.Validator.Validable;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RootTool {
    protected static void respondBadInput(DialogStatePosition dsp, ApiProvider api){
        api.sendMessage(new TelegramMessage("Bad input.", dsp.chatID));
    }

    protected static void executeCarefully(DialogStatePosition dsp, ApiProvider api, String[] args, String input, Validable v){
        if (v.validate(input)) {
            execute(dsp, api, args);
        } else {
            respondBadInput(dsp, api);
        }
    }

    private static void execute(DialogStatePosition dsp, ApiProvider api, String[] command){
        String output;
        Process ping;
        ProcessBuilder pb = new ProcessBuilder(command);
        pb.redirectErrorStream(true);
        dsp.isIterating = true;
        try {
            ping = pb.start();
            BufferedReader stdout = new BufferedReader(
                    new InputStreamReader(ping.getInputStream()));
            while ((output = stdout.readLine()) != null && dsp.isIterating)
                api.sendMessage(new TelegramMessage(output, dsp.chatID));
            ping.waitFor();
            ping.destroy();
        } catch (Exception e) {}
        dsp.isIterating = false;
    }
}
