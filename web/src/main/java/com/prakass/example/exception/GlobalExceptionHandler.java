package com.prakass.example.exception;

import com.prakass.example.exception.dto.ErrorDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({HttpMessageConversionException.class})
    public ResponseEntity<Object> handleMessageException(HttpMessageConversionException ex) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Bad request",
                "Error occurred while processing request body");
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        final List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        Map<String, Set<String>> errorsMap =  fieldErrors.stream().collect(
                Collectors.groupingBy(FieldError::getField,
                        Collectors.mapping(FieldError::getDefaultMessage, Collectors.toSet())
                )
        );
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Validation Failed",
                "Validation error occurred in in or more fields");
        errorDetails.setErrors(errorsMap.isEmpty()? ex:errorsMap);
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAllOtherGenericExceptions(Exception ex) {
        logger.error("An internal server error occurred", ex);
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Internal server error",
                "Internal server error occurred");
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
