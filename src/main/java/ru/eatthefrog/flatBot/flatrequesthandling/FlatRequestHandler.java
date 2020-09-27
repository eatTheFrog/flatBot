package ru.eatthefrog.flatBot.flatrequesthandling;

import org.springframework.stereotype.Component;
import ru.eatthefrog.flatBot.FlatRequest;

@Component
public class FlatRequestHandler {
    public FlatBatch handleFlatRequest(FlatRequest flatRequest) {
        return new FlatBatch();
    }

}
