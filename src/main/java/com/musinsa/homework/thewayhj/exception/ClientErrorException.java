package com.musinsa.homework.thewayhj.exception;

import org.springframework.http.HttpStatus;

public class ClientErrorException extends RuntimeException {
    public static int code;
    public static String message;

    public ClientErrorException(HttpStatus httpStatus){
        code = httpStatus.value();
        message = httpStatus.getReasonPhrase();
    }

}
