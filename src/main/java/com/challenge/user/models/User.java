package com.challenge.user.models;

import com.challenge.user.database.entitites.UserEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonNaming
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    private Long id;
    private String email;
    private String name;
    private String password;
    private List<Phone> phones;
    private String user;
    private String pwd;
    private String token;
    private Boolean isActive;
    private Timestamps timestamps;

    public static User entityToUser(UserEntity ue){
        User u = new User();
        u.setId(ue.getId());
        u.setEmail(ue.getEmail());
        u.setName(ue.getName());
        u.setPassword(ue.getPassword());
        u.setIsActive(ue.getIsActive());
        Timestamps ts = new Timestamps();
        ts.setCreatedAt(ue.getCreatedAt());
        ts.setUpdatedAt(ue.getUpdatedAt());
        u.setTimestamps(ts);
        return u;
    }

}