package ru.eatthefrog.hatterBot.requesthandling;

import org.springframework.stereotype.Component;

@Component
public class RequestHandler {
    public Response handleUseRequest(Request request) {
        return new Response();
    }
}
