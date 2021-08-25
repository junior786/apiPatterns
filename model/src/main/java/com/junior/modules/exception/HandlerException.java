package com.junior.modules.exception;

import com.junior.modules.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class HandlerException {

    @ExceptionHandler(ExceptionPessoaNotFound.class)
    public ResponseEntity<?> PessoaException(ExceptionPessoaNotFound pessoaNotFound){
        ErrorDetails errorDetails = ErrorDetails.builder()
                .detail("Pessoa não encontrada")
                .message(pessoaNotFound.getMessage())
                .localDateTime(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
}
