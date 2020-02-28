package com.challenge.user.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class UserApiException extends RuntimeException{

    @Getter @Setter private Integer code;
    @Getter private final String uuid = UUID.randomUUID().toString();

    public UserApiException(String message, Integer exceptionCode) {
        super(message);
        code = exceptionCode;
    }

    @Override
    public String getMessage() {
        if (null == super.getMessage()) {
            return toString();
        }
        return super.getMessage();
    }

    @Override
    public String toString() {
        return "UserApiException(code=" + this.getCode() + ", uuid=" +
                this.getUuid() + ")";
    }
}
