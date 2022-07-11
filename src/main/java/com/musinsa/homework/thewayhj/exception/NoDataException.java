package com.musinsa.homework.thewayhj.exception;

import org.springframework.http.HttpStatus;
import javax.persistence.NoResultException;

public class NoDataException extends NoResultException {
    public static int code;
    public static String message;

    public NoDataException(HttpStatus httpStatus){
        code = httpStatus.value();
        message = httpStatus.getReasonPhrase();
    }
}
