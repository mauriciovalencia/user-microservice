package com.challenge.user.models;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ErrorMessage {
    private Integer code;
    private String message;
}