package com.company.rateinfo.util;

import com.company.rateinfo.domain.RateEntity;
import com.company.rateinfo.service.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@Service
@RequiredArgsConstructor
public class CalculatorUtils {
    private final RateService rateService;

    public boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public boolean isAlphabetic(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    public String convertUZS(String value) {
        List<RateEntity> todayRates = rateService.getTodayRates();
        if (isNumeric(value)) {
            BigDecimal amount = new BigDecimal(value);
            BigDecimal rate = new BigDecimal(todayRates.get(0).getRate());
            BigDecimal result = amount.divide(rate, 2, BigDecimal.ROUND_HALF_UP);
            return result + " <b>USD</b>";
        } else if (isAlphabetic(value)) {
            return "\uD83D\uDD04";
        }else {
            BigDecimal amount = new BigDecimal(value);
            BigDecimal rate = new BigDecimal(todayRates.get(0).getRate());
            BigDecimal result = amount.divide(rate, 2, BigDecimal.ROUND_HALF_UP);
            return result + " <b>USD</b>";
        }
    }

    public String convertUSD(String value) {
            List<RateEntity> todayRates = rateService.getTodayRates();
            if (isNumeric(value)) {
                BigDecimal amount = new BigDecimal(value);
                BigDecimal rate = new BigDecimal(todayRates.get(0).getRate());
                BigDecimal result = amount.multiply(rate).setScale(2, BigDecimal.ROUND_HALF_UP);
                return result + " <b>UZS</b>";
            } else if (isAlphabetic(value)) {
                return "\uD83D\uDD04";
            }else {
                BigDecimal amount = new BigDecimal(value);
                BigDecimal rate = new BigDecimal(todayRates.get(0).getRate());
                BigDecimal result = amount.multiply(rate).setScale(2, BigDecimal.ROUND_HALF_UP);
                return result + " <b>UZS</b>";
            }
        }
    }



