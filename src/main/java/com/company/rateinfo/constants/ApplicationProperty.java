package com.company.rateinfo.constants;

import lombok.Getter;

@Getter
public enum ApplicationProperty {

    EXCHANGE_RATES("https://cbu.uz/uz/arkhiv-kursov-valyut/json/");

    private String value;

    ApplicationProperty(String value) {
        this.value = value;
    }
}
