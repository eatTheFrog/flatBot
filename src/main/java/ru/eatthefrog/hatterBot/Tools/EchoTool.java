package ru.eatthefrog.hatterBot.Tools;

import ru.eatthefrog.hatterBot.LongPollResponse.LongPollUpdateMessageChat;

import java.util.Arrays;

public class EchoTool implements Toolable {
    @Override
    public String getExecuteOut(String[] args, LongPollUpdateMessageChat personalization) {
        if (args.length == 1)
            return "(I can't echo empty messages, sorry! Try /echo [words])";
        return String.join(" ",
                Arrays.copyOfRange(args,1, args.length));
    }
}