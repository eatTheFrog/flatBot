package ru.eatthefrog.hatterBot.Tools;

import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;
import ru.eatthefrog.hatterBot.ExternalApiProvider.ApiProvider;
import ru.eatthefrog.hatterBot.Message.TelegramMessage;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class Pingomator {
    public void pingInBurst(DialogStatePosition dsp, ApiProvider api, String address){
        ping(dsp, api, new String[]{"ping", address, "-c", "10"});
    }

    public void pingSpecific(DialogStatePosition dsp, ApiProvider api, String userInput){
        String[] args = userInput.split(" ");
        if (args.length != 2){
            api.sendMessage(new TelegramMessage("Bad input!", dsp.chatID));
            return;
        }

        ping(dsp, api, new String[]{"ping", args[0], "-c", args[1]});
    }

    private void ping(DialogStatePosition dsp, ApiProvider api, String[] command){
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
