package com.musinsa.homework.thewayhj.util;

import com.musinsa.homework.thewayhj.entity.ResultForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public class MessageHelper {

	public static ResultForm getException(HttpStatus httpStatus){
		String exceptionMessage = httpStatus.getReasonPhrase();
		ResultForm exceptionForm = ResultForm.builder()
				.code   (httpStatus.value())
				.message(exceptionMessage)
				.build();

		printLog(exceptionForm);
		return exceptionForm;
	}

	private static void printLog(ResultForm exceptionForm){
		log.error(exceptionForm.toString());
	}
}