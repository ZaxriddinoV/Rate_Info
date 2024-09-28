package com.company.rateinfo.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.persistence.criteria.CriteriaBuilder;

public interface SendMessageService {
    void execute(Long chatId, String text) throws TelegramApiException;

    void executeButtons(ReplyKeyboardMarkup replyKeyboardMarkup, InlineKeyboardMarkup inlineKeyboardMarkup, String text, Long chatId);

    void deleteMsg(Long chatId, Integer messageId);

}
