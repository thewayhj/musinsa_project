package com.musinsa.homework.thewayhj.exception;

import org.springframework.http.HttpStatus;

public class ConflictException extends RuntimeException {
    public static int code;
    public static String message;

    public ConflictException(HttpStatus httpStatus){
        code = httpStatus.value();
        message = httpStatus.getReasonPhrase();
    }

}
