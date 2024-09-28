package com.company.rateinfo.constants;

import lombok.Getter;

@Getter
public enum Converts {

    CHOOSE_LANG("Valyuta konvertatsiyasini tanlang.", "Выберите валютную конвертацию.", "Choose currency conversion."),UZS("convertsUz", "convertsRu", "convertsEn"),
    CHOOSE_CONVERTLANG("<b>Konvertatsiya qiymatini kiriting</b> \n\n Masalan '100000' ","<b>Введите значение конвертации.</b> Например, '100000'","<b>Enter the conversion value</b>. For example, '100000'");
    private String convertsUz;
    private String convertsRu;
    private String convertsEn;


    Converts(String convertsUz, String convertsRu, String convertsEn) {
        this.convertsUz = convertsUz;
        this.convertsRu = convertsRu;
        this.convertsEn = convertsEn;
    }

    public String Lang(Languages lang){
        switch (lang) {
            case UZ:
                return convertsUz;
            case EN:
                return convertsEn;
            case RU:
                return convertsRu;
        }
        return null;
    }
}
