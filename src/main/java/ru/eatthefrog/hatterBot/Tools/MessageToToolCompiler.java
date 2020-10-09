package ru.eatthefrog.hatterBot.Tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageToToolCompiler {
    @Autowired
    AuthorsTool authorsTool;

    public Toolable getTool(String messageText) {
        String[] messageArgs = messageText.split(" ");
        switch (messageArgs[0]) {
            default:
                return authorsTool;
        }
    }
}
