package ru.eatthefrog.hatterBot.Tools;

import org.springframework.stereotype.Component;

@Component
public class AuthorsTool implements Toolable{
    @Override
    public String getExecuteOut(String argsString) {
        return "Welcome to the eaththefrog";
    }
}
