package ru.eatthefrog.hatterBot.Tools;

import ru.eatthefrog.hatterBot.DialogStateManager.DialogStatePosition;

public class HelpTool {
    public String getExecuteOut(String[] args, DialogStatePosition dialogStatePosition) {
        return "In future I'll be able to ping and nmap for you, but right now I can only /echo your words /praise my creators.";
    }
}
