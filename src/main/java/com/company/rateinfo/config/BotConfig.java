package com.company.rateinfo.config;

import com.company.rateinfo.controller.MainController;
import com.company.rateinfo.service.impl.SendMessageServiceImpl;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@PropertySource("application.yaml")
public class BotConfig {

    @Value("${telegram.bots.botUsername}")
    String username;

    @Value("${telegram.bots.botToken}")
    String token;
}
