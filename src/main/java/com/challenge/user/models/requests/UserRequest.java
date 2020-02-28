package com.challenge.user.models.requests;

import com.challenge.user.models.Phone;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonNaming
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRequest {

    @NotEmpty(message = "The e-mail is mandatory")
    @JsonProperty
    private String email;
    @NotEmpty(message = "The name is mandatory")
    @JsonProperty
    private String name;
    @NotEmpty(message = "The password is mandatory")
    @JsonProperty
    private String password;
    @NotEmpty(message = "The phones is mandatory, can´t be empty json array")
    @JsonProperty
    private List<Phone> phones;

}