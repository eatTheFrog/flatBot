package ru.eatthefrog.hatterBot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BotTokenProvider {
    @Value("${bot.tokenValue}")
    String token;


    String getToken() {
        System.out.println(token);
        return token;
    }
}
