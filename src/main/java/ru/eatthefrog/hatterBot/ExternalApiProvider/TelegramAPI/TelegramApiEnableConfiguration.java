package ru.eatthefrog.hatterBot.ExternalApiProvider.TelegramAPI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.eatthefrog.hatterBot.Message.Message;
import ru.eatthefrog.hatterBot.Message.TelegramMessage;

@Configuration
public class TelegramApiEnableConfiguration {

    @Bean
    TelegramAPIProvider telegramAPIProviderBean() {
        return new TelegramAPIProvider();
    }

    @Bean
    TelegramBotTokenProvider telegramBotTokenProvider() {
        return new TelegramBotTokenProvider();
    }

    @Bean
    TelegramLongPollMessageGetter telegramLongPollMessageGetter() {
        return new TelegramLongPollMessageGetter();
    }

    @Bean
    @Scope("prototype")
    Message messageBean() {
        return new TelegramMessage();
    }
}
