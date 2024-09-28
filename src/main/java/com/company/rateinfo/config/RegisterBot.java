package com.company.rateinfo.config;

import com.company.rateinfo.controller.MainController;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
@RequiredArgsConstructor
public class RegisterBot {

    private final MainController mainController;

    @EventListener({ContextRefreshedEvent.class})
    public void registerBot() {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(mainController);
        } catch (TelegramApiException e) {
                e.printStackTrace();
        }
    }
}
