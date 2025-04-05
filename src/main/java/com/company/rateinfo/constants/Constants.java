package com.company.rateinfo.constants;

import lombok.Getter;

import java.util.List;

@Getter
public enum Constants {

    CHOOSE_LANGUAGE("\uD83C\uDDFA\uD83C\uDDFFTilni tanlang!\n\uD83C\uDDF7\uD83C\uDDFAВыберите языка!\n\uD83C\uDDEC\uD83C\uDDE7Choose language!", "\uD83C\uDDFA\uD83C\uDDFFTilni tanlang!\n\uD83C\uDDF7\uD83C\uDDFAВыберите языка!\n\uD83C\uDDEC\uD83C\uDDE7Choose language!", "\uD83C\uDDFA\uD83C\uDDFFTilni tanlang!\n\uD83C\uDDF7\uD83C\uDDFAВыберите языка!\n\uD83C\uDDEC\uD83C\uDDE7Choose language!"),

    CHOOSE_SERVICE("Xizmatlardan birini tanlang⬇\uFE0F :", "Выберите одну из услуг⬇\uFE0F :", "Choose one of the services⬇\uFE0F :");


    private String titleUz;
    private String titleRu;
    private String titleEn;



    Constants(String titleUz, String titleRu, String titleEn) {
        this.titleUz = titleUz;
        this.titleRu = titleRu;
        this.titleEn = titleEn;

    }
    public String Lang(Languages lang){
        switch (lang) {
            case UZ :
                 return titleUz;
            case EN:
                return titleEn;
            case RU:
                return titleRu;
        }

        return null;
    }


}
