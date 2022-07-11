package com.musinsa.homework.thewayhj.exception;

import org.springframework.http.HttpStatus;
import javax.persistence.NoResultException;

public class BadRequestException extends NoResultException {
    public static int code;
    public static String message;

    public BadRequestException(HttpStatus httpStatus){
        code = httpStatus.value();
        message = httpStatus.getReasonPhrase();
    }

}
