package ru.eatthefrog.hatterBot.Tools;

public class HelpTool implements Toolable {
    @Override
    public String getExecuteOut(String args[]) {
        return "In future I'll be able to ping and nmap for you, but right now";
    }
}
