package ru.eatthefrog.hatterBot.Tools;

public class UnknownTool implements Toolable {
    public String getExecuteOut(String[] args) {
        return "I don't know this command. Try /help!";
    }
}
