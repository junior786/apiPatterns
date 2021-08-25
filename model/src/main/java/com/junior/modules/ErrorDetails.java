package com.junior.modules;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorDetails {
    private String message;
    private LocalDateTime localDateTime;
    private String detail;
}
