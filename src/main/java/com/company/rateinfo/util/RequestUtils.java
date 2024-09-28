package com.company.rateinfo.util;

import com.company.rateinfo.constants.Languages;
import com.company.rateinfo.domain.RateEntity;
import com.company.rateinfo.service.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestUtils {

    @Lazy
    private final RateService rateService;

    public String answer(Languages lang) {
        List<RateEntity> todayRates = rateService.getTodayRates();
        switch (lang) {
            case UZ:
                return "<b>O'zbekiston Respublikasi Markaziy banki </b> \n\n " + todayRates.get(0).getNameUz() + "  " + 1 + todayRates.get(0).getSymbol() + " = " + todayRates.get(0).getRate()
                        + "\n " + todayRates.get(1).getNameUz() + "  " + 1 + todayRates.get(1).getSymbol() + " = " + todayRates.get(1).getRate()
                        + "\n " + todayRates.get(2).getNameUz() + "  " + 1 + todayRates.get(2).getSymbol() + " = " + todayRates.get(2).getRate()
                        + "\n " + todayRates.get(3).getNameUz() + "  " + 1 + todayRates.get(3).getSymbol() + " = " + todayRates.get(3).getRate()
                        + "\n " + todayRates.get(4).getNameUz() + "  " + 1 + todayRates.get(4).getSymbol() + " = " + todayRates.get(4).getRate()
                        + "\n\n@rateinfobot";
            case RU:
                return "<b>Центральный банк Республики Узбекистан </b> \n \n " + todayRates.get(0).getNameRu() + "  " + 1 + todayRates.get(0).getSymbol() + " = " + todayRates.get(0).getRate()
                        + "\n " + todayRates.get(1).getNameRu() + "  " + 1 + todayRates.get(1).getSymbol() + " = " + todayRates.get(1).getRate()
                        + "\n " + todayRates.get(2).getNameRu() + "  " + 1 + todayRates.get(2).getSymbol() + " = " + todayRates.get(2).getRate()
                        + "\n " + todayRates.get(3).getNameRu() + "  " + 1 + todayRates.get(3).getSymbol() + " = " + todayRates.get(3).getRate()
                        + "\n " + todayRates.get(4).getNameRu() + "  " + 1 + todayRates.get(4).getSymbol() + " = " + todayRates.get(4).getRate()
                        + "\n\n@rateinfobot";
            case EN:
                return "<b>Central Bank of the Republic of Uzbekistan </b> \n \n " + todayRates.get(0).getNameEn() + "  " + 1 + todayRates.get(0).getSymbol() + " = " + todayRates.get(0).getRate()
                        + "\n " + todayRates.get(1).getNameEn() + "  " + 1 + todayRates.get(1).getSymbol() + " = " + todayRates.get(1).getRate()
                        + "\n " + todayRates.get(2).getNameEn() + "  " + 1 + todayRates.get(2).getSymbol() + " = " + todayRates.get(2).getRate()
                        + "\n " + todayRates.get(3).getNameEn() + "  " + 1 + todayRates.get(3).getSymbol() + " = " + todayRates.get(3).getRate()
                        + "\n " + todayRates.get(4).getNameEn() + "  " + 1 + todayRates.get(4).getSymbol() + " = " + todayRates.get(4).getRate()
                        + "\n\n@rateinfobot";
            default:
                return "<b>O'zbekiston Respublikasi Markaziy banki </b> \n \n " + todayRates.get(0).getNameUz() + "  " + 1 + todayRates.get(0).getSymbol() + " = " + todayRates.get(0).getRate()
                        + "\n " + todayRates.get(1).getNameUz() + "  " + 1 + todayRates.get(1).getSymbol() + " = " + todayRates.get(1).getRate()
                        + "\n " + todayRates.get(2).getNameUz() + "  " + 1 + todayRates.get(2).getSymbol() + " = " + todayRates.get(2).getRate()
                        + "\n " + todayRates.get(3).getNameUz() + "  " + 1 + todayRates.get(3).getSymbol() + " = " + todayRates.get(3).getRate()
                        + "\n " + todayRates.get(4).getNameUz() + "  " + 1 + todayRates.get(4).getSymbol() + " = " + todayRates.get(4).getRate()
                        + "\n\n@rateinfobot";
        }
    }

}
