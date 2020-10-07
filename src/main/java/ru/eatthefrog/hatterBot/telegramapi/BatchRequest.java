package ru.eatthefrog.hatterBot.telegramapi;

import ru.eatthefrog.hatterBot.requesthandling.UseRequest;

public class BatchRequest {
    String newOffset;
    UseRequest[] useRequests;
}
