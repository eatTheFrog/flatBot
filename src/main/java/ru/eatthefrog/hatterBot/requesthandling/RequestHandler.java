package ru.eatthefrog.hatterBot.requesthandling;

import org.springframework.stereotype.Component;

@Component
public class RequestHandler {
    public Response handleUseRequest(UseRequest useRequest) {
        return new Response();
    }
}
