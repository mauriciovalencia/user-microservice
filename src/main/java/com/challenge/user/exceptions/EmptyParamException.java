package com.challenge.user.exceptions;

import static com.challenge.user.constants.Errors.PARAM_CANT_BE_EMPTY_OR_NULL;

public class EmptyParamException extends UserApiException {

    public EmptyParamException(String params){
        super (String.format(PARAM_CANT_BE_EMPTY_OR_NULL.getMessage(), params), PARAM_CANT_BE_EMPTY_OR_NULL.getValue());
    }
}
