package com.company.rateinfo.repository;

import com.company.rateinfo.constants.Languages;
import com.company.rateinfo.constants.Steps;
import com.company.rateinfo.domain.UserEntity;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Boolean existsByChatId(Long chatId);
    UserEntity findByChatId(Long chatId);



    @Modifying
    @Transactional
    @Query(value = "update UserEntity u set u.step=:step where u.chatId=:chatId")
    void updateStepByChatId(@Param("chatId") Long chatId, @Param("step") Steps step);

    @Modifying
    @Transactional
    @Query(value = "update UserEntity u set u.lang=:lang where u.chatId=:chatId")
    void updateLangByChatId(@Param("chatId") Long chatId, @Param("lang")Languages lang);




}
