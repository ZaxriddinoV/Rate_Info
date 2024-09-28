package com.company.rateinfo.dto;

import com.company.rateinfo.domain.RateEntity;
import com.company.rateinfo.domain.UserEntity;
import com.company.rateinfo.util.DateUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class RateDTO implements Serializable {

    private Integer id;
    @JsonProperty("Code")
    private String code;
    @JsonProperty("Ccy")
    private String ccy;
    @JsonProperty("CcyNm_RU")
    private String ccyNm_RU;
    @JsonProperty("CcyNm_UZ")
    private String ccyNm_UZ;
    @JsonProperty("CcyNm_UZC")
    private String ccyNm_UZC;
    @JsonProperty("CcyNm_EN")
    private String ccyNm_EN;
    @JsonProperty("Nominal")
    private String nominal;
    @JsonProperty("Rate")
    private String rate;
    @JsonProperty("Diff")
    private String diff;
    @JsonProperty("Date")
    private String date;

    public RateEntity map2Entity() {
        return RateEntity.builder()
                .nameUz(this.getCcyNm_UZ())
                .nameRu(this.getCcyNm_RU())
                .nameEn(this.getCcyNm_EN())
                .rate(this.getRate())
                .different(this.getDiff())
                .apiId(Long.valueOf(this.getId()))
                .code(this.getCode())
                .symbol(this.getCcy())
                .date(DateUtils.convertDate(this.getDate(), "dd.MM.yyyy"))
                .build();
    }
}
