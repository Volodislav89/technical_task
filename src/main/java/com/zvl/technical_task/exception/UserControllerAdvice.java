package com.zvl.technical_task.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class UserControllerAdvice {
    @ExceptionHandler(value = UserException.class)
    public ResponseEntity<Object> userExceptionHandler(UserException userException) {
        log.info(userException.getMessage());
        return new ResponseEntity<>(userException.getMessage(), HttpStatus.NOT_FOUND);
    }
}
