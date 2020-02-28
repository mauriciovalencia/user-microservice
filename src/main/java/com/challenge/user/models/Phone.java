package com.challenge.user.models;

import com.challenge.user.database.entitites.PhoneEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonNaming
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Phone {

    private Long id;
    private UUID userId;
    private String citycode;
    private String countrycode;
    private String number;
    private Timestamps timestamps;

    public static Phone entityToPhone(PhoneEntity pe){
        Phone p = new Phone();
        p.setId(pe.getId());
        p.setUserId(pe.getUserId());
        p.setCitycode(pe.getCitycode());
        p.setCountrycode(pe.getContrycode());
        p.setNumber(pe.getNumber());
        Timestamps ts = new Timestamps();
        ts.setCreatedAt(pe.getCreatedAt());
        ts.setUpdatedAt(pe.getUpdatedAt());
        p.setTimestamps(ts);
        return p;
    }

}
