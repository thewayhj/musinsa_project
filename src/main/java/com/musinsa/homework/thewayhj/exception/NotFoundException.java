package com.musinsa.homework.thewayhj.exception;

import com.musinsa.homework.thewayhj.entity.enums.ExceptionCode;
import javax.persistence.NoResultException;

public class NotFoundException extends NoResultException {
    public static ExceptionCode code;
    public static String message;

    public NotFoundException(ExceptionCode exceptionCode, String ExceptionMessage){
        super(exceptionCode.name());
        code = exceptionCode;
        message = ExceptionMessage;
    }
}
