package com.challenge.user.models.responses;

import com.challenge.user.models.Timestamps;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonNaming
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    @JsonProperty
    private Long id;
    @JsonProperty
    private String name;
    @JsonProperty("last_login")
    private String lastLogin;
    @JsonProperty
    private String token;
    @JsonProperty("is_active")
    private Boolean isActive;
    @JsonProperty
    private Timestamps timestamps;

}
