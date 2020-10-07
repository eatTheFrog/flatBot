package ru.eatthefrog.hatterBot.flatrequesthandling;

import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.FlatRequest;

@Component
public class FlatRequestHandler {
    public FlatBatch handleFlatRequest(FlatRequest flatRequest) {
        return new FlatBatch();
    }

}
