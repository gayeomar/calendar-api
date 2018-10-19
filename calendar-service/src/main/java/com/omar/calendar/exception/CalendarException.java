package com.omar.calendar.exception;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.validation.FieldError;

@Data
@EqualsAndHashCode(callSuper=false)
public class CalendarException extends Exception{

	private final String code;
	
	private final String message;

	private final List<FieldError> fieldError;

}
