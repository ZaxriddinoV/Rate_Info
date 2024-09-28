package com.company.rateinfo.domain;

import javax.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Component
@Entity
@Table(name = "rates")
@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class RateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "api_id")
    private Long apiId;

    @Column(name = "name_uz")
    private String nameUz;

    @Column(name = "name_ru")
    private String nameRu;

    @Column(name = "name_en")
    private String nameEn;

    @Column(name = "name_uzc")
    private String nameUzc;

    @Column(name = "code")
    private String code;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "rate")
    private String rate;

    @Column(name = "different")
    private String different;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "visible")
    private Boolean visible;

    @Column(name = "position")
    private Integer position;
}
