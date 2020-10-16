package ru.eatthefrog.hatterBot.Tools;

import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;

import java.util.Arrays;

public class EchoTool{
    public String getExecuteOut(String[] args, DialogStatePosition dialogStatePosition) {
        if (args.length == 1)
            return "(I can't echo empty messages, sorry! Try /echo [words])";
        return String.join(" ",
                Arrays.copyOfRange(args,1, args.length));
    }
}