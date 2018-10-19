package com.omar.calendar.exception;

import java.util.List;
import lombok.Data;
import org.springframework.validation.FieldError;

@Data
public class CalendarExceptionResponseTo {

    private final String category;
    private final String code;
    private final String message;

    private final List<FieldError> fieldError;


}
