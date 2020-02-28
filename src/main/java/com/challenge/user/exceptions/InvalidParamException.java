package com.challenge.user.exceptions;

import static com.challenge.user.constants.Errors.PARAM_IS_NOT_VALID;

public class InvalidParamException extends UserApiException{

    public InvalidParamException(String params, String detail){
        super (String.format(PARAM_IS_NOT_VALID.getMessage(), params, detail), PARAM_IS_NOT_VALID.getValue());
    }
}
