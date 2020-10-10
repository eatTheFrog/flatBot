package ru.eatthefrog.hatterBot;

import org.springframework.stereotype.Component;

@Component
public class DebugPrinter {
    Boolean working = true;
    public void print(String message, Object caller) {
        if (!working)
            return;
        System.out.printf("***%s:\n%s\n%n",
                caller.getClass(),
                message
                );
    }
}
