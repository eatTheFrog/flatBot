package ru.eatthefrog.hatterBot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.databasefiller.DataBaseFiller;
import ru.eatthefrog.hatterBot.flatrequesthandling.FlatBatch;
import ru.eatthefrog.hatterBot.flatrequesthandling.FlatRequestHandler;
import ru.eatthefrog.hatterBot.telegramapi.LongPollResponse;
import ru.eatthefrog.hatterBot.telegramapi.TelegramApiProvider;
import ru.eatthefrog.hatterBot.telegramapi.TelegramLongPoller;
import ru.eatthefrog.hatterBot.telegramapi.TokenProvider;

@Component()
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
