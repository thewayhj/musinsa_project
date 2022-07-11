package com.musinsa.homework.thewayhj.aop;

import com.musinsa.homework.thewayhj.Exception.NoDataException;
import com.musinsa.homework.thewayhj.Exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @since       2022.07.07
 * @author      thewayhj
 * @description Exceptions Handler
**/
@Slf4j
@RestControllerAdvice
public class ExceptionsHandler {
	@ExceptionHandler(NoDataException.class)
	public ResponseEntity<?> handleNoContentException(NoDataException e){
		log.error("ERROR204 " +e.getMessage());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT.getReasonPhrase(), HttpStatus.NO_CONTENT);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<?> handleFoundException(NotFoundException e){
		log.error("ERROR404 " +e.getMessage());
		return new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> handleRuntimeException(RuntimeException e){
		log.error("ERROR500 " +e.getMessage());
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}