package com.junior.modules.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class HandlerExcepetion {
    @ExceptionHandler(ExceptionCepNotFound.class)
    public ResponseEntity<?> ExceptionCepFound(ExceptionCepNotFound exceptionCepNotFound){
        ErrorDetails errorDetails = ErrorDetails.builder()
                .detail("Cep errado, por favor arrumar")
                .message("Cep errado")
                .localDateTime(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
}
