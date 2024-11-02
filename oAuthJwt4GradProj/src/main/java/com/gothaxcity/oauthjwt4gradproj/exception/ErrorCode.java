package com.gothaxcity.oauthjwt4gradproj.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // auth
    ILLEGAL_REGISTRATION_ID(BAD_REQUEST, "허용되지 않는 소셜로그인입니다.");


    // quest


    // influencer's pick


    private final HttpStatus httpStatus;
    private final String message;
}