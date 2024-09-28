package com.company.rateinfo.constants;

import lombok.Getter;
import java.util.Collections;
import java.util.List;

@Getter
public enum ButtonNames {
    BACK(Collections.singletonList("Back  ↩\uFE0F"), Collections.singletonList("Назад  ↩\uFE0F"), Collections.singletonList("Ortga ↩\uFE0F")),

    CHOOSE_LANGUAGE(List.of("\uD83C\uDDFA\uD83C\uDDFF", "\uD83C\uDDF7\uD83C\uDDFA", "\uD83C\uDDEC\uD83C\uDDE7"), List.of("\uD83C\uDDFA\uD83C\uDDFF", "\uD83C\uDDF7\uD83C\uDDFA", "\uD83C\uDDEC\uD83C\uDDE7"), List.of("\uD83C\uDDEC\uD83C\uDDE7", "\uD83C\uDDF7\uD83C\uDDFA", "\uD83C\uDDEC\uD83C\uDDE7")),
    CHOOSE_SERVICE(List.of("Valyuta Kursalari","Kalkulyator","Ortga ↩\uFE0F"), List.of("Курс валют","Калькулятор","Назад  ↩\uFE0F"), List.of("Exchange Rates","Calculator","Back  ↩\uFE0F")),
    CHOOSE_LANGUALES(Collections.singletonList("Ortga ↩\uFE0F"), Collections.singletonList("Назад  ↩\uFE0F"), Collections.singletonList("Back  ↩\uFE0F")),
    COOSE_CONVERTS(List.of("UZS ➡\uFE0F USD","USD ➡\uFE0F UZS","↩\uFE0F"), List.of("Курс валют","Калькулятор","Назад  ↩\uFE0F"), List.of("Exchange Rates","Calculator","Back  ↩\uFE0F"));
    private List<String> buttonUz;
    private List<String> buttonRu;
    private List<String> buttonEn;
    ButtonNames(List<String> buttonUz, List<String> buttonRu, List<String> buttonEn) {
        this.buttonUz = buttonUz;
        this.buttonRu = buttonRu;
        this.buttonEn = buttonEn;
    }
    public List<String> Lang(Languages lang){
        switch (lang) {
            case UZ :
                return getButtonUz();
            case EN:
                return getButtonEn();
            case RU:
                return getButtonRu();
        }

        return null;
    }

}
