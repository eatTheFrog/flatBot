package ru.eatthefrog.hatterBot.Tools;

import org.springframework.stereotype.Component;

@Component
public class UnknownTool {
    public String getExecuteOut() {
        return "I don't know this command.";
    }
}
