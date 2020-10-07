package ru.eatthefrog.hatterBot.telegramapi;

import ru.eatthefrog.hatterBot.requesthandling.Request;

public class BatchRequest {
    String newOffset;
    Request[] requests;
}
