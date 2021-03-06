package com.musinsa.homework.thewayhj.exception;

import org.springframework.http.HttpStatus;
import javax.persistence.NoResultException;

public class NotFoundException extends NoResultException {
    public static int code;
    public static String message;

    public NotFoundException(HttpStatus httpStatus){
        code = httpStatus.value();
        message = httpStatus.getReasonPhrase();
    }
}
