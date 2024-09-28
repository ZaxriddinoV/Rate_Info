package com.company.rateinfo.controller;

import com.company.rateinfo.constants.*;
import com.company.rateinfo.config.BotConfig;
import com.company.rateinfo.domain.UserEntity;
import com.company.rateinfo.service.CreateButtonService;
import com.company.rateinfo.service.SendMessageService;
import com.company.rateinfo.service.UserService;
import com.company.rateinfo.service.impl.SendMessageServiceImpl;
import com.company.rateinfo.util.BotUtils;
import com.company.rateinfo.util.CalculatorUtils;
import com.company.rateinfo.util.RequestUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

@Service
@RequiredArgsConstructor
public class MainController extends TelegramLongPollingBot {
    @Lazy
    private final SendMessageService send = new SendMessageServiceImpl(this);
    private final BotConfig config;
    private final CreateButtonService buttonService;
    private final UserService userService;
    private final RequestUtils requestUtils;
    private final CalculatorUtils calculatorUtils;

    @Override
    public String getBotUsername() {
        return config.getUsername();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();
            UserEntity byChatId = userService.findByChatId(chatId);
            switch (message) {

                case "/start":
                    if (!userService.existByChatId(chatId)) {
                        userService.register(update, Steps.CHOOSE_LANGUAGE);
                    }
                    //ReplyKeyboardMarkup replyKeyboardMarkup = botUtils.createMainMenu();
                    InlineKeyboardMarkup inlineKeyboardButton = buttonService.createInlineKeyboardButton(ButtonNames.CHOOSE_LANGUAGE.getButtonUz(), 3);
                    send.executeButtons(null, inlineKeyboardButton, Constants.CHOOSE_LANGUAGE.getTitleUz(), chatId);
                    break;
                case "/help":
                    send.execute(chatId,"Ushbu bot yordamida siz.\n" +
                            "<b>USD</b> va <b>UZS</b> valyutalarini tez va oson konvertatsiya qilishingiz mumkin.\n" +
                            "Shuningdek, bot sizga bugungi kundagi eng so'nggi valyuta kurslarini ko'rsatadi.\n" +
                            "Valyuta kurslaridan xabardor bo'ling va hisob-kitoblaringizni osonlashtiring!");
                    break;
                default:
                    if (byChatId == null || !(byChatId.getStep().equals(Steps.USD_UZS) || byChatId.getStep().equals(Steps.UZS_USD))) {
                        if (!userService.existByChatId(chatId)) {
                            userService.register(update, Steps.CHOOSE_LANGUAGE);
                        }
                        InlineKeyboardMarkup inlineKeyboardButton1 = buttonService.createInlineKeyboardButton(null, 3);
                        send.executeButtons(null, inlineKeyboardButton1, "❗\uFE0F❗\uFE0F❗\uFE0F \n\n ", chatId);
                        return;
                    }

            }
            if (byChatId.getStep().equals(Steps.USD_UZS) || byChatId.getStep().equals(Steps.UZS_USD) && byChatId != null) {
                String messages = update.getMessage().getText();
                switch (byChatId.getStep()) {
                    case UZS_USD:
                        userService.updateStepByChatId(chatId, Steps.UZS_USD);
                        InlineKeyboardMarkup inlineKeyboardButton = buttonService.createInlineKeyboardButton(ButtonNames.CHOOSE_LANGUALES.Lang(byChatId.getLang()), 1);
                        send.executeButtons(null, inlineKeyboardButton, calculatorUtils.convertUZS(messages), chatId);
                        break;
                    case USD_UZS:
                        userService.updateStepByChatId(chatId, Steps.USD_UZS);
                        InlineKeyboardMarkup inlineKeyboardButton1 = buttonService.createInlineKeyboardButton(ButtonNames.CHOOSE_LANGUALES.Lang(byChatId.getLang()), 1);
                        send.executeButtons(null, inlineKeyboardButton1, calculatorUtils.convertUSD(messages), chatId);
                        break;
                }
                return;
            }


        } else if (update.hasCallbackQuery()) {
            String callbackData = update.getCallbackQuery().getData();
            long chatId = update.getCallbackQuery().getMessage().getChatId();

            if (callbackData.equals("\uD83C\uDDFA\uD83C\uDDFF")) { // UZ
                userService.updateStepByChatId(chatId, Steps.CHOOSE_SERVICE);
                userService.updateLangByChatId(chatId, Languages.UZ);
                InlineKeyboardMarkup inlineKeyboardButton = buttonService.createInlineKeyboardButton(ButtonNames.CHOOSE_SERVICE.getButtonUz(), 2);
                send.executeButtons(null, inlineKeyboardButton, Constants.CHOOSE_SERVICE.getTitleUz(), chatId);
                return;
            } else if (callbackData.equals("\uD83C\uDDF7\uD83C\uDDFA")) { // RU
                userService.updateStepByChatId(chatId, Steps.CHOOSE_SERVICE);
                userService.updateLangByChatId(chatId, Languages.RU);
                InlineKeyboardMarkup inlineKeyboardButton = buttonService.createInlineKeyboardButton(ButtonNames.CHOOSE_SERVICE.getButtonRu(), 2);
                send.executeButtons(null, inlineKeyboardButton, Constants.CHOOSE_SERVICE.getTitleRu(), chatId);
                return;
            } else if (callbackData.equals("\uD83C\uDDEC\uD83C\uDDE7")) { // EN
                userService.updateStepByChatId(chatId, Steps.CHOOSE_SERVICE);
                userService.updateLangByChatId(chatId, Languages.EN);
                InlineKeyboardMarkup inlineKeyboardButton = buttonService.createInlineKeyboardButton(ButtonNames.CHOOSE_SERVICE.getButtonEn(), 2);
                send.executeButtons(null, inlineKeyboardButton, Constants.CHOOSE_SERVICE.getTitleEn(), chatId);
                return;
            }
            if (callbackData.equals("Back  ↩\uFE0F") || callbackData.equals("Назад  ↩\uFE0F") || callbackData.equals("Ortga ↩\uFE0F") || callbackData.equals("↩\uFE0F")) {
                UserEntity user = userService.findByChatId(chatId);
                UserEntity byChatId = userService.findByChatId(chatId);
                switch (user.getStep()) {
                    case CHOOSE_SERVICE:
                        userService.updateStepByChatId(chatId, Steps.CHOOSE_LANGUAGE);
                        InlineKeyboardMarkup inlineKeyboardButton = buttonService.createInlineKeyboardButton(ButtonNames.CHOOSE_LANGUAGE.getButtonUz(), 3);
                        send.executeButtons(null, inlineKeyboardButton, Constants.CHOOSE_LANGUAGE.Lang(byChatId.getLang()), chatId);
                        break;
                    case CHOOSE_RATES:
                        userService.updateStepByChatId(chatId, Steps.CHOOSE_SERVICE);
                        InlineKeyboardMarkup inlineKeyboardButton1 = buttonService.createInlineKeyboardButton(ButtonNames.CHOOSE_SERVICE.Lang(byChatId.getLang()), 2);
                        send.executeButtons(null, inlineKeyboardButton1, Constants.CHOOSE_SERVICE.Lang(byChatId.getLang()), chatId);
                        break;
                    case CHOOSE_CONVERTS:
                        userService.updateStepByChatId(chatId, Steps.CHOOSE_SERVICE);
                        InlineKeyboardMarkup inlineKeyboardButton2 = buttonService.createInlineKeyboardButton(ButtonNames.CHOOSE_LANGUAGE.getButtonUz(), 3);
                        send.executeButtons(null, inlineKeyboardButton2, Constants.CHOOSE_LANGUAGE.getTitleUz(), chatId);
                        break;
                    case UZS_USD,USD_UZS:
                        userService.updateStepByChatId(chatId, Steps.CHOOSE_RATES);
                        InlineKeyboardMarkup inlineKeyboardButton3 = buttonService.createInlineKeyboardButton(ButtonNames.COOSE_CONVERTS.getButtonUz(), 1);
                        send.executeButtons(null, inlineKeyboardButton3, Converts.CHOOSE_LANG.Lang(byChatId.getLang()), chatId);
                        break;
                }
                return;
            }

            if (callbackData.equals("Valyuta Kursalari") || callbackData.equals("Курс валют") || callbackData.equals("Exchange Rates")) {
                UserEntity byChatId = userService.findByChatId(chatId);
                userService.updateStepByChatId(chatId, Steps.CHOOSE_RATES);
                InlineKeyboardMarkup inlineKeyboardButton = buttonService.createInlineKeyboardButton(ButtonNames.CHOOSE_LANGUALES.Lang(byChatId.getLang()), 1);
                send.executeButtons(null, inlineKeyboardButton, requestUtils.answer(byChatId.getLang()), chatId);
                return;
            } else if (callbackData.equals("Kalkulyator") || callbackData.equals("Калькулятор") || callbackData.equals("Calculator")) {
                UserEntity byChatId = userService.findByChatId(chatId);
                userService.updateStepByChatId(chatId, Steps.CHOOSE_CONVERTS);
                InlineKeyboardMarkup inlineKeyboardButton = buttonService.createInlineKeyboardButton(ButtonNames.COOSE_CONVERTS.getButtonUz(), 1);
                send.executeButtons(null, inlineKeyboardButton, Converts.CHOOSE_LANG.Lang(byChatId.getLang()), chatId);
                return;
            }
            if (callbackData.equals("UZS ➡\uFE0F USD") || callbackData.equals("USD ➡\uFE0F UZS")) {
                UserEntity byChatId = userService.findByChatId(chatId);
                UserEntity user = userService.findByChatId(chatId);
                switch (callbackData) {
                    case "UZS ➡\uFE0F USD":
                        userService.updateStepByChatId(chatId, Steps.UZS_USD);
                        send.execute(chatId, Converts.CHOOSE_CONVERTLANG.Lang(byChatId.getLang()));
                        break;
                    case "USD ➡\uFE0F UZS":
                        userService.updateStepByChatId(chatId, Steps.USD_UZS);
                        send.execute(chatId, Converts.CHOOSE_CONVERTLANG.Lang(byChatId.getLang()));
                        break;
                }
            }

        }
    }
}
