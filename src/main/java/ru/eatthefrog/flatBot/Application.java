package ru.eatthefrog.flatBot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ru.eatthefrog.flatBot.databasefiller.DataBaseFiller;
import ru.eatthefrog.flatBot.flatrequesthandling.Flat;
import ru.eatthefrog.flatBot.flatrequesthandling.FlatBatch;
import ru.eatthefrog.flatBot.flatrequesthandling.FlatRequestHandler;
import ru.eatthefrog.flatBot.telegramapi.LongPollResponse;
import ru.eatthefrog.flatBot.telegramapi.TelegramApiProvider;
import ru.eatthefrog.flatBot.telegramapi.TelegramLongPoller;
import ru.eatthefrog.flatBot.telegramapi.TokenProvider;

import java.io.IOException;

@Component
public class Application {
    int updateFrequencySec = 100;

    @Autowired
    DataBaseFiller dataBaseFiller;

    @Autowired
    FlatRequestHandler flatRequestHandler;

    @Autowired
    TelegramApiProvider telegramApiProvider;

    @Autowired
    TelegramLongPoller telegramLongPoller;

    @Autowired
    LongPollResponseHandler longPollResponseHandler;

    @Autowired
    TokenProvider tokenProvider;
    public void run() {
        telegramApiProvider.setToken(
                tokenProvider.getToken()
        );
        //Добавляет в telegramApiProvider токен для бота.

        while(true) {
            if (dataBaseFiller.getSecondsSinceUpdate() > updateFrequencySec) {
                dataBaseFiller.updateFlatDataBase();
                //Обновляет базу данных, если она не обновлялась
                // updateFrequencySec секунд
            }
            LongPollResponse longPollResponse = telegramLongPoller.getForLongPollMessage();
            //Делает longpoll запрос
            FlatRequest[] flatRequests = longPollResponseHandler.handleResponse(
                    longPollResponse
            );
            // После этого обрабатывает запрос в специальном обработчике
            // который формирует из всех обновлений клиентский заявки
            for (FlatRequest flatRequest:
                 flatRequests) {
                // Проходится по всем заявкам, заполняет их внутреннее состояние.
                // Затем, передаёт каждую из заявок обработчику, который возвращает
                // удовлетворяющий запросу набор квартир.
                flatRequest.parseContentBuildState();
                FlatBatch flats = flatRequestHandler.handleFlatRequest(flatRequest);
                telegramApiProvider.sendMessage(
                        flatRequest.chatId,
                        flats.getMessageInterpretation()
                );
            }


        }
    }
}
