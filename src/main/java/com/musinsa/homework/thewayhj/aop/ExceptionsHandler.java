package com.musinsa.homework.thewayhj.aop;

import com.musinsa.homework.thewayhj.exception.*;
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
	public ResponseEntity<?> handleNoContentException(){
		log.error("ERROR204 " +HttpStatus.NO_CONTENT.getReasonPhrase());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT.getReasonPhrase(), HttpStatus.NO_CONTENT);
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<?> handleBadRequestException(){
		log.error("ERROR400 " +HttpStatus.BAD_REQUEST.getReasonPhrase());
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<?> handleForbiddenException(){
		log.error("ERROR403 " +HttpStatus.FORBIDDEN.getReasonPhrase());
		return new ResponseEntity<>(HttpStatus.FORBIDDEN.getReasonPhrase(), HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<?> handleNotFoundException(){
		log.error("ERROR404 " +HttpStatus.NOT_FOUND.getReasonPhrase());
		return new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ConflictException.class)
	public ResponseEntity<?> handleConflictException(){
		log.error("ERROR409 " +HttpStatus.CONFLICT.getReasonPhrase());
		return new ResponseEntity<>(HttpStatus.CONFLICT.getReasonPhrase(), HttpStatus.CONFLICT);
	}

	@ExceptionHandler(ClientErrorException.class)
	public ResponseEntity<?> handleClientErrorException(){
		log.error("ERROR412 " +HttpStatus.PRECONDITION_FAILED.getReasonPhrase());
		return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED.getReasonPhrase(), HttpStatus.PRECONDITION_FAILED);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> handleRuntimeException(){
		log.error("ERROR500 " +HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}