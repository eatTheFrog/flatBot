package ru.eatthefrog.hatterBot.Tools;

import org.springframework.stereotype.Component;

@Component
public class PraiseTool implements Toolable {
    @Override
    public String getExecuteOut(String[] args) {
        return "Praised be Bashkarov and Chasoviting!";
    }
}
