package com.company.rateinfo.service;

import com.company.rateinfo.constants.Languages;
import com.company.rateinfo.constants.Steps;
import com.company.rateinfo.domain.UserEntity;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface UserService {

    UserEntity register(Update update, Steps step);
    UserEntity save(UserEntity user);
    Boolean existByChatId(Long chatId);

    UserEntity findByChatId(Long chatId);

    UserEntity existStepByChatId(Long chaId);
    void updateStepByChatId(Long chatId, Steps step);

    void updateLangByChatId(Long chatId, Languages lang);

}
