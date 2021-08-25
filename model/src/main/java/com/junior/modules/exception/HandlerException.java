package com.junior.modules.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class HandlerException {

    @ExceptionHandler(ExceptionPessoaNotFound.class)
    public ResponseEntity<?> PessoaException(ExceptionPessoaNotFound pessoaNotFound) {
        ErrorDetails errorDetails = ErrorDetails.builder()
                .detail("Pessoa não encontrada")
                .message(pessoaNotFound.getMessage())
                .localDateTime(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> MethodArgumentNotValid(MethodArgumentNotValidException methodArgumentNotValidException) {
        Map<String, String> errors = new HashMap<>(5);

        List<FieldError> fieldErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors();
        fieldErrors.forEach(p -> errors.put(p.getField(), p.getDefaultMessage()));

        ErrorDetailsValid errorDetailsValid = ErrorDetailsValid.builder()
                .errors(errors)
                .localDateTime(LocalDateTime.now())
                .message("Erro encontrado no corpo da requisição")
                .detail("Conferir o corpo da requisição")
                .build();
        return new ResponseEntity<>(errorDetailsValid, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> DefaultHandlerExcepetion(HttpMessageNotReadableException badRequestExcepetion) {
        ErrorDetails errorDetails = ErrorDetails.builder()
                .detail(badRequestExcepetion.getMessage())
                .message("Conferir o corpo da requisição")
                .localDateTime(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
