package com.company.rateinfo.service.impl;

import com.company.rateinfo.constants.Languages;
import com.company.rateinfo.constants.Steps;
import com.company.rateinfo.domain.UserEntity;
import com.company.rateinfo.repository.UserRepository;
import com.company.rateinfo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    @Override
    public UserEntity register(Update update, Steps step) {
        UserEntity savedUser = UserEntity.builder()
                .chatId(update.getMessage().getChatId())
                .firstName(update.getMessage().getFrom().getFirstName())
                .lastName(update.getMessage().getFrom().getLastName())
                .username(update.getMessage().getFrom().getUserName())
                .step(step)
                .build();
        return userRepository.save(savedUser);
    }


    @Override
    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public Boolean existByChatId(Long chatId) {
        return userRepository.existsByChatId(chatId);
    }

    @Override
    public UserEntity findByChatId(Long chatId) {
        return userRepository.findByChatId(chatId);
    }

    @Override
    public UserEntity existStepByChatId(Long chaId) {
        UserEntity user = userRepository.findByChatId(chaId);
        if (user.getStep().equals(Steps.CHOOSE_LANGUAGE)) {
            user.setStep(Steps.CHOOSE_SERVICE);
        }
        return user;

    }

    @Override
    public void updateStepByChatId(Long chatId, Steps step) {
        userRepository.updateStepByChatId(chatId, step);
    }

    @Override
    public void updateLangByChatId(Long chatId, Languages lang) {
        userRepository.updateLangByChatId(chatId,lang);
    }
}
