package com.company.rateinfo.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.List;

public interface CreateButtonService {
    ReplyKeyboardMarkup createMarkupButtons(List<String> buttons);

    ReplyKeyboardMarkup makeReplyMarkup();

    InlineKeyboardMarkup createInlineKeyboardButton(List<String> buttons, int column);
}
