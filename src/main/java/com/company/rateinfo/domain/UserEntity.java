package com.company.rateinfo.domain;

import com.company.rateinfo.constants.Constants;
import com.company.rateinfo.constants.Languages;
import com.company.rateinfo.constants.Steps;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "users", indexes = {@Index(columnList = "chat_id")})
@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username")
    private String username;

    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "lang")
    private Languages lang;

    @Enumerated(EnumType.STRING)
    @Column(name = "step")
    private Steps step;


}
