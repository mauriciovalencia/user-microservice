package com.challenge.user.exceptions;

import static com.challenge.user.constants.Errors.PARAM_ALREADY_EXIST;

public class ExistParamException extends UserApiException{

    public ExistParamException(String params, String where){
        super (String.format(PARAM_ALREADY_EXIST.getMessage(), params, where), PARAM_ALREADY_EXIST.getValue());
    }

}
