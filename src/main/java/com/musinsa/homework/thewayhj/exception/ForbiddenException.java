package com.musinsa.homework.thewayhj.exception;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends RuntimeException {
    public static int code;
    public static String message;

    public ForbiddenException(HttpStatus httpStatus){
        code = httpStatus.value();
        message = httpStatus.getReasonPhrase();
    }

}
