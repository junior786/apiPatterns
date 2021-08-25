package com.junior.modules.exception;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
public class ErrorDetails {
    private String message;
    private LocalDateTime localDateTime;
    private String detail;
}
