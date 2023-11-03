package com.ShopAll.apiShopAll.exceptionHandler;

import com.ShopAll.apiShopAll.exception.Error;
import com.ShopAll.apiShopAll.exception.Exception;
import com.ShopAll.apiShopAll.exception.RequestException;
import lombok.Data;
import org.apache.coyote.Response;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handleBadRequestException(MethodArgumentNotValidException e) {
        List<String> errorMessages = e.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .toList();
        return new Error("Something doesn't look right",errorMessages);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Exception handleIllegalCallerException() {
        //Exception ex = new Exception("Something went wrong", e.getMessage());
        //return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
        return new Exception("Something went wrong", "You've provided an invalid argument");
    }

    @ExceptionHandler(RequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Exception handleEmailDoesExists(RequestException e) {
        /*Exception ex = new Exception("Cannot use that email", e.getMessage());
        return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);*/
        return new Exception("Cannot use that parameter(s)", e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Exception handleRuntimeException(){
        return new Exception("Cannot solve your request", "We'll work on that");
    }
}
