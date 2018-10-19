package com.omar.calendar.exception;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CalendarExceptionHandler {

    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public CalendarExceptionResponseTo handleAll(Exception ex){
        System.out.println("************ 1 ControllerAdvice called");

        CalendarExceptionResponseTo response = new CalendarExceptionResponseTo(""
                        + "Error", "InvalidRequest", "Invalid request", null);
        return response;
    }

    @ExceptionHandler(value = { CalendarException.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public CalendarExceptionResponseTo handleCalendarEx(CalendarException ex){
        System.out.println("************ 1 ControllerAdvice called");

        CalendarExceptionResponseTo response = new CalendarExceptionResponseTo(""
                + "Error", "InvalidRequest", "Invalid request", ex.getFieldError());
        return response;
    }

    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CalendarExceptionResponseTo handleValidationError(MethodArgumentNotValidException ex){
        System.out.println("************ 2 ControllerAdvice called");

        final BindingResult result = ex.getBindingResult();

        final String errorMessage = ((result.getGlobalError() != null)
                && (result.getGlobalError().getDefaultMessage() != null)) ? result.getGlobalError().getDefaultMessage()
                : "Validation_Error";

        final List<FieldError> fieldError = (result == null ? null : result.getFieldErrors());

        CalendarExceptionResponseTo response = new CalendarExceptionResponseTo(""
                + errorMessage, "InvalidRequest", "Invalid request", fieldError);
        return response;
    }

    @ExceptionHandler(value = { BindException.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public CalendarExceptionResponseTo handleBindingError(BindException ex){
        System.out.println("************ 3 ControllerAdvice called");

        final BindingResult result = ex.getBindingResult();

        final String errorMessage = ((result.getGlobalError() != null)
                && (result.getGlobalError().getDefaultMessage() != null)) ? result.getGlobalError().getDefaultMessage()
                : "Validation_Error";

        final List<FieldError> fieldError = (result == null ? null : result.getFieldErrors());

        CalendarExceptionResponseTo response = new CalendarExceptionResponseTo(""
                + errorMessage, "InvalidRequest", "Invalid request", fieldError);
        return response;
    }

}
