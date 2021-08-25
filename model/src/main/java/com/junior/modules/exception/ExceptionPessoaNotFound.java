package com.junior.modules.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExceptionPessoaNotFound extends RuntimeException{
    public ExceptionPessoaNotFound(String msg) {
        super(msg);
    }
}
