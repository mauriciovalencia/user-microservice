package com.challenge.user.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonNaming
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Timestamps {

    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    public String getCreatedAt() { return createdAt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME); }
    public String getUpdatedAt() { return updatedAt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME); }

}