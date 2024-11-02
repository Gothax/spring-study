package com.gothaxcity.oauthjwt4gradproj.account.exception;

import com.gothaxcity.oauthjwt4gradproj.exception.CustomException;
import com.gothaxcity.oauthjwt4gradproj.exception.ErrorCode;

public class AuthException extends CustomException {

    public AuthException(ErrorCode errorCode) {
        super(errorCode);
    }
}
