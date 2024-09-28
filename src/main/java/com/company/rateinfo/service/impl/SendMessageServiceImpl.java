package com.company.rateinfo.service.impl;

import com.company.rateinfo.controller.MainController;
import com.company.rateinfo.service.SendMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
@RequiredArgsConstructor
public class SendMessageServiceImpl implements SendMessageService {

    @Lazy
    private final MainController mainController;

    @Override
    public void execute(Long chatId, String text) {
        SendMessage sendMessage = new SendMessage(chatId.toString(), text);
        sendMessage.setParseMode(ParseMode.HTML);
        try {
            mainController.execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void executeButtons(ReplyKeyboardMarkup replyKeyboardMarkup, InlineKeyboardMarkup inlineKeyboardMarkup, String text, Long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);
        sendMessage.enableHtml(true);
        if (replyKeyboardMarkup != null)
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
        else if (inlineKeyboardMarkup != null)
            sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        try {
            mainController.execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteMsg(Long chatId, Integer messageId) {
        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setChatId(chatId.toString());
        deleteMessage.setMessageId(messageId);

        try {
            mainController.execute(deleteMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }
}
